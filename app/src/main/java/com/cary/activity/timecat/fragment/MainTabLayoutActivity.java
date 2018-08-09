package com.cary.activity.timecat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.guide.PrefManager;
import com.cary.activity.timecat.main.dialog.CEOEmailActivity;

public class MainTabLayoutActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private Fragment[] mFragments;
    private FrameLayout mLayout;
    private long exitTime = 0;//标识时间退出
    private PrefManager prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab_layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //透明导航栏 @底部 这一句不要加，目的是防止沉浸式状态栏和部分底部自带虚拟按键的手机（比如华为）发生冲突，注释掉就好了
//        保存的初始化
        prefManager = new PrefManager(this);
        //这句话，可以再次显示引导页
//        prefManager.setFirstTimeLaunch(true);
        if (prefManager.isFirstTimeLaunch()) {
            startActivity(new Intent(this, CEOEmailActivity.class));
            prefManager.setFirstTimeLaunch(false);
        }

        initView();
        initFragment();
        setListener();

        //状态栏透明和间距处理
//        StatusBarUtil.immersive(this, 0xff000000, 0.1f);
    }


    private void setListener() {
        //对RadioGroup设置监听事件(监听点击选择)
        mRadioGroup.setOnCheckedChangeListener(this);

    }

    private void initFragment() {
        //初始化要显示的Fragment数组
        mFragments = new Fragment[4];
        mFragments[0] = new HomeFragment();
        mFragments[1] = new MessageFragment();
        mFragments[2] = new LookFragment();
        mFragments[3] = new PersonFragment();
        //获取Fragment管理器
        FragmentManager manager = getSupportFragmentManager();
        //获取事物(使用v4包下)
        FragmentTransaction transaction = manager.beginTransaction();
        //默认选中HomepageFragment替换Framelayout
        transaction.replace(R.id.fl_radio_show, mFragments[0]);
        //提交事物
        transaction.commit();
        //默认点击首页
        mRadioGroup.check(R.id.rb_radio_homepage);
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_radio_navigation);
        mLayout = (FrameLayout) findViewById(R.id.fl_radio_show);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        //写法与默认点击页面的相同
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.rb_radio_homepage:
                transaction.replace(R.id.fl_radio_show, mFragments[0]);
                transaction.commit();
                break;
            case R.id.rb_radio_subscription:
                transaction.replace(R.id.fl_radio_show, mFragments[1]);
                transaction.commit();
                break;
            case R.id.rb_radio_find:
                transaction.replace(R.id.fl_radio_show, mFragments[2]);
                transaction.commit();
                break;
            case R.id.rb_radio_mine:
                transaction.replace(R.id.fl_radio_show, mFragments[3]);
                transaction.commit();
                break;
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
