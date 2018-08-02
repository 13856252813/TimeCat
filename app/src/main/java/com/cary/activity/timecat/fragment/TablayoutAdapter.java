package com.cary.activity.timecat.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cary.activity.timecat.R;

import java.util.List;

/**
 * Created by Cary on 2018/4/8.
 */

public class TablayoutAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitles;
    private List<Integer> mIcons;
    private Context mContext;

    public TablayoutAdapter(FragmentManager fm, Context mContext, List<Fragment> mFragments, List<String> mTitles, List<Integer> mIcons) {
        super(fm);
        this.mContext = mContext;
        this.mIcons = mIcons;
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
    public View getTabView(int position){
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_item_layout, null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        tv.setText(mTitles.get(position));
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(mIcons.get(position));
        return view;
    }
}
