package com.cary.activity.timecat.fragment.person.advice;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.TimeUtil;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 投诉建议
 */
public class ComPlaintAdviceActivity extends BaseActivity {

    private static final String TAG = ComPlaintAdviceActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.et_complaint_advice_nickname)
    EditText etComplaintAdviceNickname;
    @BindView(R.id.et_complaint_advice_telephone)
    EditText etComplaintAdviceTelephone;
    @BindView(R.id.et_complaint_advice_content)
    EditText etComplaintAdviceContent;
    @BindView(R.id.btn_complaint_advice_commit)
    Button btnComplaintAdviceCommit;
    @BindView(R.id.spinner_complaint_advice)
    Spinner spinnerComplaintAdvice;
    @BindView(R.id.tv_complaint_advice_questiontype)
    TextView tvComplaintAdviceQuestiontype;

    private String nickname, telephone, commit;
    private AdviceApi adviceApi;
    private SharedPreferencesHelper spHelper;
    private AdviceResult adviceResult;
    private TimeUtil timeUtil;

    private String questionType="COMMON";
    private String[] questionTypeStr = {"常规问题", "服务问题", "老师问题"};
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        titleText.setText("投诉建议");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, questionTypeStr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerComplaintAdvice.setOnItemSelectedListener(new SpinnerSelectedListener());
        spinnerComplaintAdvice.setAdapter(adapter);

        adviceApi = AdviceApi.getApi();
        spHelper = new SharedPreferencesHelper(this);
        timeUtil =  new TimeUtil();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_com_plaint_advice;
    }

    @OnClick({R.id.title_back, R.id.btn_complaint_advice_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_complaint_advice_commit:
                nickname = etComplaintAdviceNickname.getText().toString().trim();
                telephone = etComplaintAdviceTelephone.getText().toString().trim();
                commit = etComplaintAdviceContent.getText().toString().trim();
                if (TextUtils.isEmpty(questionType)) {
                    ToastUtil.showShort(this, "请选择问题类型");
                    break;
                } else if (TextUtils.isEmpty(nickname)) {
                    ToastUtil.showShort(this, "请输入称呼");
                    break;
                } else if (TextUtils.isEmpty(telephone)) {
                    ToastUtil.showShort(this, "请输入联系电话");
                    break;
                } else if (TextUtils.isEmpty(commit)) {
                    ToastUtil.showShort(this, "请输入您的宝贵建议");
                    break;
                } else {
                    createSingle(questionType, nickname, telephone, commit);
                }
                break;
        }
    }

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {


        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            tvComplaintAdviceQuestiontype.setText(questionTypeStr[arg2]);
            if ("常见问题".equals(questionTypeStr[arg2])) {
                questionType = "COMMON";
            } else if ("服务问题".equals(questionTypeStr[arg2])) {
                questionType = "SERVICE";
            } else if ("老师问题".equals(questionTypeStr[arg2])) {
                questionType = "TEACHER";
            }
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }

    private Callback<AdviceResult> callback = new Callback<AdviceResult>() {
        @Override
        public void onResponse(Call<AdviceResult> call, Response<AdviceResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                adviceResult = response.body();
                if ("00".equals(adviceResult.getCode())) {
                    ToastUtil.showShort(ComPlaintAdviceActivity.this, "提交成功");
                    finish();
                } else {
                    ToastUtil.showShort(ComPlaintAdviceActivity.this, adviceResult.getMsg());
                }

            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<AdviceResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(String questionType, String name, String mobile, String content) {
        String token = (String) spHelper.getSharedPreference("token", "");
        String uid = (String) spHelper.getSharedPreference("id", "");
        Call<AdviceResult> call = adviceApi.getService().createCommit(token, uid, name,
                content, mobile, questionType, false,false);
        call.enqueue(callback);
    }
}