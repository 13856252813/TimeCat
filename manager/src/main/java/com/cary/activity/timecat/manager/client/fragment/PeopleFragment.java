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
import com.cary.activity.timecat.manager.client.myorder.confirmorder.SelectTeacherActivity;
import com.cary.activity.timecat.manager.client.people.EmployeeAddActivity;
import com.cary.activity.timecat.manager.client.people.EmployeeManagerActivity;
import com.cary.activity.timecat.manager.client.people.TeacherApplyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 人员管理
 */
public class PeopleFragment extends Fragment {

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
    private String[] data = {"老师管理", "老师入驻申请", "员工管理", "添加员工", "客户管理", "  ", " ", " ", " "};
    private int[] imgdata = {R.mipmap.tie, R.mipmap.add_manager, R.mipmap.student, R.mipmap.student2,
            R.mipmap.girl, 0, 0, 0, 0};
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
        titleHomeText.setText("人员管理");
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
//                        ToastUtil.showShort(getActivity(), "老师管理");
                        intent.setClass(getActivity(), SelectTeacherActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
//                        ToastUtil.showShort(getActivity(), "老师入驻申请");
                        intent.setClass(getActivity(), TeacherApplyActivity.class);
                        startActivity(intent);

                        break;
                    case 2:
//                        ToastUtil.showShort(getActivity(), "员工管理");
                        intent.setClass(getActivity(), EmployeeManagerActivity.class);
                        intent.putExtra("type", 0);
                        startActivity(intent);
                        break;
                    case 3:
//                        ToastUtil.showShort(getActivity(), "添加员工");
                        intent.setClass(getActivity(), EmployeeAddActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
//                        ToastUtil.showShort(getActivity(), "客户管理");
                        intent.setClass(getActivity(), EmployeeManagerActivity.class);
                        intent.putExtra("type", 1);
                        startActivity(intent);
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