package com.cary.activity.timecat.manager.crashpledge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CrashPledgeActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_crash_pledge_text)
    TextView tvCrashPledgeText;
    @BindView(R.id.tv_crash_pledge)
    TextView tvCrashPledge;
    @BindView(R.id.btn_payment_crash_pledge)
    Button btnPaymentCrashPledge;

    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_pledge);
        ButterKnife.bind(this);

        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        titleText.setText("押金");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

    }

    @OnClick({R.id.title_back, R.id.btn_payment_crash_pledge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_payment_crash_pledge:
//                ToastUtil.showShort(this, "缴纳押金");
                String crashPledge = tvCrashPledge.getText().toString().trim();
                Intent intent = new Intent(this,PayPhotoGraphyOrderActivity.class);
                intent.putExtra("crashPledge",crashPledge);
                startActivity(intent);
                break;
        }
    }
}
