package com.cary.activity.timecat.manager.teacher.fragment.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 排期 时间列表
 */
public class ScheduleListFragment extends Fragment {

    private static final String TAG = ScheduleListFragment.class.getSimpleName();
    @BindView(R.id.grab_recycler)
    RecyclerView grabRecycler;
    @BindView(R.id.swiperefreshlayout_grab)
    SwipeRefreshLayout swiperefreshlayoutGrab;
    Unbinder unbinder;

    private View viewContent;
    private int mType = 0;
    private String mTitle;

    private ScheduleApi mApi;
    private ScheduleResult mRes;
    private List<ScheduleResult.Data> mData;
    private SharedPreferencesHelper sharehelper;
    private int uid;
    private String token;
    private int currentpage = 1;
    private ScheduleRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    public void setType(int mType) {
        this.mType = mType;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_content, container, false);
//        RecyclerView grab_recycler = (RecyclerView) viewContent.findViewById(R.id.grab_recycler);
        unbinder = ButterKnife.bind(this, viewContent);

//        loadListDate(false, true, grab_recycler, R.layout.fragment_content_item_schedule, mData);
//        grab_recycler.setNestedScrollingEnabled(false);
        mApi = ScheduleApi.getApi();
        sharehelper = new SharedPreferencesHelper(getActivity());
        token = (String) sharehelper.getSharedPreference("token", "");
        uid = (int) sharehelper.getSharedPreference("id", 0);

        mLayoutManager = new LinearLayoutManager(getActivity());
        grabRecycler.setLayoutManager(mLayoutManager);
        grabRecycler.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ScheduleRecyclerViewAdapter(getActivity());
        grabRecycler.setAdapter(mAdapter);
        mAdapter.setItemClickListener(new ScheduleRecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int postion) {

            }


        });

        swiperefreshlayoutGrab.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                setMethod();
            }
        });

        grabRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        setMethod();
        return viewContent;
    }

    private void setMethod() {
        if (mTitle.equals("待完成")) {
            createSingle(false);
        } else if (mTitle.equals("已完成")) {
            createSingle(true);
        }
    }

    public void loadMore() {
        mAdapter.setLoadStatus(ScheduleRecyclerViewAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        setMethod();
    }

    //    动态咨询 加载数据
    private Callback<ScheduleResult> callback = new Callback<ScheduleResult>() {
        @Override
        public void onResponse(Call<ScheduleResult> call, Response<ScheduleResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    mData = mRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        swiperefreshlayoutGrab.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    //总弄页数
                    if (mRes.getPi().getTotalPage() < 2) {
                        mAdapter.setLoadStatus(ScheduleRecyclerViewAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(ScheduleRecyclerViewAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ScheduleResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(boolean success) {
        Call<ScheduleResult> call = mApi.getService().createCommitPageSuccess(token, success, currentpage);
        call.enqueue(callback);
    }


    //RecyclerView实现ListView效果，实际就是布局管理器参数改为GridLayoutManager
    private void loadListDate(Boolean inversion, Boolean orientation,
                              final RecyclerView recyclerViewGrid, int layoutId, List<String> mHotScenicData) {

//创建适配器adapter对象 参数1.上下文 2.数据加载集合
        ScheduleRecyclerViewAdapter recyclerViewGridAdapter = new ScheduleRecyclerViewAdapter(getActivity());
//设置适配器
        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
//布局管理器对象 参数1.上下文 2.规定显示的行数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
//通过布局管理器可以控制条目排列的顺序 true反向显示 false正常显示(默认)
        gridLayoutManager.setReverseLayout(inversion);
//设置RecycleView显示的方向是水平还是垂直
//GridLayout.HORIZONTAL水平 GridLayout.VERTICAL默认垂直
// 三元运算符
        gridLayoutManager.setOrientation(orientation ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
//设置布局管理器， 参数linearLayoutManager对象
        recyclerViewGrid.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}