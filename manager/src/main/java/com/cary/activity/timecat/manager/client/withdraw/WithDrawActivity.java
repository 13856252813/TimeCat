package com.cary.activity.timecat.manager.client.withdraw;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 提现申请
 */
public class WithDrawActivity extends AppCompatActivity {

    private static final String TAG = WithDrawActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recycler_withdraw)
    RecyclerView recyclerWithdraw;
    @BindView(R.id.swiperefreshlayout_withdraw)
    SwipeRefreshLayout swiperefreshlayoutWithdraw;

    private WithDrawAdapter mAdapter;
    private SharedPreferencesHelper sharePH;

    private WithDrawApi mApi;
    private String token;
    private int uid;
    private WithDrawResult mRes;
    private int currentpage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_with_draw);
        ButterKnife.bind(this);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleText.setText("提现申请");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

        mApi = WithDrawApi.getApi();
        sharePH = new SharedPreferencesHelper(this);
        token = (String) sharePH.getSharedPreference("token", "");
        uid = (int) sharePH.getSharedPreference("id", 0);

        //在加载数据之前配置
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerWithdraw.setLayoutManager(linearLayoutManager);
        recyclerWithdraw.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new WithDrawAdapter(this);
        recyclerWithdraw.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
//                Intent intent = new Intent(WithDrawActivity.this, TaskDetialActivity.class);
//                intent.putExtra("id", mRes.getData().get(postion).getId());
//                startActivity(intent);
            }

            @Override
            public void onItemClick(int postion) {
                ToastUtil.showShort(WithDrawActivity.this, "status:" + mRes.getData().get(postion).getStatus());
            }
        });

        swiperefreshlayoutWithdraw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingleTask();
            }
        });

        recyclerWithdraw.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        createSingleTask();

    }

    public void loadMore() {
        mAdapter.setLoadStatus(WithDrawAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingleTask();
    }

    private int SumPage = 1;
    //    动态咨询 加载数据
    private Callback<WithDrawResult> callbackTask = new Callback<WithDrawResult>() {
        @Override
        public void onResponse(Call<WithDrawResult> call, Response<WithDrawResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    if (currentpage == 1) {
                        mAdapter.reSetData(mRes.getData());
                        swiperefreshlayoutWithdraw.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mRes.getData());
                    }
                    //总弄页数
                    SumPage = mRes.getPi().getTotalPage();
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(WithDrawAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(WithDrawAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(WithDrawActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<WithDrawResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleTask() {
        Call<WithDrawResult> call = mApi.getService().createCommitPage(token, uid, currentpage);
        call.enqueue(callbackTask);
    }
}
