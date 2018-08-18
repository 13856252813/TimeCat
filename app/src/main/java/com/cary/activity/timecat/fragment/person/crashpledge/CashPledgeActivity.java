package com.cary.activity.timecat.fragment.person.crashpledge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.photography.PayPhotoGraphyOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 押金
 */
public class CashPledgeActivity extends BaseActivity {

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
    private double totalDeposit, deposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        titleText.setText("押金");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        totalDeposit = getIntent().getDoubleExtra("totalDeposit", 0);
        deposit = getIntent().getDoubleExtra("deposit", 0);
        tvCashpledgeNumber.setText(totalDeposit + "");
        tvCashpledgeNumberText.setText("(¥" + deposit + "可退款)");
    }

    @Override
    public int getLayout() {
        return R.layout.activity_cash_pledge;
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
                intent.putExtra("deposit", deposit);
                startActivity(intent);
                break;
        }
    }
}
