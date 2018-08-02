package com.cary.activity.timecat.manager.client.myorder;

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

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;
import com.cary.activity.timecat.manager.client.myorder.orderdetial.OrderDetialActivity;
import com.cary.activity.timecat.manager.teacher.fragment.grab.TaskDetialActivity;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 套餐
 */
@SuppressLint("ValidFragment")
public class ToBeCompletedFragment extends Fragment {
    private static String TAG = ToBeCompletedFragment.class.getSimpleName();
    @BindView(R.id.recycler_meal)
    RecyclerView recyclerMeal;
    Unbinder unbinder;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;

    private ToBeCompletedAdapter mAdapter;
    private SharedPreferencesHelper sharePH;

    private TaskApi taskApi;
    private String token;
    private int storeId;
    private TaskListResult taskRes;
    private int currentpage = 1;
    private int type = 0;
    private int status = 0;

    @SuppressLint("ValidFragment")
    public ToBeCompletedFragment(int type) {
        // Required empty public constructor
        if (type == 0) {
            status = 0;
        } else if (type == 1) {
            //退款中
            status = 2;
        }
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

        taskApi = TaskApi.getApi();
        sharePH = new SharedPreferencesHelper(getActivity());
        token = (String) sharePH.getSharedPreference("token", "");
        storeId = (int) sharePH.getSharedPreference("id", 0);
        //在加载数据之前配置
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerMeal.setLayoutManager(linearLayoutManager);
        //创建一个适配器
        swiperefreshlayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        recyclerMeal.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ToBeCompletedAdapter(getActivity());
        recyclerMeal.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent();
                if (type == 1) {
                    intent.setClass(getActivity(), ReturnOrderActivity.class);
                    intent.putExtra("ordertype", 1);//taskRes.getData().get(postion).getorderType());

                } else {
                    String type = taskRes.getData().get(postion).getType();
                    if ("gp".equals(type) || "sx".equals(type) || "gz".equals(type)) {
                        intent.setClass(getActivity(), TaskDetialActivity.class);
                    } else if ("wedding".equals(type) || "photo".equals(type) || "baby".equals(type)) {
                        intent.setClass(getActivity(), OrderDetialActivity.class);
                    }
                }
                intent.putExtra("id", taskRes.getData().get(postion).getId());
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
                createSingleTask(status);
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
        createSingleTask(status);
//
//        recyclerMeal.addItemDecoration(new SpaceItemDecoration(20));
//        //在加载数据之前配置
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerMeal.setLayoutManager(linearLayoutManager);
//        //创建一个适配器
//        myAdapter = new ToBeCompletedAdapter(getActivity());
//        recyclerMeal.setAdapter(myAdapter);
//        List<String> mData = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            mData.add("title:" + i);
//        }
//        myAdapter.addData(mData);

        return view;
    }

    public void loadMore() {
        mAdapter.setLoadStatus(ToBeCompletedAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingleTask(status);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private int SumPage = 1;
    //    动态咨询 加载数据
    private Callback<TaskListResult> callbackTask = new Callback<TaskListResult>() {
        @Override
        public void onResponse(Call<TaskListResult> call, Response<TaskListResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                taskRes = response.body();
                if ("00".equals(taskRes.getCode())) {
                    if (currentpage == 1) {
                        mAdapter.reSetData(taskRes.getData());
                        swiperefreshlayout.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(taskRes.getData());
                    }
                    //总弄页数
                    SumPage = taskRes.getPi().getTotalPage();
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(ToBeCompletedAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(ToBeCompletedAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), taskRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<TaskListResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleTask(int status) {
        Call<TaskListResult> call = taskApi.getService().createCommitPage(token, storeId, status, currentpage);
        call.enqueue(callbackTask);
    }
}
