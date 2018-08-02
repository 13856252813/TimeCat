package com.cary.activity.timecat.manager.register;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.login.LoginApi;
import com.cary.activity.timecat.manager.login.LoginCommitParam;
import com.cary.activity.timecat.manager.login.LoginCommitResult;
import com.cary.activity.timecat.manager.util.BaseUtil;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;
import com.cary.activity.timecat.manager.util.view.EditTextWatcher;
import com.cary.activity.timecat.manager.util.view.MyCountDownTimer;
import com.cary.activity.timecat.manager.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = RegisterActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.etRegisterInputPhone)
    EditText etRegisterInputPhone;
    @BindView(R.id.etRegisterInputPhoneVerify)
    EditText etRegisterInputPhoneVerify;
    @BindView(R.id.etRegisterInputPass)
    EditText etRegisterInputPass;
    @BindView(R.id.etRegisterInputInvitation)
    EditText etRegisterInputInvitation;
    @BindView(R.id.btRegister)
    Button btRegister;
    @BindView(R.id.tvRegisterProtolText)
    TextView tvRegisterProtolText;
    @BindView(R.id.llRegisterProtol)
    LinearLayout llRegisterProtol;
    @BindView(R.id.tvGetVerfiy)
    TextView tvGetVerfiy;
    @BindView(R.id.cbProtol)
    CheckBox cbProtol;

    private MyCountDownTimer mc;
    private boolean processFlag = true; //默认可以点击

    private SendSmsCodeClass smscodeClass;
    //    private String loginphone,loginpass;
    private RegisterApi regApi;
    private RegisterCommitParam regcommitParam;
    private SharedPreferencesHelper sharedPreferencesHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        ButterKnife.bind(this);
        initView();

        regApi = RegisterApi.getApi();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);

        mc = new MyCountDownTimer(60000, 1000, tvGetVerfiy);
        etRegisterInputPass.addTextChangedListener(new EditTextWatcher(etRegisterInputPass));
        etRegisterInputInvitation.addTextChangedListener(new EditTextWatcher(etRegisterInputInvitation));
        etRegisterInputInvitation.setVisibility(View.GONE);
        tvRegisterProtolText.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        titleBack.setOnClickListener(this);
        tvGetVerfiy.setOnClickListener(this);
        smscodeClass = new SendSmsCodeClass(this);


    }

    //文本显示控制
    private void initView() {
        tvRegisterProtolText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvGetVerfiy.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        titleText.setText("注册账户");

    }


    @Override
    public void onClick(View v) {
        String phoneNumber = etRegisterInputPhone.getText().toString().trim();
        String verfiyNumber = etRegisterInputPhoneVerify.getText().toString().trim();
        String pass = etRegisterInputPass.getText().toString().trim();
        String Invitation = etRegisterInputInvitation.getText().toString().trim();
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tvGetVerfiy:
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
            case R.id.tvRegisterProtolText:
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("url", "https://m.baidu.com");
                intent.putExtra("title", getString(R.string.tvRegisterProtolTwo));
                startActivity(intent);
                break;
            case R.id.btRegister:
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
                } else if (!cbProtol.isChecked()) {
                    //判断选中状态
                    ToastUtil.showShort(this, getString(R.string.error_field_protol_agree));
                    break;
                } else if (pass.length() < 6 || pass.length() > 16) {
//                    判断输入的密码是多少位
                    ToastUtil.showShort(this, getString(R.string.error_invalid_password));
                    break;
                } else if (TextUtils.isEmpty(verfiyNumber)) {
                    ToastUtil.showShort(this, "请输入验证码");
                    break;
                } else {
                    regcommitParam = new RegisterCommitParam();
                    regcommitParam.setMobile(phoneNumber);
                    regcommitParam.setPassword(pass);
                    regcommitParam.setRequestCode(Invitation);
                    regcommitParam.setType("1");
                    regcommitParam.setSmsCode(verfiyNumber);
                    createSingleReg(regcommitParam);
                    //跳转到完善信息
                }
                break;
            default:
                break;
        }
    }

    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//          loginphone = data.getStringExtra("loginphone");
