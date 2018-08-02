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
import com.cary.activity.timecat.manager.teacher.fragment.attention.teacher.TeacherAttentionApi;
import com.cary.activity.timecat.manager.teacher.fragment.attention.teacher.TeacherAttentionResult;
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
 * 老师
 */
public class TeacherMealFragment extends Fragment {
    private static String TAG = TeacherMealFragment.class.getSimpleName();
    @BindView(R.id.recycler_attention_teacher)
    RecyclerView recyclerAttentionTeacher;
    @BindView(R.id.swiperefreshlayout_teacher)
    SwipeRefreshLayout swiperefreshlayoutTeacher;
    Unbinder unbinder;

    private AttentionTeacherAdapter mAdapter;
    private TeacherAttentionApi mApi;
    private TeacherAttentionResult mComRes;
    private List<TeacherAttentionResult.Data> mData;
    private LinearLayoutManager mLayoutManager;

    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int currentpage = 1;

    public TeacherMealFragment() {
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
        View view = inflater.inflate(R.layout.fragment_attention_teacher, container, false);
        unbinder = ButterKnife.bind(this, view);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerAttentionTeacher.setLayoutManager(linearLayoutManager);
        mApi = TeacherAttentionApi.getApi();
        sharePh = new SharedPreferencesHelper(getActivity());
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerAttentionTeacher.setLayoutManager(mLayoutManager);
        recyclerAttentionTeacher.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new AttentionTeacherAdapter(getActivity());
        recyclerAttentionTeacher.setAdapter(mAdapter);
        mAdapter.setItemClickListener(new AttentionTeacherAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Intent intent = new Intent(getActivity(), TeacherDetialActivity.class);
//                intent.putExtra("id", mData.get(position).getId());
//                startActivity(intent);
            }
        });

        swiperefreshlayoutTeacher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingle();
            }
        });

        recyclerAttentionTeacher.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        createSingle();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void loadMore() {
        mAdapter.setLoadStatus(AttentionTeacherAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingle();
    }

    // 加载数据
    private Callback<TeacherAttentionResult> callback = new Callback<TeacherAttentionResult>() {
        @Override
        public void onResponse(Call<TeacherAttentionResult> call, Response<TeacherAttentionResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mComRes = response.body();
                if ("00".equals(mComRes.getCode())) {
                    mData = mComRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        swiperefreshlayoutTeacher.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    //总弄页数
                    if (mComRes.getPi().getTotalPage() < 2) {
                        mAdapter.setLoadStatus(AttentionTeacherAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(AttentionTeacherAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<TeacherAttentionResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<TeacherAttentionResult> call = mApi.getService().createCommitPage(token, currentpage, uid);
        call.enqueue(callback);
    }
}
