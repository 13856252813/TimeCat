package com.cary.activity.timecat.fragment.index.photography;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayOrderFailedActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pay_order_failed);
        ButterKnife.bind(this);

        titleText.setText("支付失败");
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        rlTitle.setBackgroundColor(getResources().getColor(R.color.white));

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_pay_order_failed;
    }
}
