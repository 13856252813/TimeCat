package com.cary.activity.timecat.fragment.index.hotscenic;

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
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotScenicActivity extends BaseActivity {

    private static final String TAG = HotScenicActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recyclerViewhotscenic)
    RecyclerView recyclerViewhotscenic;
    @BindView(R.id.swipeRefreshlayout_hotscenic)
    SwipeRefreshLayout swipeRefreshlayoutHotscenic;

    private HotScenicApi hotScenicApi;
    private HotScenicCommitResult hotScenicComRes;
    private List<HotScenicCommitResult.Data> mHotScenicData;
    private HotScenicAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private SharedPreferencesHelper sharePh;
    private String token;
    private String uid;
    private int currentpage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleText.setText("热门景点");

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = ((int) sharePh.getSharedPreference("id", 0) + "");

        hotScenicApi = HotScenicApi.getApi();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewhotscenic.setLayoutManager(mLayoutManager);
        recyclerViewhotscenic.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new HotScenicAdapter(this);
        recyclerViewhotscenic.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(HotScenicActivity.this, HotScenicDetialActivity.class);
                intent.putExtra("id", mHotScenicData.get(postion).getId());
                startActivityForResult(intent, 1001);
            }
        });

        swipeRefreshlayoutHotscenic.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingle();
            }
        });

        recyclerViewhotscenic.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        return R.layout.activity_hot_scenic;
    }

    public void loadMore() {
        mAdapter.setLoadStatus(HotScenicAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingle();
    }

    private int SumPage = 1;
    private Callback<HotScenicCommitResult> callbackPage = new Callback<HotScenicCommitResult>() {
        @Override
        public void onResponse(Call<HotScenicCommitResult> call, Response<HotScenicCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                hotScenicComRes = response.body();
                if ("00".equals(hotScenicComRes.getCode())) {
                    mHotScenicData = hotScenicComRes.getData();
//                    loadListDate(false, true, recyclerViewSelectStore, R.layout.activity_select_store_item_layout, mSStoreData);
//                    recyclerViewSelectStore.setNestedScrollingEnabled(false);
                    if (currentpage == 1) {
                        mAdapter.reSetData(mHotScenicData);
                        swipeRefreshlayoutHotscenic.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mHotScenicData);
                    }
                    SumPage = hotScenicComRes.getPi().getTotalPage();
                    //总弄页数
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(HotScenicAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(HotScenicAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(HotScenicActivity.this, hotScenicComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<HotScenicCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<HotScenicCommitResult> call = hotScenicApi.getService().createCommitPage(token, currentpage);
        call.enqueue(callbackPage);
    }

}
