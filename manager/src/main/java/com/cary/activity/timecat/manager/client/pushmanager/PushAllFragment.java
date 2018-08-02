package com.cary.activity.timecat.manager.client.pushmanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 推送全体
 */
@SuppressLint("ValidFragment")
public class PushAllFragment extends Fragment {
    private static String TAG = PushAllFragment.class.getSimpleName();
    @BindView(R.id.recycler_meal)
    RecyclerView recyclerMeal;
    Unbinder unbinder;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.btn_push_msg)
    Button btnPushMsg;
    @BindView(R.id.ll_push_msg)
    LinearLayout llPushMsg;

    private PushAdapter mAdapter;
    private SharedPreferencesHelper sharePH;

    private PushApi mApi;
    private String token;
    private int uid;
    private int storeId;
    private PushResult mRes;
    private int currentpage = 1;
    private int type = 1;

    @SuppressLint("ValidFragment")
    public PushAllFragment(int type) {
        // Required empty public constructor
        this.type = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attention_meal, container, false);
        unbinder = ButterKnife.bind(this, view);

        mApi = PushApi.getApi();
        sharePH = new SharedPreferencesHelper(getActivity());
        token = (String) sharePH.getSharedPreference("token", "");
        uid = (int) sharePH.getSharedPreference("id", 0);
        storeId = (int) sharePH.getSharedPreference("storeId", 0);
        llPushMsg.setVisibility(View.VISIBLE);

        btnPushMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PushMessageActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });

        //在加载数据之前配置
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerMeal.setLayoutManager(linearLayoutManager);
        //创建一个适配器
        swiperefreshlayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        recyclerMeal.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new PushAdapter(getActivity());
        recyclerMeal.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), PushDetialActivity.class);
                intent.putExtra("id", mRes.getData().get(postion).getId());
                startActivity(intent);
            }

            @Override
            public void onItemClick(int postion) {

            }
        });

        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingleTask();
            }
        });

        recyclerMeal.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        return view;
    }

    public void loadMore() {
        mAdapter.setLoadStatus(PushAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingleTask();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private int SumPage = 1;
    //    动态咨询 加载数据
    private Callback<PushResult> callbackTask = new Callback<PushResult>() {
        @Override
        public void onResponse(Call<PushResult> call, Response<PushResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    if (currentpage == 1) {
                        mAdapter.reSetData(mRes.getData());
                        swiperefreshlayout.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mRes.getData());
                    }
                    //总弄页数
                    SumPage = mRes.getPi().getTotalPage();
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(PushAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(PushAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<PushResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleTask() {
        Call<PushResult> call = mApi.getService().createCommitPage(token, type, storeId, currentpage);
        call.enqueue(callbackTask);
    }
}
