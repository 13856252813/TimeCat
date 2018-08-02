package com.cary.activity.timecat.manager.client.myorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.client.question.QuestionApi;
import com.cary.activity.timecat.manager.client.question.QuestionDetialResult;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReturnOrderActivity extends AppCompatActivity {
    private static final String TAG = ReturnOrderActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_myorder_iamge)
    ImageView ivMyorderIamge;
    @BindView(R.id.tv_myorder_name)
    TextView tvMyorderName;
    @BindView(R.id.tv_myorder_desc)
    TextView tvMyorderDesc;
    @BindView(R.id.tv_myorder_stage)
    TextView tvMyorderStage;
    @BindView(R.id.tv_myorder_stage_time)
    TextView tvMyorderStageTime;
    @BindView(R.id.tv_myorder_price)
    TextView tvMyorderPrice;
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
    @BindView(R.id.tv_question_detial_remark_text)
    TextView tvQuestionDetialRemarkText;
    @BindView(R.id.tv_question_remark)
    TextView tvQuestionRemark;
    @BindView(R.id.btn_return_order_noagree)
    Button btnReturnOrderNoagree;
    @BindView(R.id.btn_return_order_agree)
    Button btnReturnOrderAgree;

    private QuestionApi mApi;
    private QuestionDetialResult mRes;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int id,ordertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_return_order);
        ButterKnife.bind(this);

        titleText.setText("退款详情");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));


        id = getIntent().getIntExtra("id", 0);
        ordertype = getIntent().getIntExtra("ordertype",0);

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");

        mApi = QuestionApi.getApi();
        createSingle();
    }

    @OnClick({R.id.title_back, R.id.btn_return_order_noagree, R.id.btn_return_order_agree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_return_order_noagree:
                ToastUtil.showShort(this,"拒绝");

                break;
            case R.id.btn_return_order_agree:
                ToastUtil.showShort(this,"同意退款");


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
                } else {
                    ToastUtil.showShort(ReturnOrderActivity.this, mRes.getMsg());
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
}
