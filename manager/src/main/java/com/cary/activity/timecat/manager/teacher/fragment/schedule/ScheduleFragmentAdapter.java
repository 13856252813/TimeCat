package com.cary.activity.timecat.manager.teacher.fragment.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cary.activity.timecat.manager.teacher.fragment.ContentFragment;

import java.util.List;

//继承FragmentStatePagerAdapter
    public class ScheduleFragmentAdapter extends FragmentStatePagerAdapter {

        public static final String TAB_TAG = "@dream@";

        private List<String> mTitles;

        public ScheduleFragmentAdapter(FragmentManager fm, List<String> titles) {
            super(fm);
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            //初始化Fragment数据
            ScheduleListFragment fragment = new ScheduleListFragment();
            String[] title = mTitles.get(position).split(TAB_TAG);
            fragment.setType(Integer.parseInt(title[1]));
            fragment.setTitle(title[0]);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position).split(TAB_TAG)[0];
        }
    }