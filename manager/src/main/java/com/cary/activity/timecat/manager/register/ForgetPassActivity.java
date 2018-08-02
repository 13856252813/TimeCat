package com.cary.activity.timecat.manager.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.BaseUtil;
import com.cary.activity.timecat.manager.util.ToastUtil;
import com.cary.activity.timecat.manager.util.view.MyCountDownTimer;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 忘记密码
 */
public class ForgetPassActivity extends AppCompatActivity {

    private static final String TAG = ForgetPassActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.etForgetPassInputPhone)
    EditText etForgetPassInputPhone;
    @BindView(R.id.etForgetPassInputPhoneVerify)
    EditText etForgetPassInputPhoneVerify;
    @BindView(R.id.tvForgetPassGetVerfiy)
    TextView tvForgetPassGetVerfiy;
    @BindView(R.id.etForgetPassInputPass)
    EditText etForgetPassInputPass;
    @BindView(R.id.btForgetPass)
    Button btForgetPass;

    private MyCountDownTimer mc;
    private boolean processFlag = true; //默认可以点击

    private ForgetPassCommitParam forgetcommitParam;
    private ForgetPassApi forgetpassapi;

    private SendSmsCodeClass smscodeClass;

    private String idStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        ButterKnife.bind(this);
        idStr = getIntent().getStringExtra("id");
        if (!TextUtils.isEmpty(idStr)&&"1".equals(idStr)) {
            titleText.setText("修改密码");
        } else {
            titleText.setText(getString(R.string.Forget_pass_title));
        }
        forgetpassapi = ForgetPassApi.getApi();
        forgetcommitParam = new ForgetPassCommitParam();
        smscodeClass = new SendSmsCodeClass(this);
        mc = new MyCountDownTimer(60000, 1000, tvForgetPassGetVerfiy);

    }

    @OnClick({R.id.title_back, R.id.tvForgetPassGetVerfiy, R.id.btForgetPass})
    public void onViewClicked(View view) {
        String phoneNumber = etForgetPassInputPhone.getText().toString().trim();
        String verfiyNumber = etForgetPassInputPhoneVerify.getText().toString().trim();
        String pass = etForgetPassInputPass.getText().toString().trim();

        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tvForgetPassGetVerfiy:
                if (TextUtils.isEmpty(phoneNumber)) {
                    ToastUtil.showShort(this, getString(R.string.error_field_required));
                    break;
                } else if (!BaseUtil.isMobile(phoneNumber)) {
                    //手机号码格式
                    ToastUtil.showShort(this, getString(R.string.error_invalid_email));
                    break;
                } else if (processFlag) {
                    smscodeClass.SendSmsCodeMethod(phoneNumber);
                    setProcessFlag();//
                    mc.start();// 去执行的具体操作
                    new TimeThread().start();
                }
                break;
            case R.id.btForgetPass:
//                判断是否为空
//                手机号码不能为空
                if (TextUtils.isEmpty(phoneNumber)) {
                    ToastUtil.showShort(this, getString(R.string.error_field_required));
                    break;
                } else if (!BaseUtil.isMobile(phoneNumber)) {
                    //手机号码格式
                    ToastUtil.showShort(this, getString(R.string.error_invalid_email));
                    break;
                } else if (TextUtils.isEmpty(verfiyNumber)) {
//    验证码不能为空
                    ToastUtil.showShort(this, getString(R.string.error_field_required_verfiy));
                    break;
                } else if (TextUtils.isEmpty(pass)) {
//                    密码不能为空
                    ToastUtil.showShort(this, getString(R.string.error_field_required_pass));
                    break;
                } else if (pass.length() < 6 || pass.length() > 16) {
//                    判断输入的密码是多少位
                    ToastUtil.showShort(this, getString(R.string.error_invalid_password));
                    break;
                } else {
                    forgetcommitParam.setMobile(phoneNumber);
                    forgetcommitParam.setPassword(pass);
                    forgetcommitParam.setSmsCode(verfiyNumber);
                    createSingle();
                }
                break;
            default:
                break;
        }
    }

    public void oncancel(View view) {
        ToastUtil.showShort(this, "取消");
        mc.cancel();
    }

    public void restart(View view) {
        ToastUtil.showShort(this, "重新发送");
        mc.start();
    }

    /**
     * 设置按钮在短时间内被重复点击的有效标识（true表示点击有效，false表示点击无效）
     */
    private synchronized void setProcessFlag() {
        processFlag = false;
    }

    /**
     * 计时线程（防止在一定时间段内重复点击按钮）
     */
    private class TimeThread extends Thread {
        public void run() {
            try {
                sleep(60000);
                processFlag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Callback<NormalCommitResult> callback = new Callback<NormalCommitResult>() {
        @Override
        public void onResponse(Call<NormalCommitResult> call, Response<NormalCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                NormalCommitResult forgetpassComRes = response.body();
                if ("00".equals(forgetpassComRes.getCode())) {
                    ToastUtil.showShort(ForgetPassActivity.this, "密码修改成功");
                    finish();
                } else {
                    ToastUtil.showShort(ForgetPassActivity.this, forgetpassComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NormalCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
            ToastUtil.showShort(ForgetPassActivity.this, "注册失败");
        }
    };

    private void createSingle() {
        Call<NormalCommitResult> call = forgetpassapi.getService().createCommit(
                forgetcommitParam.getMobile(),
                forgetcommitParam.getPassword(),
                forgetcommitParam.getSmsCode());
        call.enqueue(callback);
    }

    private void createMap() {
        Map map = forgetcommitParam.createCommitParams();
        Call<NormalCommitResult> call = forgetpassapi.getService().createCommit(map);
        call.enqueue(callback);
    }

}
