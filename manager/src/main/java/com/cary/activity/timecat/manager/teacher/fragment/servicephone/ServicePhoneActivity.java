package com.cary.activity.timecat.manager.teacher.fragment.servicephone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

@SuppressLint("Registered")
public class ServicePhoneActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_information)
    TextView tvInformation;
    @BindView(R.id.ll_information)
    LinearLayout llInformation;
    @BindView(R.id.tv_consociation)
    TextView tvConsociation;
    @BindView(R.id.ll_consociation)
    LinearLayout llConsociation;
    @BindView(R.id.tv_bigcustomer)
    TextView tvBigcustomer;
    @BindView(R.id.ll_bigcustomer)
    LinearLayout llBigcustomer;
    @BindView(R.id.tv_wechatservice)
    TextView tvWechatservice;
    @BindView(R.id.ll_wechatservice)
    LinearLayout llWechatservice;

    private String infomationphone = "";
    private String consociation = "";
    private String bigcustomer = "";
    private String weichatservice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_phone);
        ButterKnife.bind(this);

        titleText.setText("客服电话");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

    }

    @OnClick({R.id.title_back, R.id.ll_information, R.id.ll_consociation, R.id.ll_bigcustomer, R.id.ll_wechatservice})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_information:
                //用intent启动拨打电话
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + infomationphone));
                startActivity(intent);
                break;
            case R.id.ll_consociation:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + consociation));
                startActivity(intent);
                break;
            case R.id.ll_bigcustomer:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + bigcustomer));
                startActivity(intent);
                break;
            case R.id.ll_wechatservice:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + weichatservice));
                startActivity(intent);
                break;
        }
    }
}
