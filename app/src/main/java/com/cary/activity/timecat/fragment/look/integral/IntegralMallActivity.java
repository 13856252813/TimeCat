package com.cary.activity.timecat.fragment.look.integral;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.look.integral.exchange.IntegralExchangeActivity;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntegralMallActivity extends BaseActivity {
    private static final String TAG = IntegralMallActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_integral_mall_all_text)
    TextView tvIntegralMallAllText;
    @BindView(R.id.tv_integral_mall_all_line)
    TextView tvIntegralMallAllLine;
    @BindView(R.id.ll_integral_mall_all)
    LinearLayout llIntegralMallAll;
    @BindView(R.id.tv_integral_mall_exchange_text)
    TextView tvIntegralMallExchangeText;
    @BindView(R.id.tv_integral_mall_exchange_line)
    TextView tvIntegralMallExchangeLine;
    @BindView(R.id.ll_integral_mall_exchange)
    LinearLayout llIntegralMallExchange;
    @BindView(R.id.recycler_integrall_mall)
    RecyclerView recyclerIntegrallMall;
    @BindView(R.id.swiperefreshlayout_integral)
    SwipeRefreshLayout swiperefreshlayoutIntegral;

    private IntegralApi mApi;
    private IntegralListResult mRes;
    private List<IntegralListResult.Data> mData;
    private IntegralMallAdapter mAdapter;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int currentpage = 1;
    private int uid;
    private int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_integral_mall);
        ButterKnife.bind(this);

        titleText.setText("积分商城");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mApi = IntegralApi.getApi();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setReverseLayout(false);
        gridLayoutManager.setOrientation(true ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
//设置布局管理器， 参数linearLayoutManager对象
        recyclerIntegrallMall.setLayoutManager(gridLayoutManager);
        recyclerIntegrallMall.addItemDecoration(new DividerItemDecoration(this, 1));
        mAdapter = new IntegralMallAdapter(this);
        recyclerIntegrallMall.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(IntegralMallActivity.this, IntegralExchangeActivity.class);
                intent.putExtra("id", mData.get(postion).getId());
                startActivity(intent);
            }
        });

        swiperefreshlayoutIntegral.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                setMethod(flag);
            }
        });

        recyclerIntegrallMall.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        setMethod(flag);

//        loadListDate(false, true, recyclerIntegrallMall, R.layout.activity_integral_mall_list_item, mData);
    }

    public void loadMore() {
        mAdapter.setLoadStatus(IntegralMallAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        setMethod(flag);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_integral_mall;
    }

    @OnClick({R.id.title_back, R.id.ll_integral_mall_all, R.id.ll_integral_mall_exchange})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_integral_mall_all:
                setTextColor(true);//全部
                currentpage = 1;
                setMethod(1);
                break;
            case R.id.ll_integral_mall_exchange:
                setTextColor(false);//积分兑换
                currentpage = 1;
                flag = 2;
                setMethod(2);
                break;
        }
    }

    private void setMethod(int flag) {
        if(mData!=null&&mData.size()>0){
            mData.clear();
        }
        if (flag == 1) {
            createSingleIntegralAll();
        } else if (flag == 2) {
            createSingleIntegral(uid);
        }
    }

    private void setTextColor(boolean isFlag) {
        if (isFlag) {
            tvIntegralMallAllText.setTextColor(getResources().getColor(R.color.login_color_btn));
            tvIntegralMallAllLine.setBackgroundColor(getResources().getColor(R.color.login_color_btn));
            tvIntegralMallExchangeText.setTextColor(getResources().getColor(R.color.color_three));
            tvIntegralMallExchangeLine.setBackgroundColor(getResources().getColor(R.color.transparent));
        } else {
            tvIntegralMallExchangeText.setTextColor(getResources().getColor(R.color.login_color_btn));
            tvIntegralMallExchangeLine.setBackgroundColor(getResources().getColor(R.color.login_color_btn));
            tvIntegralMallAllText.setTextColor(getResources().getColor(R.color.color_three));
            tvIntegralMallAllLine.setBackgroundColor(getResources().getColor(R.color.transparent));

        }
    }

    //加载数据
    private Callback<IntegralListResult> callbackIntegral = new Callback<IntegralListResult>() {
        @Override
        public void onResponse(Call<IntegralListResult> call, Response<IntegralListResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    mData = mRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        swiperefreshlayoutIntegral.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    //总弄页数
                    if (mRes.getPi().getTotalPage() < 2) {
                        mAdapter.setLoadStatus(IntegralMallAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(IntegralMallAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(IntegralMallActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<IntegralListResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleIntegral(int uid) {
        Call<IntegralListResult> call = mApi.getService().createCommitPage(token, uid, currentpage);
        call.enqueue(callbackIntegral);
    }

    private void createSingleIntegralAll() {
        Call<IntegralListResult> call = mApi.getService().createCommitPageAll(token, currentpage);
        call.enqueue(callbackIntegral);
    }

    //RecyclerView实现ListView效果，实际就是布局管理器参数改为GridLayoutManager
    private void loadListDate(Boolean inversion, Boolean orientation,
                              final RecyclerView recyclerViewGrid, int layoutId, List<String> mData) {

//创建适配器adapter对象 参数1.上下文 2.数据加载集合
        IntegralMallAdapter recyclerViewGridAdapter = new IntegralMallAdapter(this);
//设置适配器
        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
//布局管理器对象 参数1.上下文 2.规定显示的行数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
//通过布局管理器可以控制条目排列的顺序 true反向显示 false正常显示(默认)
        gridLayoutManager.setReverseLayout(inversion);
//设置RecycleView显示的方向是水平还是垂直
//GridLayout.HORIZONTAL水平 GridLayout.VERTICAL默认垂直
// 三元运算符
        gridLayoutManager.setOrientation(orientation ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
//设置布局管理器， 参数linearLayoutManager对象
        recyclerViewGrid.setLayoutManager(gridLayoutManager);
        recyclerViewGrid.addItemDecoration(new DividerItemDecoration(this, 1));
        recyclerViewGridAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(IntegralMallActivity.this, IntegralExchangeActivity.class);
                intent.putExtra("flagTag", postion + "");
                startActivity(intent);
            }
        });
    }
}
