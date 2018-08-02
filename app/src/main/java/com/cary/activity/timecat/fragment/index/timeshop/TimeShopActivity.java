package com.cary.activity.timecat.fragment.index.timeshop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.timeshop.fragment.TimeShopFragment;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 时光超市
 */
public class TimeShopActivity extends BaseActivity {

    private static final String TAG = TimeShopActivity.class.getSimpleName();

    @BindView(R.id.tl_timeshop_tab)
    TabLayout tlFulldressTab;
    @BindView(R.id.viewpager_timeshop)
    ViewPager viewpagerFulldress;

    //当标签数目小于等于4个时，标签栏不可滑动
    public static final int MOVABLE_COUNT = 4;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;

    private int tabCount = 0;//总个数
    private List<String> tabs;
    private List<Fragment> fragments;

    private TimeShopTabApi mApi;
    private TimeShopTabResult mTabRes;
    private List<TimeShopTabResult.DataBean> mData;

    private SharedPreferencesHelper sharePh;
    private String token;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_full_dress_tab);
        ButterKnife.bind(this);
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleText.setText("时光超市");

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = ((int) sharePh.getSharedPreference("id", 0) + "");
        mApi = TimeShopTabApi.getApi();
        createSingleTimeShop();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_time_shop;
    }

    @OnClick({R.id.title_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;

        }
    }

    private Callback<TimeShopTabResult> callback = new Callback<TimeShopTabResult>() {
        @Override
        public void onResponse(Call<TimeShopTabResult> call, Response<TimeShopTabResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mTabRes = response.body();
                if ("00".equals(mTabRes.getCode())) {
                    mData = mTabRes.getData();
                    initDatas(mData);
                    initViewPager();
                    initTabLayout();
                } else {
                    ToastUtil.showShort(TimeShopActivity.this, mTabRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<TimeShopTabResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleTimeShop( ) {
        Call<TimeShopTabResult> call = mApi.getService().createCommitList(token);
        call.enqueue(callback);
    }

    private void initTabLayout() {
        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        tlFulldressTab.setTabMode(tabCount <= MOVABLE_COUNT ? TabLayout.MODE_FIXED : TabLayout.MODE_SCROLLABLE);
        //指示条的颜色
        tlFulldressTab.setSelectedTabIndicatorColor(getResources().getColor(R.color.login_color_btn));
        tlFulldressTab.setSelectedTabIndicatorHeight((int) getResources().getDimension(R.dimen.two));
        //关联tabLayout和ViewPager,两者的选择和滑动状态会相互影响
        tlFulldressTab.setupWithViewPager(viewpagerFulldress);
        //自定义标签布局
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = tlFulldressTab.getTabAt(i);
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.activity_full_dress_tab_view, tlFulldressTab, false);
            tv.setText(tabs.get(i));
            tab.setCustomView(tv);
        }
    }

    private void initViewPager() {
        viewpagerFulldress.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    private void initDatas(List<TimeShopTabResult.DataBean> mList) {
        tabs = new ArrayList<>();
        fragments = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            tabs.add(mList.get(i).getName());
            TimeShopFragment fragment = new TimeShopFragment(mList.get(i));
            fragments.add(fragment);
        }


        for (int i = 0; i < tabs.size(); i++) {
//            fragments.add(TabFragment.newInstance(tabs.get(i)));
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
