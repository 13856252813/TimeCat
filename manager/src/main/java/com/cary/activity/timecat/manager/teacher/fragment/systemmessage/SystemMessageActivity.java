package com.cary.activity.timecat.manager.teacher.fragment.systemmessage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 系统消息
 */
@SuppressLint("Registered")
public class SystemMessageActivity extends AppCompatActivity {

    private static final String TAG = SystemMessageActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recycler_system_message)
    RecyclerView recyclerSystemMessage;

    private SysMsgListViewAdapter mAdapter;
    private SysMsgApi mApi;
    private SysMsgResult mComRes;
    private List<SysMsgResult.Data> mData;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;

    private SharedPreferencesHelper sharePh;
    private String token;
    private String uid;
    private int currentpage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_message);
        ButterKnife.bind(this);

        titleText.setText("系统消息");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerSystemMessage.setLayoutManager(layoutManager);
////下面这行代码就是添加分隔线的方法
//        recyclerSystemMessage.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
////        recyclerSystemMessage.addItemDecoration(new DividerItemDecoration(this, 8));
//        recyclerListViewAdapter = new SysMsgListViewAdapter(this, mData);//添加适配器，这里适配器刚刚装入了数据
//        recyclerSystemMessage.setAdapter(recyclerListViewAdapter);

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = ((int) sharePh.getSharedPreference("id", 0) + "");

        mApi = SysMsgApi.getApi();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout_sysmsg);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerSystemMessage.setLayoutManager(mLayoutManager);
        recyclerSystemMessage.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SysMsgListViewAdapter(this);
        recyclerSystemMessage.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(SystemMessageActivity.this, SysMsgDetialActivity.class);
                intent.putExtra("id", mData.get(postion).getId());
                startActivity(intent);
            }

            @Override
            public void onItemClick(int postion) {

            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingleMsg();
            }
        });

        recyclerSystemMessage.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        createSingleMsg();

    }

    public void loadMore() {
        mAdapter.setLoadStatus(SysMsgListViewAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingleMsg();
    }

    private int SumPage = 1;
    //    动态咨询 加载数据
    private Callback<SysMsgResult> callbackNews = new Callback<SysMsgResult>() {
        @Override
        public void onResponse(Call<SysMsgResult> call, Response<SysMsgResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mComRes = response.body();
                if ("00".equals(mComRes.getCode())) {
                    mData = mComRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        mSwipeRefreshLayout.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    //总弄页数
                    SumPage = mComRes.getPi().getTotalPage();
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(SysMsgListViewAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(SysMsgListViewAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(SystemMessageActivity.this, mComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<SysMsgResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleMsg() {
        Call<SysMsgResult> call = mApi.getService().createCommitPage(token, uid, currentpage);
        call.enqueue(callbackNews);
    }

}
