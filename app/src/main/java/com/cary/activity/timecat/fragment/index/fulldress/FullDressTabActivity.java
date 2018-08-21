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
import android.widget.LinearLayout;
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
    @BindView(R.id.cloth_title)
    TextView mTextTitle;
    @BindView(R.id.layout_tab)
    LinearLayout mLayoutTab;

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

    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        flagTag = getIntent().getStringExtra("flagtag");
        if (!TextUtils.isEmpty(flagTag)) {
            if ("0".equals(flagTag)) {
                Log.v(TAG, "我是售卖区");
            } else if ("1".equals(flagTag)) {
                Log.v(TAG, "我是共享区");
            }
        }
        sex =getIntent().getIntExtra("sex",1);
        boolean isOrder= getIntent().getBooleanExtra("isOrder",false);
        if(isOrder){
            mTextTitle.setVisibility(View.VISIBLE);
            mLayoutTab.setVisibility(View.GONE);
        }

        sharePh = new SharedPreferencesHelper(this);
        sharePh.put("sex",sex);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = ((int) sharePh.getSharedPreference("id", 0) + "");
        mApi = FullDressTabApi.getApi();


        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        tlFulldressTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tlFulldressTab.setSelectedTabIndicatorColor(getResources().getColor(R.color.login_color_btn));
        tlFulldressTab.setSelectedTabIndicatorHeight((int) getResources().getDimension(R.dimen.two));
        tlFulldressTab.setupWithViewPager(viewpagerFulldress);

        createSinglefulldress(sex+"", flagTag, "");
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
                sex = 1;
                sharePh.put("sex",sex);
                btnFulldessGril.setBackgroundResource(R.drawable.switch_button_left_checked);
                btnFulldessBoy.setBackgroundResource(R.drawable.switch_button_right);
                btnFulldessBoy.setTextColor(getResources().getColor(R.color.login_color_btn));
                btnFulldessGril.setTextColor(getResources().getColor(R.color.white));
                createSinglefulldress("1", flagTag, "");
                break;
            case R.id.btn_fulldess_boy:
                sex = 0;
                sharePh.put("sex",sex);
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
                    viewpagerFulldress.setAdapter(myPagerAdapter);
                    myPagerAdapter.notifyDataSetChanged();
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
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = tlFulldressTab.getTabAt(i);
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.activity_full_dress_tab_view, tlFulldressTab, false);
            tv.setText(tabs.get(i));
            tab.setCustomView(tv);
        }
    }


    private void initDatas(List<FullDressTabResult.Data> mList) {
        getSupportFragmentManager().getFragments().clear();
        tabs = new ArrayList<>();
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.clear();
        for (int i = 0; i < mList.size(); i++) {
            tabs.add(mList.get(i).getName());
            FullDressFragment fragment = FullDressFragment.newInstance(mList.get(i), flagTag);
            fragments.add(fragment);
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
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

    }
}
