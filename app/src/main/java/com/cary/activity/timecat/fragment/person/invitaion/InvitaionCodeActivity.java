package com.cary.activity.timecat.fragment.person.invitaion;

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
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 邀请码 界面
 */
public class InvitaionCodeActivity extends BaseActivity {

    private static final String TAG = InvitaionCodeActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_invitaion_code)
    TextView tvInvitaionCode;
    @BindView(R.id.tv_invitaion_code_text)
    TextView tvInvitaionCodeText;
    @BindView(R.id.iv_invitaion_code_arrow)
    ImageView ivInvitaionCodeArrow;
    @BindView(R.id.rl_invitaion_code_write)
    RelativeLayout rlInvitaionCodeWrite;
    @BindView(R.id.recycler_invitaion_code_person)
    RecyclerView recyclerInvitaionCodePerson;
    @BindView(R.id.tv_invitaion_person)
    TextView tvInvitaionPerson;
    @BindView(R.id.swiperefreshlayout_invitaion)
    SwipeRefreshLayout swiperefreshlayoutInvitaion;

    private LinearLayoutManager mLayoutManager;
    private MyInvitaionAdapter mAdapter;
    private String myself;
    private InvitaionResult mRes;
    private List<InvitaionResult.Data> mData;
    private InvitaionApi mApi;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    //    private int currentpage = 1;
    private int invitaionPerson;//邀请人的id 根据这个id 再去获取


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_invitaion_code);
        ButterKnife.bind(this);
        titleText.setText("邀请码");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleTextRight.setVisibility(View.VISIBLE);
        titleTextRight.setText("");


        myself = getIntent().getStringExtra("myself");
        invitaionPerson = getIntent().getIntExtra("invitaionperson", 0);
        tvInvitaionCode.setText(myself);
        if (invitaionPerson != 0) {
            tvInvitaionPerson.setText(invitaionPerson + "");
        }

        recyclerInvitaionCodePerson.addItemDecoration(new SpaceItemDecoration(20));
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mApi = InvitaionApi.getApi();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerInvitaionCodePerson.setLayoutManager(mLayoutManager);
        recyclerInvitaionCodePerson.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MyInvitaionAdapter(this);
        recyclerInvitaionCodePerson.setAdapter(mAdapter);

        swiperefreshlayoutInvitaion.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                createSingle();
            }
        });

        recyclerInvitaionCodePerson.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override//滚动状态变化时回调
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
                // mLastVisibleItemPosition=mLayoutManager.findLastVisibleItemPosition();
                //滑动停止并且底部不可滚动（即滑动到底部） 加载更多
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !(ViewCompat.canScrollVertically(recyclerView, 1))) {
                    loadMore();
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
        return R.layout.activity_invitaion_code;
    }

    @OnClick({R.id.title_back, R.id.rl_invitaion_code_write})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.rl_invitaion_code_write:
                Intent intent = new Intent(this, WriteInvitaionActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void loadMore() {
        mAdapter.setLoadStatus(MyInvitaionAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
//        currentPage ++ ;
        createSingle();
    }

    //    private int SumPage = 1;
    private Callback<InvitaionResult> callback = new Callback<InvitaionResult>() {
        @Override
        public void onResponse(Call<InvitaionResult> call, Response<InvitaionResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    mData = mRes.getData();
//                    if (currentpage == 1) {
                    mAdapter.reSetData(mData);
                    swiperefreshlayoutInvitaion.setRefreshing(false);
//                    } else if (currentpage > 1) {
//                        mAdapter.addAll(mData);
//                    }
//                    SumPage = mRes.getPi().getTotalPage();
//                    //总弄页数
//                    if (SumPage < 2) {
//                        mAdapter.setLoadStatus(MyInvitaionAdapter.LoadStatus.LOADING_GONE);
//                    } else {
//                        mAdapter.setLoadStatus(MyInvitaionAdapter.LoadStatus.CLICK_LOAD_MORE);
//                    }
                } else {
                    ToastUtil.showShort(InvitaionCodeActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<InvitaionResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<InvitaionResult> call = mApi.getService().createCommitPage(token, uid);
        call.enqueue(callback);
    }
}
