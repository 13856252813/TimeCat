package com.cary.activity.timecat.fragment.index.news;

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
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 新闻列表 fragment
 */
@SuppressLint("ValidFragment")
public class NewsListFragment extends Fragment {
    private static final String TAG = NewsListFragment.class.getSimpleName();
    private int position = 0;
    private NewsListAdapter mAdapter;

    private NewsApi mNewsApi;
    private NewsCommitResult mNewsComRes;
    private List<NewsCommitResult.Data> mNewsData;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;

    private SharedPreferencesHelper sharePh;
    private String token;
    private int currentpage = 1;

    public NewsListFragment(int pos) {
        this.position = pos;
        Log.v(TAG, "position:" + position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;//inflater.inflate(R.layout.fragment_news_list, container, false);
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.fragment_news_list, null);
            initView(rootView);// 控件初始化
        }
        return rootView;
    }

    private void initView(View rootView) {// 控件初始化
        TextView textViewActivityFragment = rootView.findViewById(R.id.textViewActivityFragment);
        textViewActivityFragment.setText("position:" + position);
        sharePh = new SharedPreferencesHelper(getActivity());
        token = (String) sharePh.getSharedPreference("token", "");
        mNewsApi = NewsApi.getApi();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewNewsList);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefreshlayout_news);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsListAdapter(getActivity(), position);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(getActivity(), NewsDetialActivity.class);
                intent.putExtra("id", mNewsData.get(postion).getId());
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingleNews();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        createSingleNews();
    }

    public void loadMore() {
        mAdapter.setLoadStatus(NewsListAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingleNews();
    }

    //    动态咨询 加载数据
    private Callback<NewsCommitResult> callbackNews = new Callback<NewsCommitResult>() {
        @Override
        public void onResponse(Call<NewsCommitResult> call, Response<NewsCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mNewsComRes = response.body();
                if ("00".equals(mNewsComRes.getCode())) {
                    mNewsData = mNewsComRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mNewsData);
                        mSwipeRefreshLayout.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mNewsData);
                    }
                    //总弄页数
                    if (mNewsComRes.getPi().getTotalPage() < 2) {
                        mAdapter.setLoadStatus(NewsListAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(NewsListAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mNewsComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NewsCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleNews() {
        Call<NewsCommitResult> call = mNewsApi.getService().createCommitPage(token, currentpage, position);
        call.enqueue(callbackNews);
    }
}
