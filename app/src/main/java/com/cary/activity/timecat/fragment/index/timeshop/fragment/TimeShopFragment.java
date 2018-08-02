package com.cary.activity.timecat.fragment.index.timeshop.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.fragment.TimeShopGridAdapter;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.fragment.index.timeshop.TimeShopDetialActivity;
import com.cary.activity.timecat.fragment.index.timeshop.TimeShopTabApi;
import com.cary.activity.timecat.fragment.index.timeshop.TimeShopTabResult;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cary on 2018/4/10.
 * 时光超市分类
 */

@SuppressLint("ValidFragment")
public class TimeShopFragment extends Fragment {
    private static final String TAG = TimeShopFragment.class.getSimpleName();

    private TimeShopTabResult.DataBean data;//获取分类的id
    private TimeShopGridAdapter mAdapter;
    private TimeShopTabApi mApi;
    private TimeShopProductResult mRes;
    private List<TimeShopProductResult.DataBean> mData;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private SharedPreferencesHelper sharePh;
    private String token;
    private String uid;
    private int currentpage = 1;
    private String storeId;
//    public static Fragment newInstance(int pos) {
//        position = pos;
//        TabFragment fragment = new TabFragment();
//        return fragment;
//    }

    public TimeShopFragment(TimeShopTabResult.DataBean data) {
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.select_set_meal_all, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        //通过布局管理器可以控制条目排列的顺序 true反向显示 false正常显示(默认)
        gridLayoutManager.setReverseLayout(false);
        //设置RecycleView显示的方向是水平还是垂直
        //GridLayout.HORIZONTAL水平 GridLayout.VERTICAL默认垂直
        // 三元运算符
        gridLayoutManager.setOrientation(true ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
        //设置布局管理器， 参数linearLayoutManager对象
        recyclerView.setLayoutManager(gridLayoutManager);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new SpaceItemDecoration(5));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

//        mAdapter = new SelectSetMealRecyclerAdapter(getActivity(),mListDatas);
//        recyclerView.setAdapter(mAdapter);
//
//        mAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int postion) {
//                ToastUtil.showShort(getActivity(), "Grid 1 postion:" + postion);
//                Intent intent = new Intent(getActivity(), SetMealDetialActivity.class);
//                intent.putExtra("id","123");
//                startActivity(intent);
//            }
//        });

        sharePh = new SharedPreferencesHelper(getActivity());
        token = (String) sharePh.getSharedPreference("token", "");
        uid = ((int) sharePh.getSharedPreference("id", 0) + "");
        storeId = sharePh.getSharedPreference("storeId", 0) + "";

        mApi = TimeShopTabApi.getApi();
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefreshlayout_setmeal);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new TimeShopGridAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(getActivity(), TimeShopDetialActivity.class);
                intent.putExtra("id", mData.get(postion).getId());
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingleColoth(data.getId());
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        createSingleColoth(data.getId());
        return rootView;
    }

    public void loadMore() {
        mAdapter.setLoadStatus(TimeShopGridAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingleColoth(data.getId());
    }

    private int SumPage = 1;
    //    服装列表 加载数据
    private Callback<TimeShopProductResult> callback  = new Callback<TimeShopProductResult>() {
        @Override
        public void onResponse(Call<TimeShopProductResult> call, Response<TimeShopProductResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    mData = mRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        mSwipeRefreshLayout.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    //总弄页数
                    SumPage = mRes.getPi().getTotalPage();
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(TimeShopGridAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(TimeShopGridAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<TimeShopProductResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleColoth(int catagory) {
        Call<TimeShopProductResult> call = mApi.getService().createCommitPage(token,
                catagory , currentpage);
        call.enqueue(callback );
    }

}