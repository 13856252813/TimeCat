package com.cary.activity.timecat.fragment.index.fulldress;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.fragment.FullDressFragment;
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
 * 衣服 售卖 共享
 */
public class FullDressTabActivity extends BaseActivity {

    private static final String TAG = FullDressTabActivity.class.getSimpleName();

    @BindView(R.id.iv_fulldess_list_back)
    ImageView ivFulldessListBack;
    @BindView(R.id.btn_fulldess_gril)
    Button btnFulldessGril;
    @BindView(R.id.btn_fulldess_boy)
    Button btnFulldessBoy;
    @BindView(R.id.tl_fulldress_tab)
    TabLayout tlFulldressTab;
    @BindView(R.id.viewpager_fulldress)
    ViewPager viewpagerFulldress;

    private String flagTag = "0";
    //当标签数目小于等于4个时，标签栏不可滑动
    public static final int MOVABLE_COUNT = 4;

    private int tabCount = 0;//总个数
    private List<String> tabs;
    private List<Fragment> fragments;

    private FullDressTabApi mApi;
    private FullDressTabResult mFDTabRes;
    private List<FullDressTabResult.Data> mData;
    private LinearLayoutManager mLayoutManager;

    private SharedPreferencesHelper sharePh;
    private String token;
    private String uid;
    private int sex=1;//0男 1女

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_full_dress_tab);
        ButterKnife.bind(this);

        flagTag = getIntent().getStringExtra("flagtag");
        if (!TextUtils.isEmpty(flagTag)) {
            if ("0".equals(flagTag)) {
                Log.v(TAG, "我是售卖区");
            } else if ("1".equals(flagTag)) {
                Log.v(TAG, "我是共享区");
            }
        }

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = ((int) sharePh.getSharedPreference("id", 0) + "");
        mApi = FullDressTabApi.getApi();
        createSinglefulldress("1", flagTag, "");
    }

    @Override
    public int getLayout() {
        return R.layout.activity_full_dress_tab;
    }

    @OnClick({R.id.iv_fulldess_list_back, R.id.btn_fulldess_gril, R.id.btn_fulldess_boy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fulldess_list_back:
                finish();
                break;
            case R.id.btn_fulldess_gril:
                btnFulldessGril.setBackgroundResource(R.drawable.switch_button_left_checked);
                btnFulldessBoy.setBackgroundResource(R.drawable.switch_button_right);
                btnFulldessBoy.setTextColor(getResources().getColor(R.color.login_color_btn));
                btnFulldessGril.setTextColor(getResources().getColor(R.color.white));
                createSinglefulldress("1", flagTag, "");
                break;
            case R.id.btn_fulldess_boy:
                btnFulldessGril.setBackgroundResource(R.drawable.switch_button_left);
                btnFulldessBoy.setBackgroundResource(R.drawable.switch_button_right_checked);
                btnFulldessBoy.setTextColor(getResources().getColor(R.color.white));
                btnFulldessGril.setTextColor(getResources().getColor(R.color.login_color_btn));
                createSinglefulldress("0", flagTag, "");
                break;
        }
    }

    private Callback<FullDressTabResult> callbackfulldress = new Callback<FullDressTabResult>() {
        @Override
        public void onResponse(Call<FullDressTabResult> call, Response<FullDressTabResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mFDTabRes = response.body();
                if ("00".equals(mFDTabRes.getCode())) {
                    mData = mFDTabRes.getData();
                    initDatas(mData);
                    initViewPager();
                    initTabLayout();
                } else {
                    ToastUtil.showShort(FullDressTabActivity.this, mFDTabRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<FullDressTabResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSinglefulldress(String sex, String type, String name) {
        Call<FullDressTabResult> call = mApi.getService().createCommitList(token, sex, type);
        call.enqueue(callbackfulldress);
    }

    private void initTabLayout() {
        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        tlFulldressTab.setTabMode(/*tabCount <= MOVABLE_COUNT ? TabLayout.MODE_FIXED :*/ TabLayout.MODE_SCROLLABLE);
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

    private void initDatas(List<FullDressTabResult.Data> mList) {
        tabs = new ArrayList<>();
        fragments = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            tabs.add(mList.get(i).getName());
            FullDressFragment fragment = new FullDressFragment(mList.get(i),sex,flagTag);
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
