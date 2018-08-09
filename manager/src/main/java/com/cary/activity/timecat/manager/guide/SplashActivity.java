package com.cary.activity.timecat.manager.guide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.MainActivity;
import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.login.LoginActivity;
import com.cary.activity.timecat.manager.util.BaseUtil;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.TimeUtil;

/**
 * 开屏页
 */
public class SplashActivity extends AppCompatActivity {

    private final String TAG = SplashActivity.class.getSimpleName();

    private static final int sleepTime = 2000;
    private PrefManager prefManager;
    private TimeUtil tu;
    private SharedPreferencesHelper shareph;
    private String token;
    private String type = "";

    @Override
    protected void onCreate(Bundle arg0) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.em_activity_splash);
        super.onCreate(arg0);

//        DemoHelper.getInstance().initHandler(this.getMainLooper());
//在setContentView()前检查是否第一次运行
        prefManager = new PrefManager(this);
        tu = new TimeUtil();
        shareph = new SharedPreferencesHelper(this);
        token = (String) shareph.getSharedPreference("token", "");
        type = (String) shareph.getSharedPreference("type", "");

        RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.splash_root);
        TextView versionText = (TextView) findViewById(R.id.tv_version);

        versionText.setText(getVersion());
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        rootLayout.startAnimation(animation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            public void run() {
                Log.v(TAG, "Token:" + token);
                Log.v(TAG, "prefManager.getSaveTime():" + prefManager.getSaveTime());
                if (tu.compareTwoTime3(prefManager.getSaveTime()) && !TextUtils.isEmpty(token) && !TextUtils.isEmpty(type)) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
//                if (DemoHelper.getInstance().isLoggedIn()) {
//                    // auto login mode, make sure all group and conversation is loaed before enter the main screen
//                    long start = System.currentTimeMillis();
//                    EMClient.getInstance().chatManager().loadAllConversations();
//                    EMClient.getInstance().groupManager().loadAllGroups();
//                    long costTime = System.currentTimeMillis() - start;
//                    //wait
//                    if (sleepTime - costTime > 0) {
//                        try {
//                            Thread.sleep(sleepTime - costTime);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    String topActivityName = EasyUtils.getTopActivityName(EMClient.getInstance().getContext());
//                    if (topActivityName != null && (topActivityName.equals(VideoCallActivity.class.getName()) || topActivityName.equals(VoiceCallActivity.class.getName()))) {
//                        // nop
//                        // avoid main screen overlap Calling Activity
//                    } else {
//                        //enter main screen
//                        startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
//                    }
//                    finish();
//                } else {
//                    try {
//                        Thread.sleep(sleepTime);
//                    } catch (InterruptedException e) {
//                    }
//                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                    finish();
//                }
            }
        }).start();

    }

    /**
     * get sdk version
     */
    private String getVersion() {
        return BaseUtil.getAppVersionName(this);//EMClient.getInstance().VERSION;
    }
}
