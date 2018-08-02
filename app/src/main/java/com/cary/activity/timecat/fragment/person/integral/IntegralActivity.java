package com.cary.activity.timecat.fragment.person.integral;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.fragment.look.integral.IntegralMallActivity;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntegralActivity extends BaseActivity {
    private static final String TAG = IntegralActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_accmount_integral_text)
    TextView tvAccmountIntegralText;
    @BindView(R.id.tv_accmount_integral)
    TextView tvAccmountIntegral;
    @BindView(R.id.iv_integral_mall_arrow)
    ImageView ivIntegralMallArrow;
    @BindView(R.id.tv_tointegralmall_text)
    TextView tvTointegralmallText;
    @BindView(R.id.rl_tointegral_mall)
    RelativeLayout rlTointegralMall;
    @BindView(R.id.recycler_integral_list)
    RecyclerView recyclerIntegralList;
    @BindView(R.id.tv_nomore_text)
    TextView tvNomoreText;
    @BindView(R.id.swiperefreshlayout_integral)
    SwipeRefreshLayout swiperefreshlayoutIntegral;

    private IntegralAdapter mAdapter;
    private int integral;
    private IntegralApi mApi;
    private IntegralResult mRes;
    private List<IntegralResult.Data> mData;
    private LinearLayoutManager mLayoutManager;

    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int currentpage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_integral);
        ButterKnife.bind(this);

        titleText.setText("积分");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        integral = getIntent().getIntExtra("integral", 0);
        tvAccmountIntegral.setText(integral + "");

        recyclerIntegralList.addItemDecoration(new SpaceItemDecoration(20));
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mApi = IntegralApi.getApi();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerIntegralList.setLayoutManager(mLayoutManager);
        recyclerIntegralList.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new IntegralAdapter(this);
        recyclerIntegralList.setAdapter(mAdapter);

        swiperefreshlayoutIntegral.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingle();
            }
        });

        recyclerIntegralList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override//滚动状态变化时回调
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
                // mLastVisibleItemPosition=mLayoutManager.findLastVisibleItemPosition();
                //滑动停止并且底部不可滚动（即滑动到底部） 加载更多
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !(ViewCompat.canScrollVertically(recyclerView, 1))) {
                    if (currentpage < SumPage) {
                        loadMore();
                    }
                }
            }

            @Override//滚动时回调
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        createSingle();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_integral;
    }

    @OnClick({R.id.title_back, R.id.rl_tointegral_mall})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.rl_tointegral_mall:
                //转到积分商城
                startActivity(new Intent(this, IntegralMallActivity.class));
                break;
        }
    }

    public void loadMore() {
        mAdapter.setLoadStatus(IntegralAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingle();
    }

    private int SumPage = 1;
    private Callback<IntegralResult> callback = new Callback<IntegralResult>() {
        @Override
        public void onResponse(Call<IntegralResult> call, Response<IntegralResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    mData = mRes.getData();
//                    loadListDate(false, true, recyclerViewSelectStore, R.layout.activity_select_store_item_layout, mSStoreData);
//                    recyclerViewSelectStore.setNestedScrollingEnabled(false);
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        swiperefreshlayoutIntegral.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    SumPage = mRes.getPi().getTotalPage();
                    //总弄页数
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(IntegralAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(IntegralAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(IntegralActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<IntegralResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<IntegralResult> call = mApi.getService().createCommitPage(token, uid, currentpage);
        call.enqueue(callback);
    }

}
