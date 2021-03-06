package com.cary.activity.timecat.manager.client.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.client.finance.FinanceStatisicActivity;
import com.cary.activity.timecat.manager.client.myorder.MyOrderActivity;
import com.cary.activity.timecat.manager.client.pushmanager.PushManagerActivity;
import com.cary.activity.timecat.manager.client.question.QuestionActivity;
import com.cary.activity.timecat.manager.client.withdraw.WithDrawActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 管理端 财务管理
 */
public class FinaceFragment extends Fragment {

    @BindView(R.id.title_home_notify)
    ImageView titleHomeNotify;
    @BindView(R.id.redCirclePoint)
    ImageView redCirclePoint;
    @BindView(R.id.rl_title_left)
    RelativeLayout rlTitleLeft;
    @BindView(R.id.title_home_text)
    TextView titleHomeText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.home_title_right)
    TextView homeTitleRight;
    @BindView(R.id.title_home_layout)
    RelativeLayout titleHomeLayout;
    Unbinder unbinder;
    private View v;
    private RecyclerView recyclerView;
    //    private TextView top_center;
    private String[] data = {"订单管理", "提现申请", "投诉建议", "退款审核", "推送管理", "财务统计", " ", " ", " "};
    private int[] imgdata = {R.mipmap.clipboard, R.mipmap.wallet, R.mipmap.customerservice, R.mipmap.refund,
            R.mipmap.paperplane, R.mipmap.pie_chart, 0, 0, 0};
    private RecyclerGridViewAdapter recyclerGridViewAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_store, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.fragment_recyclerview);
//        top_center = (TextView) v.findViewById(R.id.top_center);
        GridLayoutManager mgr = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mgr);
//        int spanCount = 4;//跟布局里面的spanCount属性是一致的
//        int spacing = 2;//每一个矩形的间距
//        boolean includeEdge = false;//如果设置成false那边缘地带就没有间距s
//        //设置每个item间距
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        //设置适配器
        recyclerGridViewAdapter = new RecyclerGridViewAdapter(getActivity(), data, imgdata);
        recyclerView.setAdapter(recyclerGridViewAdapter);
        onRecyclerItemClickListener();
        unbinder = ButterKnife.bind(this, v);

        titleHomeLayout.setBackgroundColor(getResources().getColor(R.color.white));
        titleHomeText.setText("财务管理");
        titleHomeText.setTextColor(getResources().getColor(R.color.color_three));
        return v;
    }

    public void initConentView(View viewContent) {

    }

    private void onRecyclerItemClickListener() {
        recyclerGridViewAdapter.setOnRecyclerViewItemListener(new RecyclerGridViewAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
//                Toast.makeText(getActivity(), "onClick:" + position, Toast.LENGTH_SHORT).show();
//                "门店信息管理", "套餐管理", "产品管理", "服装管理",
                Intent intent = new Intent();
                switch (position) {
                    case 0:
//                        ToastUtil.showShort(getActivity(), "订单管理");
                        intent.setClass(getActivity(), MyOrderActivity.class);
                        intent.putExtra("type",0);
                        startActivity(intent);

                        break;
                    case 1:
//                        ToastUtil.showShort(getActivity(), "提现申请");
                        startActivity(new Intent(getActivity(), WithDrawActivity.class));

                        break;
                    case 2:
//                        ToastUtil.showShort(getActivity(), "投诉建议");
                        startActivity(new Intent(getActivity(), QuestionActivity.class));
                        break;
                    case 3:
//                        ToastUtil.showShort(getActivity(), "退款审核");
                        intent.setClass(getActivity(), MyOrderActivity.class);
                        intent.putExtra("type",1);
                        startActivity(intent);
                        break;
                    case 4:
//                        ToastUtil.showShort(getActivity(), "推送管理");
                        startActivity(new Intent(getActivity(), PushManagerActivity.class));
                        break;
                    case 5:
//                        ToastUtil.showShort(getActivity(), "财务统计");
                        startActivity(new Intent(getActivity(), FinanceStatisicActivity.class));

                        break;
                }
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(getActivity(), "onLongClick:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}