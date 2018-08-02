package com.cary.activity.timecat.fragment.index.selectsetmeal.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.selectsetmeal.SelectSetMealRecyclerAdapter;
import com.cary.activity.timecat.fragment.index.selectsetmeal.SetMealApi;
import com.cary.activity.timecat.fragment.index.selectsetmeal.SetMealResult;
import com.cary.activity.timecat.fragment.index.setmealdetial.SetMealDetialActivity;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cary on 2018/4/10.
 */

@SuppressLint("ValidFragment")
public class TabFragment extends Fragment {
    private static final String TAG = TabFragment.class.getSimpleName();

    private int position = 0;
    private SelectSetMealRecyclerAdapter mAdapter;

    private SetMealApi mApi;
    private SetMealResult mMealRes;
    private List<SetMealResult.Data> mData;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;

    private SharedPreferencesHelper sharePh;
    private String token;
    private String uid;
    private int currentpage = 1;
    private String IdStr;
    private String typeList;
    private String storeId;
//    public static Fragment newInstance(int pos) {
//        position = pos;
//        TabFragment fragment = new TabFragment();
//        return fragment;
//    }

    public TabFragment(String IdStr,int pos,String typeList) {
        this.position  = pos;
        this.IdStr = IdStr;
        this.typeList = typeList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.select_set_meal_all, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
//
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
        uid =  ((int)sharePh.getSharedPreference("id",0)+"");
        storeId = sharePh.getSharedPreference("storeId",0)+"";

        mApi = SetMealApi.getApi();
        mSwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swiperefreshlayout_setmeal);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SelectSetMealRecyclerAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(getActivity(), SetMealDetialActivity.class);
                intent.putExtra("id", mData.get(postion).getId());
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                setMethod();
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
        setMethod();
        return rootView;
    }

    public void loadMore() {
        mAdapter.setLoadStatus(SelectSetMealRecyclerAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        setMethod();
    }

    private void setMethod(){
        if(position==0){
            //全部
            createSingleMeal("");
        }else if(position==1){
            //私人订制
            createSingleMeal("0");
        }else if(position==2){
            //套餐
            createSingleMeal("1");
        }
    }
    private int SumPage = 1;
    //    动态咨询 加载数据
    private Callback<SetMealResult> callbackMeal = new Callback<SetMealResult>() {
        @Override
        public void onResponse(Call<SetMealResult> call, Response<SetMealResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mMealRes = response.body();
                if ("00".equals(mMealRes.getCode())) {
                    mData = mMealRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        mSwipeRefreshLayout.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    //总弄页数
                    SumPage = mMealRes.getPi().getTotalPage();
                    if (SumPage< 2) {
                        mAdapter.setLoadStatus(SelectSetMealRecyclerAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(SelectSetMealRecyclerAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mMealRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<SetMealResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleMeal(String packageType) {

        Call<SetMealResult> call = mApi.getService().createCommitPage(token,storeId,packageType,currentpage);
        call.enqueue(callbackMeal);
    }

}