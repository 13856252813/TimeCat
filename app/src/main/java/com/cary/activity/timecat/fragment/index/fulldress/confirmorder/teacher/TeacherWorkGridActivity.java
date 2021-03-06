package com.cary.activity.timecat.fragment.index.fulldress.confirmorder.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.FullDessGridAdapter;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.TeacherApi;
import com.cary.activity.timecat.fragment.index.fulldress.detial.FullDressDetialActivity;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 老师作品
 */
public class TeacherWorkGridActivity extends BaseActivity {
    private static final String TAG = TeacherWorkGridActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recycler_teacher_work)
    RecyclerView recyclerTeacherWork;
    @BindView(R.id.swiperefreshlayout_teacher_work)
    SwipeRefreshLayout swiperefreshlayoutTeacherWork;
    private TeacherApi teahApi;
    private TeacherDetialResult mteahRes;

    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int id;
    private int currentpage = 1;

    private TeacherWorkResult mWorkRes;
    private List<TeacherWorkResult.Data> mWorkList;
    private TeacherWorkAppcitionAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_teacher_work_grid);
        ButterKnife.bind(this);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        titleText.setText("作品集");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        id = getIntent().getIntExtra("id", 0);

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        teahApi = TeacherApi.getApi();
//        loadListDate(false, true, recyclerTeacherWork, mGridList);
        mAdapter = new TeacherWorkAppcitionAdapter(this);
        recyclerTeacherWork.setAdapter(mAdapter);
        mAdapter.setItemClickListener(new TeacherWorkAppcitionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳转到详情
//                Intent intent = new Intent(TeacherDetialActivity.this, TeacherWorkGridActivity.class);
//                intent.putExtra("id", mWorkList.get(position).getId());
//                startActivity(intent);
            }
        });

        swiperefreshlayoutTeacherWork.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingleWork();
            }
        });

        recyclerTeacherWork.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        createSingleWork();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_teacher_work_grid;
    }
    public void loadMore() {
        mAdapter.setLoadStatus(TeacherWorkAppcitionAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingleWork();
    }

    private int SumPage = 1;
    //    作品集列表 加载数据
    private Callback<TeacherWorkResult> callbackrework = new Callback<TeacherWorkResult>() {
        @Override
        public void onResponse(Call<TeacherWorkResult> call, Response<TeacherWorkResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mWorkRes = response.body();
                if ("00".equals(mWorkRes.getCode())) {
                    mWorkList = mWorkRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mWorkList);
                        swiperefreshlayoutTeacherWork.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mWorkList);
                    }
                    //总弄页数
                    SumPage = mWorkRes.getPi().getTotalPage();
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(TeacherWorkAppcitionAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(TeacherWorkAppcitionAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(TeacherWorkGridActivity.this, mWorkRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<TeacherWorkResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleWork( ) {
        Call<TeacherWorkResult> call = teahApi.getService().createCommitTeachWorkPage(token, id,currentpage);
        call.enqueue(callbackrework);
    }

    //此处是底部的gridview 的列表
    //RecyclerView实现ListView效果，实际就是布局管理器参数改为GridLayoutManager
    private void loadListDate(Boolean inversion, Boolean orientation,
                              final RecyclerView recyclerViewGrid, List<String> mGridList) {

//创建适配器adapter对象 参数1.上下文 2.数据加载集合
        FullDessGridAdapter recyclerViewGridAdapter = new FullDessGridAdapter(this);
//设置适配器
        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
//布局管理器对象 参数1.上下文 2.规定显示的行数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
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
                Intent intent = new Intent(TeacherWorkGridActivity.this, FullDressDetialActivity.class);
                intent.putExtra("id", "123");
                intent.putExtra("flagTag", "1");
                startActivity(intent);

            }
        });
    }
}
