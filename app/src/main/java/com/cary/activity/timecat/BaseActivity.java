package com.cary.activity.timecat;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.cary.activity.timecat.reglogin.LoginCommitResult;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.StatusBarUtil;
import com.cary.activity.timecat.util.ToastUtil;
import com.google.gson.Gson;
import com.hyphenate.util.DensityUtil;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private WindowManager mWindowManager;
    View mFloatView;

    private Unbinder mUnbind;

    private SharedPreferencesHelper mSharePreference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getLayout()!=0){
            setContentView(getLayout());
        }
        PushAgent.getInstance(this).onAppStart();
        setStatusBar();
        mUnbind= ButterKnife.bind(this);
        mWindowManager=getWindowManager();

    }
    public abstract int getLayout();
    public void clickBack(View view){
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this); // 基础指标统计，不能遗漏
        if(setCustomerView()){
            initCustomerView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this); // 基础指标统计，不能遗漏
        if(setCustomerView()){
            removeView();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbind.unbind();

    }

    public void setStatusBar(){
        StatusBarUtil.setColor(this,getResources().getColor(R.color.white),0);
    }


    public boolean setCustomerView(){
        return true;
    }

    public LoginCommitResult.Data getCurrentUser(){
        if(mSharePreference == null){
            mSharePreference=new SharedPreferencesHelper(this);
        }
        return new Gson().fromJson(
                (String) mSharePreference.getSharedPreference("user",""),LoginCommitResult.Data.class);

    }

    private void initCustomerView(){


        mFloatView=getLayoutInflater().inflate(R.layout.float_button,null);

        WindowManager.LayoutParams params=new WindowManager.LayoutParams();
        params.width= DensityUtil.dip2px(this,48);
        params.height= DensityUtil.dip2px(this,48);
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        params.x =DensityUtil.dip2px(this,10);
        params.y =DensityUtil.dip2px(this,80);

        mWindowManager.addView(mFloatView,params);
        mFloatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(BaseActivity.this,"客服");
            }
        });
    }


    private void removeView(){
        mWindowManager.removeView(mFloatView);

    }




}

