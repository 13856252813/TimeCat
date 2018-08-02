package com.cary.activity.timecat.manager.client.question;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;
import com.cary.activity.timecat.manager.adapter.SpaceItemDecoration;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 投诉建议
 */
@SuppressLint("Registered")
public class QuestionActivity extends AppCompatActivity {

    private static final String TAG = QuestionActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_question_all_text)
    TextView tvQuestionAllText;
    @BindView(R.id.tv_question_all_line)
    TextView tvQuestionAllLine;
    @BindView(R.id.ll_question_all_list)
    LinearLayout llQuestionAllList;
    @BindView(R.id.tv_question_normal_text)
    TextView tvQuestionNormalText;
    @BindView(R.id.tv_question_normal_line)
    TextView tvQuestionNormalLine;
    @BindView(R.id.ll_question_normal_list)
    LinearLayout llQuestionNormalList;
    @BindView(R.id.tv_question_service_text)
    TextView tvQuestionServiceText;
    @BindView(R.id.tv_question_service_line)
    TextView tvQuestionServiceLine;
    @BindView(R.id.ll_question_service_list)
    LinearLayout llQuestionServiceList;
    @BindView(R.id.tv_question_teacher_text)
    TextView tvQuestionTeacherText;
    @BindView(R.id.tv_question_teacher_line)
    TextView tvQuestionTeacherLine;
    @BindView(R.id.ll_question_teacher_list)
    LinearLayout llQuestionTeacherList;
    @BindView(R.id.recycler_question_list)
    RecyclerView recyclerQuestionList;
    @BindView(R.id.swiperefreshlayout_question)
    SwipeRefreshLayout swiperefreshlayoutQuestion;

    private QuestionAdapter mAdapter;
    private QuestionApi mApi;
    private QuestionResult mRes;
    private List<QuestionResult.Data> mData;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int currentpage = 1;
    private int uid;
    private int flag = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        titleText.setText("投诉建议处理");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
        titleTextRight.setText("历史记录");
        titleTextRight.setVisibility(View.VISIBLE);
        titleTextRight.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        titleTextRight.setTextColor(getResources().getColor(R.color.color_three));

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mApi = QuestionApi.getApi();
        recyclerQuestionList.addItemDecoration(new SpaceItemDecoration(20));
        //在加载数据之前配置
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerQuestionList.setLayoutManager(linearLayoutManager);
        //创建一个适配器
        mAdapter = new QuestionAdapter(this);
        recyclerQuestionList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(QuestionActivity.this, QuestionDetialActivity.class);
                intent.putExtra("id", mData.get(postion).getId());
                startActivity(intent);
            }

            @Override
            public void onItemClick(int postion) {

            }
        });

        swiperefreshlayoutQuestion.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage = 1;
                setMethod(flag);
            }
        });

        recyclerQuestionList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        setMethod(flag);
    }

    public void loadMore() {
        mAdapter.setLoadStatus(QuestionAdapter.LoadStatus.LOADING_MORE);
        mAdapter.refresh();
        currentpage++;
        setMethod(flag);
    }

    private void setMethod(int flag) {
        if (flag == -1) {
            createSingle();
        } else if (flag == 0 ){
            createSingle("COMMON");
        }else if(flag == 1) {
            createSingle("SERVICE");
        } else if (flag == 2) {
            createSingle("TEACHER");
        }
    }

    @OnClick({R.id.title_back, R.id.ll_question_all_list, R.id.ll_question_normal_list, R.id.ll_question_service_list, R.id.ll_question_teacher_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_question_all_list:
                flag = -1;
                setTextImageView(tvQuestionAllText,tvQuestionAllLine,true);
                setTextImageView(tvQuestionNormalText,tvQuestionNormalLine,false);
                setTextImageView(tvQuestionServiceText,tvQuestionServiceLine,false);
                setTextImageView(tvQuestionTeacherText,tvQuestionTeacherLine,false);
                setMethod(flag);
                break;
            case R.id.ll_question_normal_list:
                flag = 0;
                setTextImageView(tvQuestionAllText,tvQuestionAllLine,false);
                setTextImageView(tvQuestionNormalText,tvQuestionNormalLine,true);
                setTextImageView(tvQuestionServiceText,tvQuestionServiceLine,false);
                setTextImageView(tvQuestionTeacherText,tvQuestionTeacherLine,false);
                setMethod(flag);
                break;
            case R.id.ll_question_service_list:
                flag = 1;
                setTextImageView(tvQuestionAllText,tvQuestionAllLine,false);
                setTextImageView(tvQuestionNormalText,tvQuestionNormalLine,false);
                setTextImageView(tvQuestionServiceText,tvQuestionServiceLine,true);
                setTextImageView(tvQuestionTeacherText,tvQuestionTeacherLine,false);
                setMethod(flag);
                break;
            case R.id.ll_question_teacher_list:
                flag = 2;
                setTextImageView(tvQuestionAllText,tvQuestionAllLine,false);
                setTextImageView(tvQuestionNormalText,tvQuestionNormalLine,false);
                setTextImageView(tvQuestionServiceText,tvQuestionServiceLine,false);
                setTextImageView(tvQuestionTeacherText,tvQuestionTeacherLine,true);
                setMethod(flag);
                break;
        }
    }
    private void setTextImageView(TextView tv, TextView iv, boolean tiflag) {
        if (tiflag) {
            tv.setTextColor(getResources().getColor(R.color.login_color_btn));
            iv.setBackgroundColor(getResources().getColor(R.color.login_color_btn));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_three));
            iv.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
    }
    private int SumPage = 1;
    //加载数据
    private Callback<QuestionResult> callback = new Callback<QuestionResult>() {
        @Override
        public void onResponse(Call<QuestionResult> call, Response<QuestionResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    mData = mRes.getData();
                    if (currentpage == 1) {
                        mAdapter.reSetData(mData);
                        swiperefreshlayoutQuestion.setRefreshing(false);
                    } else if (currentpage > 1) {
                        mAdapter.addAll(mData);
                    }
                    SumPage = mRes.getPi().getTotalPage();
                    //总弄页数
                    if (SumPage < 2) {
                        mAdapter.setLoadStatus(QuestionAdapter.LoadStatus.LOADING_GONE);
                    } else {
                        mAdapter.setLoadStatus(QuestionAdapter.LoadStatus.CLICK_LOAD_MORE);
                    }
                } else {
                    ToastUtil.showShort(QuestionActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<QuestionResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<QuestionResult> call = mApi.getService().createCommitPage(token, currentpage);
        call.enqueue(callback);
    }

    private void createSingle(String questionType) {
        Call<QuestionResult> call = mApi.getService().createCommitPage(token, questionType, currentpage);
        call.enqueue(callback);
    }

}
