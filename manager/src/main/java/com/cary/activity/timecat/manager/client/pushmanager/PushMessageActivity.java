package com.cary.activity.timecat.manager.client.pushmanager;

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
 * 推送消息
 */
@SuppressLint("Registered")
public class PushMessageActivity extends AppCompatActivity {

    private static final String TAG = PushMessageActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_push_all_text)
    TextView tvPushAllText;
    @BindView(R.id.tv_push_all_line)
    TextView tvPushAllLine;
    @BindView(R.id.ll_push_all)
    LinearLayout llPushAll;
    @BindView(R.id.tv_single_text)
    TextView tvSingleText;
    @BindView(R.id.tv_single_line)
    TextView tvSingleLine;
    @BindView(R.id.ll_single)
    LinearLayout llSingle;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.lincontent)
    LinearLayout lincontent;

    private int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_push_manager);
        ButterKnife.bind(this);
        titleText.setText("发送推送消息");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
        tvPushAllText.setText("全体推送");
        tvSingleText.setText("个人推送");

        type = getIntent().getIntExtra("type", 1);

        //调用切换Fragmnet方法
        if (type == 1) {
            changeFragmnet(new PushAllMsgFragment(type));
        } else if (type == 2) {
            changeFragmnet(new PushSignalMsgFragment(type));
        }
    }


    @OnClick({R.id.title_back, R.id.ll_push_all, R.id.ll_single})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_push_all:
                changeFragmnet(new PushAllMsgFragment(type));
                SetTextColor(tvPushAllText, tvPushAllLine, true);
                SetTextColor(tvSingleText, tvSingleLine, false);

                break;
            case R.id.ll_single:
                changeFragmnet(new PushSignalMsgFragment(type));
                SetTextColor(tvPushAllText, tvPushAllLine, false);
                SetTextColor(tvSingleText, tvSingleLine, true);

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
