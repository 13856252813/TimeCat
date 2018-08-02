package com.cary.activity.timecat.fragment.index.baseservice;

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

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.selectsetmeal.SelectSetMealActivity;
import com.cary.activity.timecat.fragment.index.selectstore.RecyclerViewSStoreAdapter;
import com.cary.activity.timecat.fragment.index.selectstore.SelectStoreActivity;
import com.cary.activity.timecat.fragment.index.selectstore.SelectStoreApi;
import com.cary.activity.timecat.fragment.index.selectstore.SelectStoreCommitResult;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseServiceActivity extends AppCompatActivity {
    private static final String TAG = SelectStoreActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.recyclerViewSelectStore)
    RecyclerView recyclerViewSelectStore;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.swiperefreshlayout_store)
    SwipeRefreshLayout swiperefreshlayoutStore;

    private SelectStoreApi mSStoreApi;
    private SelectStoreCommitResult mSStoreComRes;
    private List<SelectStoreCommitResult.Data> mSStoreData;
    private LinearLayoutManager mLayoutManager;
    private RecyclerViewSStoreAdapter mAdapter;

    private SharedPreferencesHelper sharePh;
    private String token;
    private String uid;
    private int currentpage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_service);
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
        titleText.setText("基础服务");
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = ((int) sharePh.getSharedPreference("id", 0) + "");

        mSStoreApi = SelectStoreApi.getApi();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewSelectStore.setLayoutManager(mLayoutManager);
        recyclerViewSelectStore.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecyclerViewSStoreAdapter(this);
        recyclerViewSelectStore.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(BaseServiceActivity.this, SelectSetMealActivity.class);
                intent.putExtra("id", mSStoreData.get(postion).getId());
                startActivity(intent);
            }
        });

        swiperefreshlayoutStore.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingle();
            }
        });

        recyclerViewSelectStore.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override//滚动状态变化时回调
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
                // mLastVisibleItemPosition=mLayoutManager.findLastVisibleItemPosition();
                //滑动停止并且底部不可滚动（即滑动到底部） 加载更多
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !(ViewCompat.canScrollVertically(recyclerView, 1))) {
                    if(currentpage<SumPage) {
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

    public void loadMore() {
        mAdapter.setLoadStatus(RecyclerViewSStoreAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingle();
    }

    private  int SumPage = 1;
    private Callback<SelectStoreCommitResult> callbackStore = new Callback<SelectStoreCommitResult>() {
        @Override
        public void onResponse(Call<SelectStoreCommitResult> call, Response<SelectStoreCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mSStoreComRes = response.body();
                if ("00".equals(mSStoreComRes.getCode())) {
                    mSStoreData = mSStoreComRes.getData();
//                    loadListDate(false, true, recyclerViewSelectStore, R.layout.activity_select_store_item_layout, mSStoreData);
                    recyclerViewSelectStore.setNestedScrollingEnabled(false);
                    if (currentpage == 1) {
                        mAdapter.reSetData(mSStoreData);
                        swiperefreshlayoutStore.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mSStoreData);
                    }
                    //总弄页数
                    SumPage = mSStoreComRes.getPi().getTotalPage();
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(RecyclerViewSStoreAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(RecyclerViewSStoreAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(BaseServiceActivity.this, mSStoreComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<SelectStoreCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<SelectStoreCommitResult> call = mSStoreApi.getService().createCommitPage(token, currentpage);
        call.enqueue(callbackStore);
    }
}
