package com.cary.activity.timecat.manager.client.myorder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的订单
 */
@SuppressLint("Registered")
public class MyOrderActivity extends AppCompatActivity {

    private static final String TAG = MyOrderActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_tobecompleted_text)
    TextView tvTobecompletedText;
    @BindView(R.id.tv_tobecompleted_line)
    TextView tvTobecompletedLine;
    @BindView(R.id.ll_tobecompleted)
    LinearLayout llTobecompleted;
    @BindView(R.id.tv_completed_text)
    TextView tvCompletedText;
    @BindView(R.id.tv_completed_line)
    TextView tvCompletedLine;
    @BindView(R.id.ll_completed)
    LinearLayout llCompleted;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.lincontent)
    LinearLayout lincontent;
    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", 0);
        if (type == 0) {
            titleText.setText("我的订单");
        } else if (type == 1) {
            titleText.setText("退款申请");
        }
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

        //调用切换Fragmnet方法
        changeFragmnet(new ToBeCompletedFragment(type));
    }


    @OnClick({R.id.title_back, R.id.ll_tobecompleted, R.id.ll_completed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_tobecompleted:
                changeFragmnet(new ToBeCompletedFragment(type));
                SetTextColor(tvTobecompletedText, tvTobecompletedLine, true);
                SetTextColor(tvCompletedText, tvCompletedLine, false);

                break;
            case R.id.ll_completed:
                changeFragmnet(new CompletedFragment(type));
                SetTextColor(tvTobecompletedText, tvTobecompletedLine, false);
                SetTextColor(tvCompletedText, tvCompletedLine, true);

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
