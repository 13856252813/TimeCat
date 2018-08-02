package com.cary.activity.timecat.fragment.person.balance;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * 余额
 */
public class BalanceActivity extends BaseActivity {

    private static final String TAG = BalanceActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_accmount_balance)
    TextView tvAccmountBalance;
    @BindView(R.id.tv_accmount_balance_yuan)
    TextView tvAccmountBalanceYuan;
    @BindView(R.id.tv_balance_recharge)
    TextView tvBalanceRecharge;
    @BindView(R.id.ll_balance_recharge)
    LinearLayout llBalanceRecharge;
    @BindView(R.id.tv_balance_withdraw)
    TextView tvBalanceWithdraw;
    @BindView(R.id.ll_balance_withdraw)
    LinearLayout llBalanceWithdraw;
    @BindView(R.id.ll_accmountbalance)
    LinearLayout llAccmountbalance;
    @BindView(R.id.recycler_balance_list)
    RecyclerView recyclerBalanceList;
    @BindView(R.id.tv_nomore_text)
    TextView tvNomoreText;
    @BindView(R.id.swiperefreshlayout_balance)
    SwipeRefreshLayout swiperefreshlayoutBalance;
    private int balance;

    private BalanceAdapter mAdapter;
    private BalanceApi mApi;
    private BalanceResult mRes;
    private List<BalanceResult.Data> mData;
    private LinearLayoutManager mLayoutManager;

    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int currentpage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);

        titleText.setText("余额");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        balance = getIntent().getIntExtra("balance", 0);
        tvAccmountBalance.setText(balance + "");

        recyclerBalanceList.addItemDecoration(new SpaceItemDecoration(20));
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mApi = BalanceApi.getApi();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerBalanceList.setLayoutManager(mLayoutManager);
        recyclerBalanceList.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new BalanceAdapter(this);
        recyclerBalanceList.setAdapter(mAdapter);

        swiperefreshlayoutBalance.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingle();
            }
        });

        recyclerBalanceList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        return R.layout.activity_balance;
    }

    @OnClick({R.id.title_back, R.id.ll_balance_recharge, R.id.ll_balance_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_balance_withdraw:
                ToastUtil.showShort(this, "提现");
                break;
            case R.id.ll_balance_recharge:
                ToastUtil.showShort(this, "充值");
                break;
        }
    }

    public void loadMore() {
        mAdapter.setLoadStatus(BalanceAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingle();
    }

    private int SumPage = 1;
    private Callback<BalanceResult> callback = new Callback<BalanceResult>() {
        @Override
        public void onResponse(Call<BalanceResult> call, Response<BalanceResult> response) {
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
                        swiperefreshlayoutBalance.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    SumPage = mRes.getPi().getTotalPage();
                    //总弄页数
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(BalanceAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(BalanceAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(BalanceActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<BalanceResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<BalanceResult> call = mApi.getService().createCommitPage(token, uid, currentpage);
        call.enqueue(callback);
    }


}
