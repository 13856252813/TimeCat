package com.cary.activity.timecat.manager.teacher.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;

import java.util.Arrays;

/**
 * 抢单
 */
public class GrabFragment extends Fragment {

    private View viewContent;
    private TabLayout tab_essence;
    private ViewPager vp_essence;
    private TextView tvTitle;
    private TextView home_title_right;
    private RelativeLayout titleHomeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_grab_layout, container, false);
        initConentView(viewContent);
        initData();

        return viewContent;
    }

    public void initConentView(View viewContent) {
        this.tab_essence = (TabLayout) viewContent.findViewById(R.id.tab_essence);
        this.vp_essence = (ViewPager) viewContent.findViewById(R.id.vp_essence);
        this.tvTitle = (TextView) viewContent.findViewById(R.id.title_home_text);
        this.home_title_right = (TextView) viewContent.findViewById(R.id.home_title_right);
        this.titleHomeLayout = (RelativeLayout) viewContent.findViewById(R.id.title_home_layout);
        this.tvTitle.setText("抢单");
        this.home_title_right.setVisibility(View.VISIBLE);
        titleHomeLayout.setBackgroundColor(getResources().getColor(R.color.white));
        this.tvTitle.setTextColor(getResources().getColor(R.color.color_three));

        this.home_title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtil.showShort(getActivity(), "地址");
            }
        });

    }

    public void initData() {
        //获取标签数据
        String[] titles = getResources().getStringArray(R.array.home_tab);

        //创建一个viewpager的adapter
        IndexFragmentAdapter adapter = new IndexFragmentAdapter(getFragmentManager(), Arrays.asList(titles));
        this.vp_essence.setAdapter(adapter);

        //将TabLayout和ViewPager关联起来
        this.tab_essence.setupWithViewPager(this.vp_essence);
    }
}