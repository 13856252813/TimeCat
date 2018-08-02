package com.cary.activity.timecat.fragment.index.fulldress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.alipay.AlipayClass;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.ConfirmOrderActivity;
import com.cary.activity.timecat.fragment.index.selectfriendpay.SelectFriendPayActivity;
import com.cary.activity.timecat.fragment.index.wechatpay.WeChatPayClass;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 租借订单
 */
public class FullDressOrderActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.tv_full_dress_order_name)
    TextView tvFullDressOrderName;
    @BindView(R.id.tv_full_dress_ord_money)
    TextView tvFullDressOrdMoney;
    @BindView(R.id.tv_full_dress_order_discount_money)
    TextView tvFullDressOrderDiscountMoney;
    @BindView(R.id.iv_full_dress_order_check_logo)
    ImageView ivFullDressOrderCheckLogo;
    @BindView(R.id.iv_full_dress_order_check_img_alipay)
    ImageView ivFullDressOrderCheckImgAlipay;
    @BindView(R.id.tv_full_dress_order_paymethod_alipay_text)
    TextView tvFullDressOrderPaymethodAlipayText;
    @BindView(R.id.rl_full_dress_order_paymethod_alipay)
    RelativeLayout rlFullDressOrderPaymethodAlipay;
    @BindView(R.id.iv_full_dress_order_check_logo_wechat)
    ImageView ivFullDressOrderCheckLogoWechat;
    @BindView(R.id.iv_full_dress_order_check_img_wechat)
    ImageView ivFullDressOrderCheckImgWechat;
    @BindView(R.id.tv_full_dress_order_paymethod_wechat_text)
    TextView tvFullDressOrderPaymethodWechatText;
    @BindView(R.id.rl_full_dress_order_paymethod_wechat)
    RelativeLayout rlFullDressOrderPaymethodWechat;
    @BindView(R.id.iv_full_dress_order_check_logo_friend)
    ImageView ivFullDressOrderCheckLogoFriend;
    @BindView(R.id.iv_full_dress_order_check_img_friend)
    ImageView ivFullDressOrderCheckImgFriend;
    @BindView(R.id.tv_full_dress_order_check_img_friend_text)
    TextView tvFullDressOrderCheckImgFriendText;
    @BindView(R.id.rl_full_dress_order_paymethod_friend)
    RelativeLayout rlFullDressOrderPaymethodFriend;
    @BindView(R.id.tv_full_dress_order_summoney)
    TextView tvFullDressOrderSummoney;
    @BindView(R.id.btn_full_dress_order_confirm)
    Button btnFullDressOrderConfirm;

    private int isSelectPay = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_full_dress_order);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        ButterKnife.bind(this);

        titleText.setText("支付订单");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        tvFullDressOrdMoney.setText("预定金1");
        tvFullDressOrderDiscountMoney.setText("折扣2");
        tvFullDressOrderSummoney.setText("2343");
    }

    @OnClick({R.id.title_back, R.id.rl_full_dress_order_paymethod_alipay,
            R.id.rl_full_dress_order_paymethod_wechat,
            R.id.rl_full_dress_order_paymethod_friend,
            R.id.btn_full_dress_order_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.rl_full_dress_order_paymethod_alipay:
                payMethodSelect(tvFullDressOrderPaymethodAlipayText, ivFullDressOrderCheckImgAlipay, true);
                payMethodSelect(tvFullDressOrderPaymethodWechatText, ivFullDressOrderCheckImgWechat, false);
                payMethodSelect(tvFullDressOrderCheckImgFriendText, ivFullDressOrderCheckImgFriend, false);
                isSelectPay = 1;
                break;
            case R.id.rl_full_dress_order_paymethod_wechat:
                payMethodSelect(tvFullDressOrderPaymethodAlipayText, ivFullDressOrderCheckImgAlipay, false);
                payMethodSelect(tvFullDressOrderPaymethodWechatText, ivFullDressOrderCheckImgWechat, true);
                payMethodSelect(tvFullDressOrderCheckImgFriendText, ivFullDressOrderCheckImgFriend, false);
                isSelectPay = 2;
                break;
            case R.id.rl_full_dress_order_paymethod_friend:
                payMethodSelect(tvFullDressOrderPaymethodAlipayText, ivFullDressOrderCheckImgAlipay, false);
                payMethodSelect(tvFullDressOrderPaymethodWechatText, ivFullDressOrderCheckImgWechat, false);
                payMethodSelect(tvFullDressOrderCheckImgFriendText, ivFullDressOrderCheckImgFriend, true);
                isSelectPay = 3;
                break;
            case R.id.btn_full_dress_order_confirm:
                String paymoney = tvFullDressOrderSummoney.getText().toString().trim();
                AlipayClass ac = new AlipayClass(this);
                WeChatPayClass wcpc = new WeChatPayClass(this);
                if (isSelectPay == 1) {
                    ToastUtil.showShort(this, "支付宝支付金额：" + paymoney);
//                    ac.payV2();
                } else if (isSelectPay == 2) {
                    ToastUtil.showShort(this, "微信支付金额：" + paymoney);
//                    wcpc.WXPay();
                } else if (isSelectPay == 3) {
                    ToastUtil.showShort(this, "找人支付金额：" + paymoney);
                    startActivity(new Intent(this, SelectFriendPayActivity.class));
                }
                Intent intent = new Intent(this, ConfirmOrderActivity.class);

                startActivity(intent);
                break;
        }
    }

    private void payMethodSelect(TextView tv, ImageView iv, boolean isShow) {
        if (isShow) {
            tv.setVisibility(View.VISIBLE);
            iv.setImageDrawable(getResources().getDrawable(R.mipmap.checked));
        } else {
            tv.setVisibility(View.GONE);
            iv.setImageDrawable(getResources().getDrawable(R.mipmap.checked2));

        }
    }
}
