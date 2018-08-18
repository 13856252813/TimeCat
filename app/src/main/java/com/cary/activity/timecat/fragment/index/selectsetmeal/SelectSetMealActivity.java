package com.cary.activity.timecat.fragment.index.selectsetmeal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.selectsetmeal.fragment.TabFragment;
import com.cary.activity.timecat.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择套餐
 */
public class SelectSetMealActivity extends BaseActivity {
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
    private TabLayout tabLayout = null;

    private ViewPager viewPager;

    private Fragment[] mFragmentArrays = new Fragment[3];

    private String[] mTabTitles = new String[3];
    private  String IdStr;

    private String typeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_set_meal);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.white),0);

        IdStr = getIntent().getStringExtra("id");
        typeList = getIntent().getStringExtra("type");


        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        initView();
        titleText .setText("选择套餐");
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        rlTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleBack.setPadding(20,0,0,0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

    }

    @Override
    public int getLayout() {
        return 0;
    }

    private void initView() {
        mTabTitles[0] = "全部";
        mTabTitles[1] = "私人订制";
        mTabTitles[2] = "套餐";
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //设置tablayout距离上下左右的距离
        //tab_title.setPadding(20,20,20,20);
        mFragmentArrays[0] = new TabFragment(IdStr,0,typeList);//TabFragment.newInstance(0);
        mFragmentArrays[1] = new TabFragment(IdStr,1,typeList);//TabFragment.newInstance(1);
        mFragmentArrays[2] = new TabFragment(IdStr,2,typeList);//TabFragment.newInstance(2);
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