//          loginpass = data.getStringExtra("loginpass");
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {
            case 1001:
                Log.v(TAG,"注册完成后，销毁当前");
                //注册完成，执行登录过程
//                LogUtils.v("phone:"+loginphone+"--loginpass:"+loginpass);
//                createSingleLogin();
                break;
            case 2:
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

    //注册个人信息
    private Callback<RegisterCommitResult> callback = new Callback<RegisterCommitResult>() {
        @Override
        public void onResponse(Call<RegisterCommitResult> call, Response<RegisterCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                RegisterCommitResult regComRes = response.body();
                if ("00".equals(regComRes.getCode())) {
                    createSingleLogin(regcommitParam.getMobile(), regcommitParam.getPassword());
//                    Intent mIntent = new Intent();
//                    mIntent.putExtra("loginphone", regcommitParam.getMobile());
//                    mIntent.putExtra("loginpass", regcommitParam.getPassword());
//                    // 设置结果，并进行传送
//                    setResult(1001, mIntent);
//                    finish();
                } else {
                    ToastUtil.showShort(RegisterActivity.this, regComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<RegisterCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
            ToastUtil.showShort(RegisterActivity.this, "注册失败");
        }
    };

    private void createSingleReg(RegisterCommitParam regcommitParam) {
        Call<RegisterCommitResult> call = regApi.getService().createCommit(
                regcommitParam.getMobile(),
                regcommitParam.getPassword(),
                regcommitParam.getRequestCode(),
                regcommitParam.getType(),
                regcommitParam.getSmsCode());
        call.enqueue(callback);
    }

    //注册环信
    private Callback<LoginCommitResult> callbackLoginEase = new Callback<LoginCommitResult>() {
        @Override
        public void onResponse(Call<LoginCommitResult> call, Response<LoginCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                LoginCommitResult loginComRes = response.body();
                if ("00".equals(loginComRes.getCode())) {
                    sharedPreferencesHelper.put("id", loginComRes.getData().getId());
                    sharedPreferencesHelper.put("token", loginComRes.getData().getToken());
                    Intent intentcommit = new Intent(RegisterActivity.this, PerfectInformationActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("logincomes", loginComRes);
                    intentcommit.putExtras(bundle);
//                    startActivity(intentcommit);
                    startActivityForResult(intentcommit, 1001);
                } else {
                    ToastUtil.showShort(RegisterActivity.this, loginComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<LoginCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
            ToastUtil.showShort(RegisterActivity.this, "登录失败");
        }
    };
//
//    public void createSingleLoginEase(long uid) {
//        String token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
//        Call<LoginCommitResult> call = loginEaseApi.getApi().getService().createCommit(
//                token,
//                uid);
//        call.enqueue(callbackLoginEase);
//    }

    //登录
    private Callback<LoginCommitResult> callbackLogin = new Callback<LoginCommitResult>() {
        @Override
        public void onResponse(Call<LoginCommitResult> call, Response<LoginCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                LoginCommitResult loginComRes = response.body();
                if ("00".equals(loginComRes.getCode())) {
                    sharedPreferencesHelper.put("id", loginComRes.getData().getId());
                    sharedPreferencesHelper.put("token", loginComRes.getData().getToken());
                    sharedPreferencesHelper.put("type", loginComRes.getData().getType());
//                    createSingleLoginEase(loginComRes.getData().getId());
                    Intent intentcommit = new Intent(RegisterActivity.this, PerfectInformationActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("logincomes", loginComRes);
                    intentcommit.putExtras(bundle);
//                    startActivity(intentcommit);
                    startActivityForResult(intentcommit, 1001);
//                    setResult(1001, intentcommit);
                    finish();
                } else {
                    ToastUtil.showShort(RegisterActivity.this, loginComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<LoginCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
            ToastUtil.showShort(RegisterActivity.this, "登录失败");
        }
    };

    private void createSingleLogin(String loginphone, String loginpass) {
        LoginCommitParam logincp = new LoginCommitParam();
        logincp.setMobile(loginphone);
        logincp.setPassword(loginpass);
        String deviceToken = (String) sharedPreferencesHelper.getSharedPreference("deviceToken", "");
        Call<LoginCommitResult> call = LoginApi.getApi().getService().createCommit(
                logincp.getMobile(),
                logincp.getPassword(),
                "android", "1", deviceToken);
        call.enqueue(callbackLogin);
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
}
