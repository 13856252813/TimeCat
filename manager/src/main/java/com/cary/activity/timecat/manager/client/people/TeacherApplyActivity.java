package com.cary.activity.timecat.manager.client.people;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.client.myorder.confirmorder.teacher.TeacherDetialActivity;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherApplyActivity extends AppCompatActivity {
    private static final String TAG = TeacherApplyActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recycler_teacher_apply)
    RecyclerView recyclerTeacherApply;
    @BindView(R.id.swiperefreshlayout_teacher)
    SwipeRefreshLayout swiperefreshlayoutTeacher;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int storeId;
    private int currentpage = 1;

    private TeacherApplyApi teahListApi;
    private TeacherApplyResult mteahListRes;
    private List<TeacherApplyResult.Data> mteahListData;
    private TeacherApplyAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private TeacherApplyResult.Data mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_teacher_apply);
        ButterKnife.bind(this);

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
        titleText.setText("老师入驻申请");

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);
        storeId = (int) sharePh.getSharedPreference("storeId", 0);
        teahListApi = TeacherApplyApi.getApi();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerTeacherApply.setLayoutManager(mLayoutManager);
        recyclerTeacherApply.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new TeacherApplyAdapter(this);
        recyclerTeacherApply.setAdapter(mAdapter);

        swiperefreshlayoutTeacher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingle();
            }
        });

        recyclerTeacherApply.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        createSingle();
        mAdapter.setItemClickListener(new TeacherApplyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(TeacherApplyActivity.this, TeacherDetialActivity.class);
                intent.putExtra("id", mteahListData.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onCheckClick(int position) {
                //获取选中后的数据
                mData = mteahListData.get(position);
            }
        });


    }

    public void loadMore() {
        mAdapter.setLoadStatus(TeacherApplyAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingle();
    }

    private int SumPage = 1;
    private Callback<TeacherApplyResult> callbackPage = new Callback<TeacherApplyResult>() {
        @Override
        public void onResponse(Call<TeacherApplyResult> call, Response<TeacherApplyResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mteahListRes = response.body();
                if ("00".equals(mteahListRes.getCode())) {
                    mteahListData = mteahListRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mteahListData);
                        swiperefreshlayoutTeacher.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mteahListData);
                    }
                    SumPage = mteahListRes.getPi().getTotalPage();
                    //总弄页数
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(TeacherApplyAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(TeacherApplyAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(TeacherApplyActivity.this, mteahListRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<TeacherApplyResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<TeacherApplyResult> call = teahListApi.getService().createCommitPage(token, storeId, currentpage);
        call.enqueue(callbackPage);
    }
}
