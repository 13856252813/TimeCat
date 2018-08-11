package com.cary.activity.timecat.fragment.person.attention;

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

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.person.attention.news.NewsAttentionAdapter;
import com.cary.activity.timecat.fragment.person.attention.news.NewsAttentionApi;
import com.cary.activity.timecat.fragment.person.attention.news.NewsAttentionDetialActivity;
import com.cary.activity.timecat.fragment.person.attention.news.NewsAttentionResult;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 资讯 关注
 */
public class NewsMealFragment extends Fragment {

    private static final String TAG = NewsMealFragment.class.getSimpleName();

    @BindView(R.id.recycler_attention_news)
    RecyclerView recyclerAttentionNews;
    @BindView(R.id.swiperefreshlayout_attention)
    SwipeRefreshLayout swiperefreshlayoutAttention;
    Unbinder unbinder;

    private NewsAttentionApi mNewsApi;
    private NewsAttentionResult mNewsComRes;
    private List<NewsAttentionResult.Data> mNewsData;
    private LinearLayoutManager mLayoutManager;
    private NewsAttentionAdapter mAdapter;

    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int currentpage = 1;

    public NewsMealFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attention_news, container, false);

        unbinder = ButterKnife.bind(this, view);

        mNewsApi = NewsAttentionApi.getApi();
        sharePh = new SharedPreferencesHelper(getActivity());
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerAttentionNews.setLayoutManager(mLayoutManager);
        recyclerAttentionNews.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAttentionAdapter(getActivity());
        recyclerAttentionNews.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(getActivity(), NewsAttentionDetialActivity.class);
                intent.putExtra("id", mNewsData.get(postion).getId());
                startActivity(intent);
            }
        });

        swiperefreshlayoutAttention.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingleNews();
            }
        });

        recyclerAttentionNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void loadMore() {
        mAdapter.setLoadStatus(NewsAttentionAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingleNews();
    }

    //    动态咨询 加载数据
    private Callback<NewsAttentionResult> callbackNews = new Callback<NewsAttentionResult>() {
        @Override
        public void onResponse(Call<NewsAttentionResult> call, Response<NewsAttentionResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!"+currentpage);
                Log.i(TAG, "---" + response.body().toString());
                mNewsComRes = response.body();
                if ("00".equals(mNewsComRes.getCode())) {
                    mNewsData = mNewsComRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mNewsData);
                        Log.i(TAG, "size!!!"+mNewsData.size());
                        swiperefreshlayoutAttention.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mNewsData);
                    }
                    //总弄页数
                    if (mNewsComRes.getPi().getTotalPage() < 2) {
                        mAdapter.setLoadStatus(NewsAttentionAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(NewsAttentionAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mNewsComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NewsAttentionResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleNews() {
        Call<NewsAttentionResult> call = mNewsApi.getService().createCommitPage(token, currentpage, uid);
        call.enqueue(callbackNews);
    }
}
