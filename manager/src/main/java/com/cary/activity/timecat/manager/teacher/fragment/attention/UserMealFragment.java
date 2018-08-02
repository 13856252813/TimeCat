package com.cary.activity.timecat.manager.teacher.fragment.attention;

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
import com.cary.activity.timecat.manager.teacher.fragment.attention.user.FriendListApi;
import com.cary.activity.timecat.manager.teacher.fragment.attention.user.FriendListResult;
import com.cary.activity.timecat.manager.teacher.fragment.attention.user.SelectFriendPayAdapter;
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
 * 用户
 */
public class UserMealFragment extends Fragment {

    private static String TAG = UserMealFragment.class.getSimpleName();

    @BindView(R.id.recycler_attention_user)
    RecyclerView recyclerAttentionUser;
    Unbinder unbinder;
    @BindView(R.id.swiperefreshlayout_user)
    SwipeRefreshLayout swiperefreshlayoutUser;
    private SelectFriendPayAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;

    private FriendListApi mFriendApi;
    private FriendListResult mFriendRes;
    private List<FriendListResult.Data> mData;

    private SharedPreferencesHelper sharePh;
    private String token;
    private String uid;
    private int currentpage = 1;

    public UserMealFragment() {
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
        View view = inflater.inflate(R.layout.fragment_attention_store, container, false);
        unbinder = ButterKnife.bind(this, view);

        sharePh = new SharedPreferencesHelper(getActivity());
        token = (String) sharePh.getSharedPreference("token", "");
        uid = ((int) sharePh.getSharedPreference("id", 0) + "");
        mFriendApi = FriendListApi.getApi();
        sharePh = new SharedPreferencesHelper(getActivity());
        token = (String) sharePh.getSharedPreference("token", "");
        uid = ((int) sharePh.getSharedPreference("id", 0) + "");


        //在加载数据之前配置
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerAttentionUser.setLayoutManager(linearLayoutManager);
        //创建一个适配器
        mAdapter = new SelectFriendPayAdapter(getActivity());
        recyclerAttentionUser.setAdapter(mAdapter);

        recyclerAttentionUser.setItemAnimator(new DefaultItemAnimator());
//        mAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int postion) {
//                Intent intent = new Intent(SelectFriendPayActivity.this, SetMealDetialActivity.class);
//                intent.putExtra("id", mData.get(postion).getId());
//                startActivity(intent);
//            }
//        });

        swiperefreshlayoutUser.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingleFriend();
            }
        });

        recyclerAttentionUser.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        createSingleFriend();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public void loadMore() {
        mAdapter.setLoadStatus(SelectFriendPayAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingleFriend();
    }

    private int SumPage = 1;
    //    动态咨询 加载数据
    private Callback<FriendListResult> callbackFriend = new Callback<FriendListResult>() {
        @Override
        public void onResponse(Call<FriendListResult> call, Response<FriendListResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mFriendRes = response.body();
                if ("00".equals(mFriendRes.getCode())) {
                    mData = mFriendRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        swiperefreshlayoutUser.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    //总弄页数
                    SumPage = mFriendRes.getPi().getTotalPage();
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(SelectFriendPayAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(SelectFriendPayAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mFriendRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<FriendListResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleFriend() {
        Call<FriendListResult> call = mFriendApi.getService().createCommitPage(token, currentpage);
        call.enqueue(callbackFriend);
    }

}
