package com.cary.activity.timecat.fragment.index.basicser;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicServiceActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recyclerViewBasicService)
    RecyclerView recyclerViewBasicService;

    private List<BasicServiceDataBean> mDateBeen = new ArrayList<>();

    private String titleStr[]={"底片全送","专车接送","满意在付款","送货上门","赠送U盘","一对一拍摄","VIP化妆间"};
    private String descStr[] = {"拍摄底片全部赠送，拍多少送多少，绝无任何隐形消费",
            "拍摄当天本地市区内专车接送，享受全天极致服务",
            "承诺所有套餐支付定金即可拍照，拍照满意后再付款，不满意无条件重拍。",
            "产品厂家杨哥筛选，婚照成品一体包装，全国包邮送货上门。(偏远地区或物流不到的地区需用户自提)",
            "赠送8G定制U盘，拷贝所有底片、精修片",
            "拍摄当天摄影团队一对一服务，全程沟通式定制拍摄，照片立拍立看",
            "享受独立化妆间包房，免费提供茶饮"};

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_service);
        ButterKnife.bind(this);

        titleText.setText("基础服务");
        titleText.setTextColor(Color.RED);
        titleBack.setImageResource(R.mipmap.basic_back);

        initData();

//创建适配器adapter对象 参数1.上下文 2.数据加载集合
        RecyclerViewBServiceAdapter recyclerViewGridAdapter = new RecyclerViewBServiceAdapter(this,mDateBeen);
//设置适配器
        recyclerViewBasicService.setAdapter(recyclerViewGridAdapter);
//布局管理器对象 参数1.上下文 2.规定显示的行数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
//通过布局管理器可以控制条目排列的顺序 true反向显示 false正常显示(默认)
        gridLayoutManager.setReverseLayout(false);
//设置RecycleView显示的方向是水平还是垂直
//GridLayout.HORIZONTAL水平 GridLayout.VERTICAL默认垂直
// 三元运算符
        gridLayoutManager.setOrientation(true ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
//设置布局管理器， 参数linearLayoutManager对象
        recyclerViewBasicService.setLayoutManager(gridLayoutManager);
        recyclerViewGridAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                ToastUtil.showShort(BasicServiceActivity.this, "Grid 1 postion:" + postion);
            }
        });
    }

    private void initData() {
        for(int i=0;i<titleStr.length;i++){
            mDateBeen.add(new BasicServiceDataBean(titleStr[i],descStr[i]));
        }

    }


    @OnClick({R.id.title_back, R.id.title_text, R.id.rlTitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_text:
                break;
            case R.id.rlTitle:
                break;
            default:
                break;
        }
    }
}
