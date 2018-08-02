package com.cary.activity.timecat.manager.crashpledge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.alipay.AlipayClass;
import com.cary.activity.timecat.manager.util.ToastUtil;
import com.cary.activity.timecat.manager.wechatpay.WeChatPayClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在线预订
 */
public class PayPhotoGraphyOrderActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.pay_photo_graphy_order_beforehand_money)
    TextView payPhotoGraphyOrderBeforehandMoney;
    @BindView(R.id.pay_photo_graphy_order_discount_money)
    TextView payPhotoGraphyOrderDiscountMoney;
    @BindView(R.id.payorderphotography_check_img_alipay)
    ImageView payorderphotographyCheckImgAlipay;
    @BindView(R.id.pay_photo_graphy_order_paymethod_alipay_text)
    TextView payPhotoGraphyOrderPaymethodAlipayText;
    @BindView(R.id.pay_photo_graphy_order_paymethod_alipay)
    RelativeLayout payPhotoGraphyOrderPaymethodAlipay;
    @BindView(R.id.payorderphotography_check_img_wechat)
    ImageView payorderphotographyCheckImgWechat;
    @BindView(R.id.pay_photo_graphy_order_paymethod_wechat_text)
    TextView payPhotoGraphyOrderPaymethodWechatText;
    @BindView(R.id.pay_photo_graphy_order_paymethod_wechat)
    RelativeLayout payPhotoGraphyOrderPaymethodWechat;
    @BindView(R.id.paygraphy_summoney)
    TextView paygraphySummoney;
    @BindView(R.id.payorderphotography_okbutton)
    Button payorderphotographyOkbutton;
    private  int isSelectPay = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_photo_graphy_order);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        ButterKnife.bind(this);

        titleText.setText("支付订单");
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
        rlTitle.setBackgroundColor(getResources().getColor(R.color.white));
        payPhotoGraphyOrderBeforehandMoney.setText("预定金1");
        payPhotoGraphyOrderDiscountMoney.setText("折扣2");
        paygraphySummoney.setText("3");
    }

    @OnClick({R.id.title_back, R.id.pay_photo_graphy_order_paymethod_alipay, R.id.pay_photo_graphy_order_paymethod_wechat, R.id.payorderphotography_okbutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.pay_photo_graphy_order_paymethod_alipay:
                payMethodSelect(payPhotoGraphyOrderPaymethodAlipayText,payorderphotographyCheckImgAlipay,true);
                payMethodSelect(payPhotoGraphyOrderPaymethodWechatText,payorderphotographyCheckImgWechat,false);
                isSelectPay = 1;
                break;
            case R.id.pay_photo_graphy_order_paymethod_wechat:
                payMethodSelect(payPhotoGraphyOrderPaymethodAlipayText,payorderphotographyCheckImgAlipay,false);
                payMethodSelect(payPhotoGraphyOrderPaymethodWechatText,payorderphotographyCheckImgWechat,true);
                isSelectPay = 2;
                break;
            case R.id.payorderphotography_okbutton:
                String paymoney = paygraphySummoney.getText().toString().trim();
                AlipayClass ac = new AlipayClass(this);
                WeChatPayClass wcpc = new WeChatPayClass(this);
                if(isSelectPay==1){
                    ToastUtil.showShort(this,"支付宝支付金额："+paymoney);
                    ac.payV2();
                }else if(isSelectPay==2){
                    ToastUtil.showShort(this,"微信支付金额："+paymoney);
                    wcpc.WXPay();
                }
                break;
        }
    }

    private void payMethodSelect(TextView tv,ImageView iv,boolean isShow){
        if(isShow){
            tv.setVisibility(View.VISIBLE);
            iv.setImageDrawable(getResources().getDrawable(R.mipmap.checked));
        }else{
            tv.setVisibility(View.GONE);
            iv.setImageDrawable(getResources().getDrawable(R.mipmap.checked2));

        }
    }
}
