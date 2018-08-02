package com.cary.activity.timecat.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.activity.timecat.manager.client.fragment.AccountFragment;
import com.cary.activity.timecat.manager.client.fragment.FinaceFragment;
import com.cary.activity.timecat.manager.client.fragment.PeopleFragment;
import com.cary.activity.timecat.manager.client.fragment.StoreFragment;
import com.cary.activity.timecat.manager.guide.PrefManager;
import com.cary.activity.timecat.manager.login.LoginActivity;
import com.cary.activity.timecat.manager.teacher.fragment.GrabFragment;
import com.cary.activity.timecat.manager.teacher.fragment.MessageFragment;
import com.cary.activity.timecat.manager.teacher.fragment.PersonFragment;
import com.cary.activity.timecat.manager.teacher.fragment.ScheduleFragment;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 老师端和管理端 入口
 */
public class MainActivity extends FragmentActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    private List<TabItem> mTableItemList;

    private String type = "1";
    private SharedPreferencesHelper sharedPreferencesHelper;
    private long exitTime = 0;//标识时间退出
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefManager = new PrefManager(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        type = (String) sharedPreferencesHelper.getSharedPreference("type", "");
        initTabData();
        initTabHost();

    }

    //初始化Tab数据
    private void initTabData() {
        mTableItemList = new ArrayList<>();
        if ("1".equals(type)) {
            //添加tab
            mTableItemList.add(new TabItem(R.mipmap.tabbar_button_1, R.mipmap.tabbar_button_5, R.string.grabText, GrabFragment.class));
            mTableItemList.add(new TabItem(R.mipmap.tabbar_button_2, R.mipmap.tabbar_button_6, R.string.schedule_text, ScheduleFragment.class));
            mTableItemList.add(new TabItem(R.mipmap.tabbar_button_3, R.mipmap.tabbar_button_7, R.string.message_text, MessageFragment.class));
            mTableItemList.add(new TabItem(R.mipmap.tabbar_button_4, R.mipmap.tabbar_button_8, R.string.self_text, PersonFragment.class));
        } else if ("2".equals(type)) {
            ToastUtil.showShort(this, "管理端");
            mTableItemList.add(new TabItem(R.mipmap.tabbar_button_9, R.mipmap.tabbar_button_12, R.string.storeText, StoreFragment.class));
            mTableItemList.add(new TabItem(R.mipmap.tabbar_button_10, R.mipmap.tabbar_button_13, R.string.people_text, PeopleFragment.class));
            mTableItemList.add(new TabItem(R.mipmap.tabbar_button_3, R.mipmap.tabbar_button_7, R.string.message_text, MessageFragment.class));
            mTableItemList.add(new TabItem(R.mipmap.tabbar_button_11, R.mipmap.tabbar_button_14, R.string.finace_text, FinaceFragment.class));
            mTableItemList.add(new TabItem(R.mipmap.tabbar_button_4, R.mipmap.tabbar_button_8, R.string.account_text, AccountFragment.class));
        } else {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

    //初始化主页选项卡视图
    private void initTabHost() {
        //实例化FragmentTabHost对象
        FragmentTabHost fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        //去掉分割线
        fragmentTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mTableItemList.size(); i++) {
            TabItem tabItem = mTableItemList.get(i);
            //实例化一个TabSpec,设置tab的名称和视图
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(tabItem.getTitleString()).setIndicator(tabItem.getView());
            fragmentTabHost.addTab(tabSpec, tabItem.getFragmentClass(), null);

            //给Tab按钮设置背景
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(android.R.color.transparent));

            //默认选中第一个tab
            if (i == 0) {
                tabItem.setChecked(true);
            }
        }

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //重置Tab样式
                for (int i = 0; i < mTableItemList.size(); i++) {
                    TabItem tabitem = mTableItemList.get(i);
                    if (tabId.equals(tabitem.getTitleString())) {
                        tabitem.setChecked(true);
                    } else {
                        tabitem.setChecked(false);
                    }
                }
            }
        });
    }

    class TabItem {
        //正常情况下显示的图片
        private int imageNormal;
        //选中情况下显示的图片
        private int imagePress;
        //tab的名字
        private int title;
        private String titleString;

        //tab对应的fragment
        public Class<? extends Fragment> fragmentClass;

        public View view;
        public ImageView imageView;
        public TextView textView;

        public TabItem(int imageNormal, int imagePress, int title, Class<? extends Fragment> fragmentClass) {
            this.imageNormal = imageNormal;
            this.imagePress = imagePress;
            this.title = title;
            this.fragmentClass = fragmentClass;
        }

        public Class<? extends Fragment> getFragmentClass() {
            return fragmentClass;
        }

        public int getImageNormal() {
            return imageNormal;
        }

        public int getImagePress() {
            return imagePress;
        }

        public int getTitle() {
            return title;
        }

        public String getTitleString() {
            if (title == 0) {
                return "";
            }
            if (TextUtils.isEmpty(titleString)) {
                titleString = getString(title);
            }
            return titleString;
        }

        public View getView() {
            if (this.view == null) {
                this.view = getLayoutInflater().inflate(R.layout.view_tab_indicator, null);
                this.imageView = (ImageView) this.view.findViewById(R.id.tab_iv_image);
                this.textView = (TextView) this.view.findViewById(R.id.tab_tv_text);
                if (this.title == 0) {
                    this.textView.setVisibility(View.GONE);
                } else {
                    this.textView.setVisibility(View.VISIBLE);
                    this.textView.setText(getTitleString());
                }
                this.imageView.setImageResource(imageNormal);
            }
            return this.view;
        }

        //切换tab的方法
        public void setChecked(boolean isChecked) {
            if (imageView != null) {
                if (isChecked) {
                    imageView.setImageResource(imagePress);
                } else {
                    imageView.setImageResource(imageNormal);
                }
            }
            if (textView != null && title != 0) {
                if (isChecked) {
                    textView.setTextColor(getResources().getColor(R.color.login_color_btn));
                } else {
                    textView.setTextColor(getResources().getColor(R.color.color_three));
                }
            }
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
            //退出时保存
            prefManager.setSaveTime();
            finish();
            System.exit(0);
        }
    }
}
