package com.cary.activity.timecat.reglogin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.MainTabLayoutActivity;
import com.cary.activity.timecat.guide.PrefManager;
import com.cary.activity.timecat.util.BaseUtil;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.view.EditTextWatcher;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoaderCallbacks<Cursor> {

    private static final String TAG = LoginActivity.class.getSimpleName();

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    @BindView(R.id.ivLoginQQ)
    ImageView ivLoginQQ;
    @BindView(R.id.ivLoginWechat)
    ImageView ivLoginWechat;
    @BindView(R.id.ivLoginSina)
    ImageView ivLoginSina;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
//    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private ImageView ivBackView;
    private TextView tvRightText;
    private TextView tvTitleText;


    private LoginCommitParam logincommitParam;
    private LoginApi loginapi;

    private SharedPreferencesHelper sharedPreferencesHelper;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private ProgressDialog dialog;
    private String deviceToken;//= (String) sharedPreferencesHelper.getSharedPreference("deviceToken","");

    private PrefManager mPreManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mPreManager=new PrefManager(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        initPlatforms();
        dialog = new ProgressDialog(this);
        deviceToken = (String) sharedPreferencesHelper.getSharedPreference("deviceToken", "");

        ivBackView = (ImageView) findViewById(R.id.title_back);
        ivBackView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitleText = (TextView) findViewById(R.id.title_text);
        tvTitleText.setText("登录账户");

        tvRightText = (TextView) findViewById(R.id.title_text_right);
        tvRightText.setVisibility(View.VISIBLE);
        tvRightText.setText(getString(R.string.Forget_pass_title));
        tvRightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPassActivity.class));
            }
        });
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.loginPhone);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.addTextChangedListener(new EditTextWatcher(mPasswordView));

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.phone_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        Button mRegisterButton = (Button) findViewById(R.id.phone_register_in_button);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(LoginActivity.this, RegisterActivity.class);

                startActivityForResult(mIntent, 1001);
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        loginapi = LoginApi.getApi();
        logincommitParam = new LoginCommitParam();
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!BaseUtil.isMobile(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            logincommitParam.setMobile(email);
            logincommitParam.setPassword(password);

            createSingle();
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//                }
//            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private int FlagInt1 = 0, FlagInt2 = 0, FlagInt3 = 0;

    @OnClick({R.id.ivLoginQQ, R.id.ivLoginWechat, R.id.ivLoginSina})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLoginQQ:
                ToastUtil.showShort(this, "QQ登录");
                if (FlagInt1 == 0) {
                    doAuth(0);
                    FlagInt1 = 1;
                } else if (FlagInt1 == 1) {
                    deleteAuth(0);
                    FlagInt1 = 0;
                }
                break;
            case R.id.ivLoginWechat:
                ToastUtil.showShort(this, "WeChat登录");
                if (FlagInt2 == 0) {
                    doAuth(1);
                    FlagInt2 = 1;
                } else if (FlagInt2 == 1) {
                    deleteAuth(1);
                    FlagInt2 = 0;
                }
                break;
            case R.id.ivLoginSina:
                ToastUtil.showShort(this, "Sina登录");
                if (FlagInt3 == 0) {
                    doAuth(2);
                    FlagInt3 = 1;
                } else if (FlagInt3 == 1) {
                    deleteAuth(2);
                    FlagInt3 = 0;
                }
                break;
        }
    }

    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.SINA};

    private void deleteAuth(int position) {
        UMShareAPI.get(this).deleteOauth(this, platforms.get(position).mPlatform, authListener);
    }

    private void doAuth(final int position) {
        //删除授权
        UMShareAPI.get(this).getPlatformInfo(this, platforms.get(position).mPlatform, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                StringBuilder sb = new StringBuilder();
                for (String key : map.keySet()) {
                    sb.append(key).append(" : ").append(map.get(key)).append("\n");
                }
                Log.v(TAG, "sb.toString():" + sb.toString());
                if (position == 0) {
                    //QQ登录
                    createSingleQQ(map.get("openid"));
                } else if (position == 1) {
                    //微信登录
                    createSingleWeChat(map.get("openid"));
                } else if (position == 2) {
                    createSingleSina(map.get("openid"));
                }

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//                result.setText("错误" + throwable.getMessage());
                Log.v(TAG, "错误" + throwable.getMessage());
                ToastUtil.showShort(LoginActivity.this, "错误" + throwable.getMessage());
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
//                result.setText("用户已取消");
                ToastUtil.showShort(LoginActivity.this, "用户已取消");
            }
        });
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "授权成功了", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "授权失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "授权取消了", Toast.LENGTH_LONG).show();
        }
    };

    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }

    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {
            case 1001:
                Log.v(TAG, "注册完成后，销毁当前");
                //注册完成，执行登录过程
                startActivity(new Intent(LoginActivity.this, MainTabLayoutActivity.class));
                finish();
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    private Callback<LoginCommitResult> callback = new Callback<LoginCommitResult>() {
        @Override
        public void onResponse(Call<LoginCommitResult> call, Response<LoginCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                LoginCommitResult loginComRes = response.body();
                showProgress(false);
                if ("00".equals(loginComRes.getCode())) {
                    Log.e(TAG, "token:" + loginComRes.getData().getToken());
                    sharedPreferencesHelper.put("id", loginComRes.getData().getId());
                    sharedPreferencesHelper.put("token", loginComRes.getData().getToken());
                    sharedPreferencesHelper.put("storeId", loginComRes.getData().getshopId());
                    sharedPreferencesHelper.put("storeName",loginComRes.getData().getStoreName());
                    sharedPreferencesHelper.put("user",loginComRes.getData());
                    EMClient.getInstance().login( loginComRes.getData().getId()+"","123456",new EMCallBack() {//回调
                        @Override
                        public void onSuccess() {
                            EMClient.getInstance().groupManager().loadAllGroups();
                            EMClient.getInstance().chatManager().loadAllConversations();
                            Log.d("main", "登录聊天服务器成功！");
                        }

                        @Override
                        public void onProgress(int progress, String status) {

                        }

                        @Override
                        public void onError(int code, String message) {
                            Log.v(TAG,"code:"+code+", message:"+message);
                            Log.d("main", "登录聊天服务器失败！");
                        }
                    });
                    sharedPreferencesHelper.put("isLogin",true);
                    mPreManager.setSaveTime();
                    startActivity(new Intent(LoginActivity.this, MainTabLayoutActivity.class));
                    finish();
                } else {
                    ToastUtil.showShort(LoginActivity.this, loginComRes.getMsg());
                }

            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<LoginCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
            ToastUtil.showShort(LoginActivity.this, "登录失败");
        }
    };

    private void createSingle() {
        Call<LoginCommitResult> call = loginapi.getService().createCommit(
                logincommitParam.getMobile(),
                logincommitParam.getPassword(),
                "android", "0", deviceToken);
        call.enqueue(callback);
        showProgress(true);
    }

    private void createSingleQQ(String openId) {
        Call<LoginCommitResult> call = loginapi.getService().createCommitQQ(
                openId, "android", "0", deviceToken);
        call.enqueue(callback);
        showProgress(true);
    }

    private void createSingleWeChat(String openId) {
        Call<LoginCommitResult> call = loginapi.getService().createCommitWeChat(
                openId, "android", "0", deviceToken);
        call.enqueue(callback);
        showProgress(true);
    }

    private void createSingleSina(String openId) {
        Call<LoginCommitResult> call = loginapi.getService().createCommitSina(
                openId, "android", "0", deviceToken);
        call.enqueue(callback);
        showProgress(true);
    }

    private void createMap() {
        Map map = logincommitParam.createCommitParams();
        Call<LoginCommitResult> call = loginapi.getService().createCommit(map);
        call.enqueue(callback);
    }

/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 */
//    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
//
//        private final String mEmail;
//        private final String mPassword;
//
//        UserLoginTask(String email, String password) {
//            mEmail = email;
//            mPassword = password;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//            // TODO: attempt authentication against a network service.
//
//            try {
//                // Simulate network access.
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                return false;
//            }
//
//            for (String credential : DUMMY_CREDENTIALS) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mEmail)) {
//                    // Account exists, return true if the password matches.
//                    return pieces[1].equals(mPassword);
//                }
//            }
//
//            // TODO: register the new account here.
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            mAuthTask = null;
//            showProgress(false);
//
//            if (success) {
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                finish();
//            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            mAuthTask = null;
//            showProgress(false);
//        }
//    }
}


//qq login
  /*
                is_yellow_vip : 0
                screen_name : エンジニアのAiGou
                msg :
                vip : 0
                city :
                accessToken : C458830A84F4F4A7B81A39059FA230B9
                gender : 男
                province :
                is_yellow_year_vip : 0
                openid : 324288E33A0576128DB2D25E6AE7E9E1
                yellow_vip_level : 0
                profile_image_url : http://thirdqq.qlogo.cn/qqapp/100424468/324288E33A0576128DB2D25E6AE7E9E1/100
                access_token : C458830A84F4F4A7B81A39059FA230B9
                iconurl : http://thirdqq.qlogo.cn/qqapp/100424468/324288E33A0576128DB2D25E6AE7E9E1/100
                name : エンジニアのAiGou
                uid : 324288E33A0576128DB2D25E6AE7E9E1
                expiration : 1535891527923
                expires_in : 1535891527923
                level : 0
                ret : 0
*/


