package com.cary.activity.timecat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(R.color.white));
            setContentView(getLayout());
            setTitle(getResources().getString(R.string.app_name));

//        }
        PushAgent.getInstance(this).onAppStart();
    }
    public abstract int getLayout();
//    public void setTitle(String title){
//        ((TextView)findViewById(R.id.umeng_title)).setText(title);
//    }
//    public void setBackVisibily(){
//        findViewById(R.id.umeng_back).setVisibility(View.VISIBLE);
//        findViewById(R.id.umeng_back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickBack(view);
//            }
//        });
//    }
    public void clickBack(View view){
        finish();
    }

    // BaseActivity中统一调用MobclickAgent 类的 onResume/onPause 接口
    // 子类中无需再调用
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this); // 基础指标统计，不能遗漏
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this); // 基础指标统计，不能遗漏
    }
}

