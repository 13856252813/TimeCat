package com.cary.activity.timecat.manager.client.myorder.orderdetial;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单信息
 */
@SuppressLint("Registered")
public class OrderDetialActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_order_information_text)
    TextView tvOrderInformationText;
    @BindView(R.id.tv_order_information_line)
    TextView tvOrderInformationLine;
    @BindView(R.id.ll_order_information)
    LinearLayout llOrderInformation;
    @BindView(R.id.tv_cloth_mould_text)
    TextView tvClothMouldText;
    @BindView(R.id.tv_cloth_mould_line)
    TextView tvClothMouldLine;
    @BindView(R.id.ll_cloth_mould)
    LinearLayout llClothMould;
    @BindView(R.id.tv_style_result_text)
    TextView tvStyleResultText;
    @BindView(R.id.tv_style_result_line)
    TextView tvStyleResultLine;
    @BindView(R.id.ll_style_result)
    LinearLayout llStyleResult;
    @BindView(R.id.ll_order_detial_title)
    LinearLayout llOrderDetialTitle;
    @BindView(R.id.lincontent)
    LinearLayout lincontent;

    private String idStr ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detial);
        ButterKnife.bind(this);
        titleText.setText("订单详情");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
        titleTextRight.setVisibility(View.VISIBLE);
        titleTextRight.setText("");
        idStr = getIntent().getStringExtra("id");
        //调用切换Fragmnet方法
        changeFragmnet(new OrderInfomationFragment());

    }

    @OnClick({R.id.title_back, R.id.ll_order_information, R.id.ll_cloth_mould, R.id.ll_style_result})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_order_information:
                changeFragmnet(new OrderInfomationFragment());
                SetTextColor(tvOrderInformationText, tvOrderInformationLine, true);
                SetTextColor(tvClothMouldText, tvClothMouldText, false);
                SetTextColor(tvStyleResultText, tvStyleResultLine, false);

                break;
            case R.id.ll_cloth_mould:
                changeFragmnet(new OrderClothStageFragment());
                SetTextColor(tvOrderInformationText, tvOrderInformationLine, false);
                SetTextColor(tvClothMouldText, tvClothMouldLine, true);
                SetTextColor(tvStyleResultText, tvStyleResultLine, false);

                break;
            case R.id.ll_style_result:
                changeFragmnet(new OrderInfomationFragment());
                SetTextColor(tvOrderInformationText, tvOrderInformationLine, false);
                SetTextColor(tvClothMouldText, tvClothMouldText, false);
                SetTextColor(tvStyleResultText, tvStyleResultLine, true);

                break;

        }
    }

    //切换fragment的方法
    public void changeFragmnet(Fragment fr) {

        //第一步：得到fragment管理类
        FragmentManager manager = getSupportFragmentManager();
        //得到事务
        FragmentTransaction beginTransaction = manager.beginTransaction();
        //调用replace 添加fragment
        beginTransaction.replace(R.id.lincontent, fr);
        //提交
        beginTransaction.commit();

    }

    private void SetTextColor(TextView text, TextView line, boolean isShow) {
        if (isShow) {
            text.setTextColor(getResources().getColor(R.color.login_color_btn));
            line.setBackgroundColor(getResources().getColor(R.color.login_color_btn));
        } else {
            text.setTextColor(getResources().getColor(R.color.color_three));
            line.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
    }
}
