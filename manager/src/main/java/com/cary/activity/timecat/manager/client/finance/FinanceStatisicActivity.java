package com.cary.activity.timecat.manager.client.finance;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 数据统计
 */
@SuppressLint("Registered")
public class FinanceStatisicActivity extends FragmentActivity {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tab_essence)
    TabLayout tablayout;
    @BindView(R.id.vp_essence)
    ViewPager tabViewpager;
    private TabLayout tabLayout = null;

    private ViewPager viewPager;

    private Fragment[] mFragmentArrays = new Fragment[4];

    private String[] mTabTitles = new String[4];
    private  String IdStr;

    private String typeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_statisic);
        ButterKnife.bind(this);

        tabLayout = (TabLayout) findViewById(R.id.tab_essence);
        viewPager = (ViewPager) findViewById(R.id.vp_essence);
        initView();
        titleText .setText("数据统计");
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        rlTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleBack.setPadding(20,0,0,0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

    }
    private void initView() {
        mTabTitles[0] = "年";
        mTabTitles[1] = "月";
        mTabTitles[2] = "日";
        mTabTitles[3] = "全部";
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //设置tablayout距离上下左右的距离
        //tab_title.setPadding(20,20,20,20);
        mFragmentArrays[0] = new TabFragment(0);//TabFragment.newInstance(0);
        mFragmentArrays[1] = new TabFragment(1);//TabFragment.newInstance(1);
        mFragmentArrays[2] = new TabFragment(2);//TabFragment.newInstance(2);
        mFragmentArrays[3] = new TabFragment(3);//TabFragment.newInstance(2);
        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        tabLayout.setupWithViewPager(viewPager);
    }

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

    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];

        }
    }
}
