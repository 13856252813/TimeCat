package com.cary.activity.timecat.fragment.index.timeclouddish;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 时光云盘
 */
public class TimeCloudDishActivity extends BaseActivity {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.tab_viewpager)
    ViewPager tabViewpager;
//    private TabLayout tabLayout = null;
//    private ViewPager viewPager;
//
//    private Fragment[] mFragmentArrays = new Fragment[4];

    private String[] mTabTitles = {"私密云盘", "共享云盘", "传输列表", "回收站"};
    //当标签数目小于等于4个时，标签栏不可滑动
    public static final int MOVABLE_COUNT = 4;

    private int tabCount = 0;//总个数
    private List<String> tabs;
    private List<Fragment> fragments;

//    private String idArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        String IdStr = getIntent().getStringExtra("id");
//        idArray = getIntent().getStringExtra("idarray");
//        initView();
        titleText.setText("时光云盘");
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        rlTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        initDatas(mTabTitles);
        tabViewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        initTabLayout();

    }

    @Override
    public boolean setCustomerView() {
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_time_cloud_dish;
    }

    private void initTabLayout() {
        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        tablayout.setTabMode(tabCount <= MOVABLE_COUNT ? TabLayout.MODE_FIXED : TabLayout.MODE_SCROLLABLE);
        //指示条的颜色
        tablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.login_color_btn));
        tablayout.setSelectedTabIndicatorHeight((int) getResources().getDimension(R.dimen.two));
        //关联tabLayout和ViewPager,两者的选择和滑动状态会相互影响
        tablayout.setupWithViewPager(tabViewpager);

        //自定义标签布局
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.activity_time_cloud_dish_tab_view, tablayout, false);
            tv.setText(tabs.get(i));
            tab.setCustomView(tv);
        }
    }

    private void initDatas(String[] mTabTitle) {
        tabs = new ArrayList<>();
        fragments = new ArrayList<>();
        for (int i = 0; i < mTabTitle.length; i++) {
            tabs.add(mTabTitle[i]);
            TimeCloudDishFragment fragment = TimeCloudDishFragment.newInstance(i);
            fragments.add(fragment);
        }

    }
//    private void initView() {
//        mTabTitles[0] = "私密云盘";
//        mTabTitles[1] = "共享云盘";
//        mTabTitles[2] = "传输列表";
//        mTabTitles[3] = "回收站";
//        //设置tablayout距离上下左右的距离
//        //tab_title.setPadding(20,20,20,20);
//        mFragmentArrays[0] = new TimeCloudDishFragment(0);//TabFragment.newInstance(0);
//        mFragmentArrays[1] = new TimeCloudDishFragment(1);//TabFragment.newInstance(1);
//        mFragmentArrays[2] = new TimeCloudDishFragment(2);//TabFragment.newInstance(2);
//        mFragmentArrays[3] = new TimeCloudDishFragment(3);//TabFragment.newInstance(2);
//
//        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(pagerAdapter);
//        //将ViewPager和TabLayout绑定
//        tabLayout.setupWithViewPager(viewPager);
//    }

    @OnClick({R.id.title_back, R.id.title_text_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_text_right:
                break;
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        /**
         * 如果不是自定义标签布局，需要重写该方法
         */
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return tabs.get(position);
//        }
    }
}
//    final class MyViewPagerAdapter extends FragmentPagerAdapter {
//        public MyViewPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragmentArrays[position];
//        }
//
//
//        @Override
//        public int getCount() {
//            return mFragmentArrays.length;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTabTitles[position];
//
//        }
//    }
//}
