package com.cary.activity.timecat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.news.NewsActivity;
import com.cary.activity.timecat.fragment.index.selectstore.SelectStoreActivity;
import com.cary.activity.timecat.fragment.index.timeclouddish.TimeCloudDishActivity;
import com.cary.activity.timecat.fragment.look.integral.IntegralMallActivity;
import com.cary.activity.timecat.fragment.look.interact.InteractActivity;
import com.cary.activity.timecat.fragment.person.systemmessage.SystemMessageActivity;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.ButterKnife;

/**
 * Created by Cary on 2018/4/8.
 * 发现
 */

public class LookFragment extends Fragment implements View.OnClickListener {

    private ImageView titleBackNotiy;
    private TextView titleHomeText;
    private TextView homeTitleRight;
    private ImageView redCirclePoint;
    private RelativeLayout titleHomeLayout;

    private LinearLayout ll_main_look_interact, ll_main_look_wedding_card, ll_time_heading,
            ll_score_shopping, ll_look_online_selectphoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_look, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
//        StatusBarUtil.setPaddingSmart(getActivity(), view.findViewById(R.id.toolbar));
        ButterKnife.bind(getActivity());

        titleBackNotiy = (ImageView) root.findViewById(R.id.title_home_notify);
        titleHomeText = (TextView) root.findViewById(R.id.title_home_text);
        homeTitleRight = (TextView) root.findViewById(R.id.home_title_right);
        redCirclePoint = (ImageView) root.findViewById(R.id.redCirclePoint);
        titleHomeLayout = (RelativeLayout) root.findViewById(R.id.title_home_layout);
        homeTitleRight.setVisibility(View.GONE);

        ll_main_look_interact = root.findViewById(R.id.ll_main_look_interact);
        ll_main_look_wedding_card = root.findViewById(R.id.ll_main_look_wedding_card);
        ll_time_heading = root.findViewById(R.id.ll_time_heading);
        ll_score_shopping = root.findViewById(R.id.ll_score_shopping);
        ll_look_online_selectphoto = root.findViewById(R.id.ll_look_online_selectphoto);

        titleBackNotiy.setOnClickListener(this);
        homeTitleRight.setOnClickListener(this);
        ll_main_look_interact.setOnClickListener(this);
        ll_main_look_wedding_card.setOnClickListener(this);
        ll_time_heading.setOnClickListener(this);
        ll_score_shopping.setOnClickListener(this);
        ll_look_online_selectphoto.setOnClickListener(this);

        titleBackNotiy.setVisibility(View.VISIBLE);
        homeTitleRight.setVisibility(View.VISIBLE);
        //有消息则 显示红点 否则不显示
        if (true) {
            redCirclePoint.setVisibility(View.VISIBLE);
        } else {
            redCirclePoint.setVisibility(View.GONE);
        }
        titleBackNotiy.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.bell));
        titleHomeLayout.setBackgroundColor(getResources().getColor(R.color.white));
        titleHomeText.setText("发现");
        titleHomeText.setTextColor(getResources().getColor(R.color.color_three));

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.title_home_notify:
                //通知
//                ToastUtil.showShort(getActivity(), "通知");
                intent.setClass(getActivity(), SystemMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.home_title_right:
                intent.setClass(getActivity(), SelectStoreActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_main_look_interact:
                //互动吧
                intent.setClass(getActivity(), InteractActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_main_look_wedding_card:
                //微喜帖
                ToastUtil.showShort(getActivity(), "微喜帖请在电脑端制作~~");
                break;
            case R.id.ll_time_heading:
                //时光头条
//                ToastUtil.showShort(getActivity(), "时光头条");
                intent.setClass(getActivity(), NewsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_score_shopping:
//                ToastUtil.showShort(getActivity(), "积分商城");
                intent.setClass(getActivity(), IntegralMallActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_look_online_selectphoto:
//                ToastUtil.showShort(getActivity(), "在线选片");
                intent.setClass(getActivity(), TimeCloudDishActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
