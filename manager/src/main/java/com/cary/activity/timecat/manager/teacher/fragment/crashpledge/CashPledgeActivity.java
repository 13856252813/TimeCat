package com.cary.activity.timecat.manager.teacher.fragment.crashpledge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.crashpledge.PayPhotoGraphyOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 押金
 */
@SuppressLint("Registered")
public class CashPledgeActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_cashpledge_number)
    TextView tvCashpledgeNumber;
    @BindView(R.id.tv_cashpledge_number_text)
    TextView tvCashpledgeNumberText;
    @BindView(R.id.btn_cash_pledge_charge)
    Button btnCashPledgeCharge;
    @BindView(R.id.btn_cash_pledge_outcharge)
    Button btnCashPledgeOutcharge;
    private int balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_pledge);
        ButterKnife.bind(this);

        titleText.setText("押金");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
        balance = getIntent().getIntExtra("balance", 0);
        tvCashpledgeNumber.setText(balance + "");
    }

    @OnClick({R.id.title_back, R.id.btn_cash_pledge_charge, R.id.btn_cash_pledge_outcharge})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_cash_pledge_charge:
//                ToastUtil.showShort(this, "充值押金");
                intent.setClass(this, PayPhotoGraphyOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_cash_pledge_outcharge:
//                ToastUtil.showShort(this, "退押金");
                intent.setClass(this, ReturnCrashPledgeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
