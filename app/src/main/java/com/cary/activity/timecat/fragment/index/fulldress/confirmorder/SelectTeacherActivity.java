package com.cary.activity.timecat.fragment.index.fulldress.confirmorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.teacher.TeacherDetialActivity;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*****
 * 选择老师
 */
public class SelectTeacherActivity extends BaseActivity {

    private static final String TAG = SelectTeacherActivity.class.getSimpleName();

    String teacherFlag;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recycler_select_teacher)
    RecyclerView recyclerSelectTeacher;
    @BindView(R.id.tv_select_teacher_price)
    TextView tvSelectTeacherPrice;
    @BindView(R.id.tv_select_teacher_price_text)
    TextView tvSelectTeacherPriceText;
    @BindView(R.id.tv_select_teacher_price_two)
    TextView tvSelectTeacherPriceTwo;
    @BindView(R.id.btn_select_teacher)
    Button btnSelectTeacher;
    @BindView(R.id.swiperefreshlayout_teacher)
    SwipeRefreshLayout swiperefreshlayoutTeacher;

    private TeacherApi teahListApi;
    private TeacherListResult mteahListRes;
    private List<TeacherListResult.Data> mteahListData;
    private SelectTeacherAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private TeacherListResult.Data mData;

    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int storeId;
    private int currentpage = 1;
    private String teacherType="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_select_teacher);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        teacherFlag = getIntent().getStringExtra("teacherflag");

        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        if ("camcer".equals(teacherFlag)) {
            titleText.setText("选择摄影师");
            teacherType = "";
        }
        if ("dresser".equals(teacherFlag)) {
            titleText.setText("选择化妆师");
            teacherType="";
        }
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSelectTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 创建一个linearlayoutmaneger对象，并将他设置到recyclerview当中。layoutmanager用于指定
         * recyclerview的布局方式，这里是线性布局的意思。可以实现和listview类似的效果。
         */
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerSelectTeacher.setLayoutManager(linearLayoutManager);
//        selectAdapter = new SelectTeacherAdapter(this);
//        //设置item间距，30dp
//        recyclerSelectTeacher.addItemDecoration(new SpaceItemDecoration(20));
//        recyclerSelectTeacher.setAdapter(selectAdapter);
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);
        storeId = (int) sharePh.getSharedPreference("storeId", 0);

        teahListApi = TeacherApi.getApi();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerSelectTeacher.setLayoutManager(mLayoutManager);
        recyclerSelectTeacher.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SelectTeacherAdapter(this);
        recyclerSelectTeacher.setAdapter(mAdapter);

        swiperefreshlayoutTeacher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                createSingle(teacherType);
            }
        });

        recyclerSelectTeacher.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        createSingle(teacherType);
        mAdapter.setItemClickListener(new SelectTeacherAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SelectTeacherActivity.this, TeacherDetialActivity.class);
                intent.putExtra("id", mteahListData.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onCheckClick(int position) {
                //获取选中后的数据
                mData = mteahListData.get(position);
                tvSelectTeacherPrice.setText("¥"+mData.getWebUser().getPhotoAmount());
                tvSelectTeacherPriceTwo.setText("¥"+mData.getWebUser().getExtraAmount());
            }
        });


    }

    @Override
    public int getLayout() {
        return R.layout.activity_select_teacher;
    }

    public void loadMore() {
        mAdapter.setLoadStatus(SelectTeacherAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        createSingle(teacherType);
    }

    private int SumPage = 1;
    private Callback<TeacherListResult> callbackPage = new Callback<TeacherListResult>() {
        @Override
        public void onResponse(Call<TeacherListResult> call, Response<TeacherListResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mteahListRes = response.body();
                if ("00".equals(mteahListRes.getCode())) {
                    mteahListData = mteahListRes.getData();
//                    loadListDate(false, true, recyclerViewSelectStore, R.layout.activity_select_store_item_layout, mSStoreData);
//                    recyclerViewSelectStore.setNestedScrollingEnabled(false);
                    if (currentpage == 1) {
                        mAdapter.reSetData(mteahListData);
                        swiperefreshlayoutTeacher.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mteahListData);
                    }
                    SumPage = mteahListRes.getPi().getTotalPage();
                    //总弄页数
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(SelectTeacherAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(SelectTeacherAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(SelectTeacherActivity.this, mteahListRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<TeacherListResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(String teacherType) {
        Call<TeacherListResult> call = teahListApi.getService().createCommitPage(token, storeId, teacherType, currentpage);
        call.enqueue(callbackPage);
    }
}
