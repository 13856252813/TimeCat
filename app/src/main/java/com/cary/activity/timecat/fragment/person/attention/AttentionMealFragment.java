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
import com.cary.activity.timecat.fragment.index.setmealdetial.SetMealDetialActivity;
import com.cary.activity.timecat.fragment.person.attention.meal.AttentionMealAdapter;
import com.cary.activity.timecat.fragment.person.attention.meal.MealAttentionApi;
import com.cary.activity.timecat.fragment.person.attention.meal.MealAttentionResult;
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
 * 套餐
 */
public class AttentionMealFragment extends Fragment {
    private static String TAG = AttentionMealFragment.class.getSimpleName();
    @BindView(R.id.recycler_meal)
    RecyclerView recyclerMeal;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayoutAttention;
    Unbinder unbinder;

    private AttentionMealAdapter mAdapter;
    private MealAttentionApi mApi;
    private MealAttentionResult mComRes;
    private List<MealAttentionResult.Data> mData;
    private LinearLayoutManager mLayoutManager;

    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int currentpage = 1;

    public AttentionMealFragment() {
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
        View view = inflater.inflate(R.layout.fragment_attention_meal, container, false);
        unbinder = ButterKnife.bind(this, view);

        mApi = MealAttentionApi.getApi();
        sharePh = new SharedPreferencesHelper(getActivity());
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerMeal.setLayoutManager(mLayoutManager);
        recyclerMeal.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new AttentionMealAdapter(getActivity());
        recyclerMeal.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(getActivity(), SetMealDetialActivity.class);
                intent.putExtra("id", mData.get(postion).getId());
                startActivity(intent);
            }
        });

        swiperefreshlayoutAttention.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                createSingle(1);
            }
        });

        recyclerMeal.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override//滚动状态变化时回调
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
                // mLastVisibleItemPosition=mLayoutManager.findLastVisibleItemPosition();
                //滑动停止并且底部不可滚动（即滑动到底部） 加载更多
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !(ViewCompat.canScrollVertically(recyclerView, 1))) {
                    loadMore(currentpage++);
                }
            }

            @Override//滚动时回调
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        createSingle(currentpage);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void loadMore(int currentpage) {
        mAdapter.setLoadStatus(AttentionMealAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        createSingle(currentpage);
    }

    private int page = 1;
    // 加载数据
    private Callback<MealAttentionResult> callback = new Callback<MealAttentionResult>() {
        @Override
        public void onResponse(Call<MealAttentionResult> call, Response<MealAttentionResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mComRes = response.body();
                if ("00".equals(mComRes.getCode())) {
                    mData = mComRes.getData();
                    if (page == 1) {
                        mAdapter.reSetData(mData);
                        swiperefreshlayoutAttention.setRefreshing(false);
                    } else if (page > 1) {
                        mAdapter.addAll(mData);
                    }
                    //总弄页数
                    if (mComRes.getPi().getTotalPage() > 2) {
                        mAdapter.setLoadStatus(AttentionMealAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(AttentionMealAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<MealAttentionResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(int currentpage) {
        page = currentpage;
        Call<MealAttentionResult> call = mApi.getService().createCommitPage(token, currentpage, uid);
        call.enqueue(callback);
    }
}
