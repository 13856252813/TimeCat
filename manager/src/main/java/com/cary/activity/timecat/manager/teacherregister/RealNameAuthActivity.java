package com.cary.activity.timecat.manager.teacherregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealNameAuthActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.et_real_name_auth)
    EditText etRealNameAuth;
    @BindView(R.id.et_real_name_auth_cardno)
    EditText etRealNameAuthCardno;
    @BindView(R.id.tv_add_cardno_front)
    TextView tvAddCardnoFront;
    @BindView(R.id.iv_add_cardno_front)
    ImageView ivAddCardnoFront;
    @BindView(R.id.tv_add_cardno_reverse)
    TextView tvAddCardnoReverse;
    @BindView(R.id.iv_add_cardno_reverse)
    ImageView ivAddCardnoReverse;
    @BindView(R.id.btn_real_name_auth_next)
    Button btnRealNameAuthNext;

    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_real_name_auth);
        ButterKnife.bind(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        titleText.setText("登录账户");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

    }

    @OnClick({R.id.title_back, R.id.tv_add_cardno_front, R.id.iv_add_cardno_front, R.id.tv_add_cardno_reverse, R.id.iv_add_cardno_reverse, R.id.btn_real_name_auth_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tv_add_cardno_front:
                ToastUtil.showShort(this, "正面");
                break;
            case R.id.iv_add_cardno_front:
                ToastUtil.showShort(this, "正面iv");

                break;
            case R.id.tv_add_cardno_reverse:
                ToastUtil.showShort(this, "反面");

                break;
            case R.id.iv_add_cardno_reverse:
                ToastUtil.showShort(this, "反面iv");

                break;
            case R.id.btn_real_name_auth_next:
                startActivity(new Intent(this, CommitSuccessActivity.class));
                break;
        }
    }
}
