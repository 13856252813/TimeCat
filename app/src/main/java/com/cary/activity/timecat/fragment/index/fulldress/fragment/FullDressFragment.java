package com.cary.activity.timecat.fragment.index.fulldress.fragment;

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
import com.cary.activity.timecat.fragment.index.fulldress.FullDessGridAdapter;
import com.cary.activity.timecat.fragment.index.fulldress.FullDressTabActivity;
import com.cary.activity.timecat.fragment.index.fulldress.FullDressTabApi;
import com.cary.activity.timecat.fragment.index.fulldress.FullDressTabResult;
import com.cary.activity.timecat.fragment.index.fulldress.detial.FullDressDetialActivity;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cary on 2018/4/10.
 * 衣服分类
 */

public class FullDressFragment extends Fragment {
    private static final String TAG = FullDressFragment.class.getSimpleName();

    private FullDressTabResult.Data data;//获取分类的id
    private FullDessGridAdapter mAdapter;
    private FullDressTabApi mApi;
    private FullDressColtheResult mFDColthRes;
    private List<FullDressColtheResult.Data> mData;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private SharedPreferencesHelper sharePh;
    private String token;
    private String uid;
    private int currentpage = 1;
    private String storeId;
    private int sex;//0男 1女
    private static String type = "0";//0售卖 1共享

    public static FullDressFragment newInstance(FullDressTabResult.Data data, String type) {
        FullDressFragment fragment = new FullDressFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",data);
        bundle.putString("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        sharePh = new SharedPreferencesHelper(getActivity());


        data= (FullDressTabResult.Data) getArguments().getSerializable("data");
        type=getArguments().getString("type");
        sex= (int) sharePh.getSharedPreference("sex",1);
        Log.e("fl","------sex2222222222222222222222:"+sex+"----data:"+data.getName());

        View rootView = inflater.inflate(R.layout.select_set_meal_all, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setReverseLayout(false);
        gridLayoutManager.setOrientation(GridLayout.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(5));

        token = (String) sharePh.getSharedPreference("token", "");
        uid = ((int) sharePh.getSharedPreference("id", 0) + "");
        storeId = sharePh.getSharedPreference("storeId", 0) + "";

        mApi = FullDressTabApi.getApi();
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefreshlayout_setmeal);
        mAdapter = new FullDessGridAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if(((FullDressTabActivity)getActivity()).isOrder){
                    Intent intent=new Intent();
                    intent.putExtra("dress",mData.get(postion));
                    getActivity().setResult(((FullDressTabActivity) getActivity()).RESULT_OK,intent);
                    getActivity().finish();
                }else {
                    Intent intent = new Intent(getActivity(), FullDressDetialActivity.class);
                    intent.putExtra("id", mData.get(postion).getId());
                    intent.putExtra("flagTag", type);
                    startActivity(intent);
                }

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
        mAdapter.setLoadStatus(FullDessGridAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingleColoth(data.getId());
    }

    private int SumPage = 1;
    //    服装列表 加载数据
    private Callback<FullDressColtheResult> callbackColth = new Callback<FullDressColtheResult>() {
        @Override
        public void onResponse(Call<FullDressColtheResult> call, Response<FullDressColtheResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mFDColthRes = response.body();
                if ("00".equals(mFDColthRes.getCode())) {
                    mData = mFDColthRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        mSwipeRefreshLayout.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    //总弄页数
                    SumPage = mFDColthRes.getPi().getTotalPage();
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(FullDessGridAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(FullDessGridAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mFDColthRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<FullDressColtheResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleColoth(int catagory) {
        Call<FullDressColtheResult> call = mApi.getService().createCommitColothPage(token,
                catagory + "", this.sex + "", type, currentpage);
        call.enqueue(callbackColth);
    }

}