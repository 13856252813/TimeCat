package com.cary.activity.timecat.manager.teacher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;
import com.cary.activity.timecat.manager.adapter.RecyclerViewListAdapter;
import com.cary.activity.timecat.manager.teacher.fragment.grab.GrabApi;
import com.cary.activity.timecat.manager.teacher.fragment.grab.GrabResult;
import com.cary.activity.timecat.manager.teacher.fragment.grab.TaskDetialActivity;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;
import com.cary.activity.timecat.manager.util.modelbean.ModelBeanData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentFragment extends Fragment {

    private static final String TAG = ContentFragment.class.getSimpleName();

    private View viewContent;
    private int mType = 0;
    private String mTitle;

    private GrabApi mApi;
    private GrabResult mRes;
    private List<GrabResult.Data> mData;
    private LinearLayoutManager layoutManager;
    private RecyclerViewListAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int currentpage = 1;

    public void setType(int mType) {
        this.mType = mType;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //布局文件中只有一个居中的TextView
        viewContent = inflater.inflate(R.layout.fragment_content, container, false);
        RecyclerView grab_recycler = (RecyclerView) viewContent.findViewById(R.id.grab_recycler);
        //        loadListDate(false, true, grab_recycler, R.layout.fragment_content_item, mData);
        swipeRefreshLayout = viewContent.findViewById(R.id.swiperefreshlayout_grab);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        grab_recycler.setLayoutManager(layoutManager);
        //添加Android自带的分割线
        grab_recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        sharePh = new SharedPreferencesHelper(getActivity());
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mApi = GrabApi.getApi();
        mAdapter = new RecyclerViewListAdapter(getActivity());
        grab_recycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                //跳转到任务详情
                Intent intent = new Intent(getActivity(), TaskDetialActivity.class);
                intent.putExtra("id", mData.get(postion).getId());
                startActivity(intent);
            }

            @Override
            public void onItemClick(int postion) {
                //抢单

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                setMethod();
            }
        });

        grab_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        setMethod();
        return viewContent;
    }

    public void loadMore() {
        mAdapter.setLoadStatus(RecyclerViewListAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        setMethod();
    }

    private void setMethod() {
        if (mType == 0) {
            //全部
            createSingle("");
        } else if (mType == 1) {
            //跟拍
            createSingle("gp");
        } else if (mType == 2) {
            //摄像
            createSingle("sx");
        } else if (mType == 3) {
            //化妆
            createSingle("gz");
        }
    }

    private int SumPage = 1;
    //    动态咨询 加载数据
    private Callback<GrabResult> callback = new Callback<GrabResult>() {
        @Override
        public void onResponse(Call<GrabResult> call, Response<GrabResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    mData = mRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        swipeRefreshLayout.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    //总弄页数
                    SumPage = mRes.getPi().getTotalPage();
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(RecyclerViewListAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(RecyclerViewListAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<GrabResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(String packageType) {
        Call<GrabResult> call = mApi.getService().createCommitPage(token, uid, packageType, currentpage);
        call.enqueue(callback);
    }


    /***************选择抢单******************/
    private Callback<ModelBeanData> callbackgabs = new Callback<ModelBeanData>() {
        @Override
        public void onResponse(Call<ModelBeanData> call, Response<ModelBeanData> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                ModelBeanData taskRes = response.body();
                if ("00".equals(taskRes.getCode())) {
                    ToastUtil.showShort(getActivity(), taskRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ModelBeanData> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleGrabs(int taskId, int Grabsid) {
        Call<ModelBeanData> call = mApi.getService().createCommitGrabsId(token, taskId, Grabsid);
        call.enqueue(callbackgabs);
    }

    //RecyclerView实现ListView效果，实际就是布局管理器参数改为GridLayoutManager
    private void loadListDate(Boolean inversion, Boolean orientation,
                              final RecyclerView recyclerViewGrid, int layoutId, List<String> mHotScenicData) {

//创建适配器adapter对象 参数1.上下文 2.数据加载集合
        RecyclerViewListAdapter recyclerViewGridAdapter = new RecyclerViewListAdapter(getActivity());
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
        recyclerViewGridAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
//                ToastUtil.showShort(getActivity(), "Grid 1 view:" + postion);
                Intent intent = new Intent(getActivity(), TaskDetialActivity.class);

                startActivity(intent);
            }

            @Override
            public void onItemClick(int postion) {
                ToastUtil.showShort(getActivity(), "Grid 1 postion:" + postion);

            }
        });
    }
}