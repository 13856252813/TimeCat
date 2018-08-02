package com.cary.activity.timecat.manager.client.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;
import com.cary.activity.timecat.manager.util.view.GlideCircleTransform;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionDetialActivity extends AppCompatActivity {

    private static final String TAG = QuestionDetialActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_question_detial_head)
    ImageView ivQuestionDetialHead;
    @BindView(R.id.tv_question_detial_name)
    TextView tvQuestionDetialName;
    @BindView(R.id.tv_question_detial_context)
    TextView tvQuestionDetialContext;
    @BindView(R.id.tv_question_detial_type_text)
    TextView tvQuestionDetialTypeText;
    @BindView(R.id.tv_question_type)
    TextView tvQuestionType;
    @BindView(R.id.tv_question_detial_time_text)
    TextView tvQuestionDetialTimeText;
    @BindView(R.id.tv_question_time)
    TextView tvQuestionTime;
    @BindView(R.id.tv_question_detial_telephone_text)
    TextView tvQuestionDetialTelephoneText;
    @BindView(R.id.tv_question_telephone)
    TextView tvQuestionTelephone;
    @BindView(R.id.tv_question_detial_over)
    TextView tvQuestionDetialOver;
    @BindView(R.id.tv_question_detial_sendmsg)
    TextView tvQuestionDetialSendmsg;

    private QuestionApi mApi;
    private QuestionDetialResult mRes;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int id;
    private boolean isRead = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_question_detial);
        ButterKnife.bind(this);
        titleText.setText("投诉建议详情");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

        id = getIntent().getIntExtra("id", 0);
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");

        mApi = QuestionApi.getApi();
        createSingle();
    }

    @OnClick({R.id.title_back, R.id.tv_question_detial_over, R.id.tv_question_detial_sendmsg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tv_question_detial_over:
                //如果read 是true是已经读了，不能在读 ，那么取反是是false
                //如果是false 是未读，取反是true 可以读
                if (!isRead) {
                    createSingleRead();
                }
                break;
            case R.id.tv_question_detial_sendmsg:
                ToastUtil.showShort(this, "发送消息");
                break;
        }
    }

    //加载数据
    private Callback<QuestionDetialResult> callback = new Callback<QuestionDetialResult>() {
        @Override
        public void onResponse(Call<QuestionDetialResult> call, Response<QuestionDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                            .override(36, 36)
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.ic_launcher)
                            .priority(Priority.HIGH)
                            .transform(new GlideCircleTransform(QuestionDetialActivity.this, 2, getResources().getColor(R.color.black)));
                    String imageUrl = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1685935631,4222374157&fm=27&gp=0.jpg";
//                HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
                    Glide.with(QuestionDetialActivity.this).load(imageUrl).apply(options2).into(ivQuestionDetialHead);
                    tvQuestionDetialName.setText(mRes.getData().getNickname());
                    tvQuestionDetialContext.setText(mRes.getData().getContent());
                    tvQuestionTime.setText(mRes.getData().getCreateTime());
                    tvQuestionTelephone.setText(mRes.getData().getMobile());
                    String QuestionType = mRes.getData().getQuestionType();
                    if ("COMMON".equals(QuestionType)) {
                        tvQuestionType.setText("常规问题");
                    } else if ("SERVICE".equals(QuestionType)) {
                        tvQuestionType.setText("服务问题");
                    } else if ("TEACHER".equals(QuestionType)) {
                        tvQuestionType.setText("老师问题");
                    }
                    isRead = mRes.getData().getSuccess();

                } else {
                    ToastUtil.showShort(QuestionDetialActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<QuestionDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<QuestionDetialResult> call = mApi.getService().createCommitId(token, id);
        call.enqueue(callback);
    }

    private Callback<QuestionDetialResult> callbackUpdate = new Callback<QuestionDetialResult>() {
        @Override
        public void onResponse(Call<QuestionDetialResult> call, Response<QuestionDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    //跳转列表
                    startActivity(new Intent(QuestionDetialActivity.this, QuestionReadListActivity.class));

                } else {
                    ToastUtil.showShort(QuestionDetialActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<QuestionDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleRead() {
        Call<QuestionDetialResult> call = mApi.getService().createCommitPutId(token, id, true);
        call.enqueue(callbackUpdate);
    }
}
