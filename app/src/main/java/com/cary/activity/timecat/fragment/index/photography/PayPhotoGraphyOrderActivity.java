package com.cary.activity.timecat.fragment.index.photography;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.alipay.AlipayClass;
import com.cary.activity.timecat.fragment.index.selectfriendpay.SelectFriendPayActivity;
import com.cary.activity.timecat.fragment.index.setmealdetial.SetMealDetialResult;
import com.cary.activity.timecat.fragment.index.wechatpay.WeChatPayClass;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.modelbean.ModelBeanData;
import com.cary.activity.timecat.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 在线预订
 */
public class PayPhotoGraphyOrderActivity extends BaseActivity {

    private static final String TAG = PayPhotoGraphyOrderActivity.class.getSimpleName();

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
    @BindView(R.id.payorderphotography_check_img_friend)
    ImageView payorderphotographyCheckImgFriend;
    @BindView(R.id.payorderphotography_check_img_friend_text)
    TextView payorderphotographyCheckImgFriendText;
    @BindView(R.id.pay_photo_graphy_order_paymethod_friend)
    RelativeLayout payPhotoGraphyOrderPaymethodFriend;
    @BindView(R.id.paygraphy_summoney)
    TextView paygraphySummoney;
    @BindView(R.id.payorderphotography_okbutton)
    Button payorderphotographyOkbutton;

    private int isSelectPay = 1;//支付方式 1支付宝，2微信 3朋友代付
    private String payType = "Alipay";
    private SetMealDetialResult mDetialRes;
    private double frontMoney;//定金
    private double PayMoney;//支付金额

    private String token;
    private String id;//套餐
    private int userId;
    private SharedPreferencesHelper sharedPreferencesHelper;

    private PayOrderApi mPayApi;
    private PayOrderResult mPayOrderRes;

    private AlipayClass ac;
    private WeChatPayClass wcpc;

    private String SumMoney;//显示要支付的总金额
    private int clothId;//服装id
    private String orderCount;//数量
    private int AddressId;//地址id

    private int payFlag = 0;//0 套餐支付 1服装支付
    private PayClothResult payRes;

    private int photoFlag;//2租借1购买3共享
    private RentOrderResult rentRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        titleText.setText("支付订单");
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        rlTitle.setBackgroundColor(getResources().getColor(R.color.white));

        //套餐支付
        mDetialRes = (SetMealDetialResult) getIntent().getSerializableExtra("detialresult");
        if (mDetialRes != null) {
            frontMoney = mDetialRes.getData().getFrontMoney();
            if (frontMoney != 0) {
                payPhotoGraphyOrderBeforehandMoney.setText(frontMoney + "");
                PayMoney = frontMoney;
            } else {
                payPhotoGraphyOrderBeforehandMoney.setText(mDetialRes.getData().getPrice() + "");
                PayMoney = mDetialRes.getData().getPrice();
            }
            payPhotoGraphyOrderDiscountMoney.setText("无");
            paygraphySummoney.setText(PayMoney + "");
            payFlag = 0;
        }
        //服装购买下单
        SumMoney = getIntent().getStringExtra("SumMoney");
        if (!TextUtils.isEmpty(SumMoney)) {
            payPhotoGraphyOrderBeforehandMoney.setText(SumMoney);
            PayMoney = Integer.parseInt(SumMoney);
            payPhotoGraphyOrderDiscountMoney.setText("无");
            paygraphySummoney.setText(PayMoney + "");

            clothId = getIntent().getIntExtra("clothId", 0);//.putExtra("clothId",mFdDetial.getId());
            orderCount = getIntent().getStringExtra("orderCount");//.putExtra("orderCount",orderCount);
            AddressId = getIntent().getIntExtra("addressId", 0);//.putExtra("addressId",AddressId);
            payFlag = 1;
        }
        photoFlag = getIntent().getIntExtra("photoFlag", photoFlag);

        mPayApi = PayOrderApi.getApi();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        userId = (int) sharedPreferencesHelper.getSharedPreference("id", 0);

        //支付初始化
        ac = new AlipayClass(this);
        wcpc = new WeChatPayClass(this);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_pay_photo_graphy_order;
    }

    @OnClick({R.id.title_back, R.id.pay_photo_graphy_order_paymethod_alipay, R.id.pay_photo_graphy_order_paymethod_wechat, R.id.pay_photo_graphy_order_paymethod_friend, R.id.payorderphotography_okbutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.pay_photo_graphy_order_paymethod_alipay:
                payMethodSelect(payPhotoGraphyOrderPaymethodAlipayText, payorderphotographyCheckImgAlipay, true);
                payMethodSelect(payPhotoGraphyOrderPaymethodWechatText, payorderphotographyCheckImgWechat, false);
                payMethodSelect(payorderphotographyCheckImgFriendText, payorderphotographyCheckImgFriend, false);
                isSelectPay = 1;
                break;
            case R.id.pay_photo_graphy_order_paymethod_wechat:
                payMethodSelect(payPhotoGraphyOrderPaymethodAlipayText, payorderphotographyCheckImgAlipay, false);
                payMethodSelect(payPhotoGraphyOrderPaymethodWechatText, payorderphotographyCheckImgWechat, true);
                payMethodSelect(payorderphotographyCheckImgFriendText, payorderphotographyCheckImgFriend, false);
                isSelectPay = 2;
                break;
            case R.id.pay_photo_graphy_order_paymethod_friend:
                payMethodSelect(payPhotoGraphyOrderPaymethodAlipayText, payorderphotographyCheckImgAlipay, false);
                payMethodSelect(payPhotoGraphyOrderPaymethodWechatText, payorderphotographyCheckImgWechat, false);
                payMethodSelect(payorderphotographyCheckImgFriendText, payorderphotographyCheckImgFriend, true);
                isSelectPay = 3;
                Intent intent = new Intent(PayPhotoGraphyOrderActivity.this, SelectFriendPayActivity.class);
                startActivityForResult(intent, 1001);
                break;
            case R.id.payorderphotography_okbutton:
                if (payFlag == 0) {
                    //套餐支付
                    createSingleOrder();
                } else if (payFlag == 1) {
                    //服装购买
                    if (isSelectPay == 1) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"支付宝支付");
                        payType = "Alipay";
                    } else if (isSelectPay == 2) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"微信支付");
                        payType = "Weixin";
                    } else if (isSelectPay == 3) {
                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this, "好友代付");
                    }
                    if (photoFlag == 2 || photoFlag == 3) {
                        createSingleRentOrder();
                    } else {
                        createSingleCloth(payType);
                    }
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1001:
                payType = data.getIntExtra("id", 0) + "";
                ToastUtil.showShort(this, "payTpye:" + payType);
                break;
        }
    }

    /************************服装购买  获取订单号************/
    private Callback<PayClothResult> callbackpayCloth = new Callback<PayClothResult>() {
        @Override
        public void onResponse(Call<PayClothResult> call, Response<PayClothResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                payRes = response.body();
                if (isSelectPay == 1) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"支付宝支付");
                    ac.payV2(payRes.getData().getPayInfo());
                } else if (isSelectPay == 2) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"微信支付");
                    wcpc.WXPay(payRes.getData().getPayInfo());
                } else if (isSelectPay == 3) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"找人支付");
                    Intent intent = new Intent(PayPhotoGraphyOrderActivity.this, WebViewActivity.class);
                    intent.putExtra("url", HttpUrlClient.PAYORDERSUCCESS);
                    intent.putExtra("title", "支付成功");
                    startActivity(intent);
                    finish();
                }

            } else {
//                if (isSelectPay == 3) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"找人支付");
                Intent intent = new Intent(PayPhotoGraphyOrderActivity.this, PayOrderFailedActivity.class);
                startActivity(intent);
                finish();
//                } else {
//                    ToastUtil.showShort(PayPhotoGraphyOrderActivity.this, payRes.getMsg());
//                }
            }
        }

        @Override
        public void onFailure(Call<PayClothResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleCloth(String payType) {
        Call<PayClothResult> call = mPayApi.getService().createCommitPayCloth(token, userId, orderCount, clothId, payType, AddressId);
        call.enqueue(callbackpayCloth);
    }

    /************************服装租借 共享  下单 获取订单号************/
    private Callback<RentOrderResult> callbackrentorder = new Callback<RentOrderResult>() {
        @Override
        public void onResponse(Call<RentOrderResult> call, Response<RentOrderResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                rentRes = response.body();
                if ("00".equals(rentRes.getCode())) {
                    if (isSelectPay == 1) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"支付宝支付");
                        payType = "Alipay";
                    } else if (isSelectPay == 2) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"微信支付");
                        payType = "Weixin";
                    } else if (isSelectPay == 3) {
                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this, "好友代付");
                    }
                    createSinglePayOrderEarnest(rentRes.getData().getId(), payType);
                } else {
                    ToastUtil.showShort(PayPhotoGraphyOrderActivity.this, rentRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<RentOrderResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleRentOrder() {
        Call<RentOrderResult> call = mPayApi.getService().createCommitRentCloth(token, userId, orderCount, clothId, payType, AddressId);
        call.enqueue(callbackrentorder);
    }

    /************************下单 获取订单号************/
    private Callback<PayOrderResult> callbackpayorder = new Callback<PayOrderResult>() {
        @Override
        public void onResponse(Call<PayOrderResult> call, Response<PayOrderResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mPayOrderRes = response.body();
                if ("00".equals(mPayOrderRes.getCode())) {
                    if (isSelectPay == 1) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"支付宝支付");
                        payType = "Alipay";
                    } else if (isSelectPay == 2) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"微信支付");
                        payType = "Weixin";
                    } else if (isSelectPay == 3) {
                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this, "好友代付");
                    }
                    createSinglePayOrderEarnest(mPayOrderRes.getData().getId(), payType);
                } else {
                    ToastUtil.showShort(PayPhotoGraphyOrderActivity.this, mPayOrderRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<PayOrderResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleOrder() {
        Call<PayOrderResult> call = mPayApi.getService().createCommitOrder(token, userId + "", mDetialRes.getData().getId() + "");
        call.enqueue(callbackpayorder);
    }



    /*************支付定金***************/
    private Callback<ModelBeanData> callbackpayorderEarnest = new Callback<ModelBeanData>() {
        @Override
        public void onResponse(Call<ModelBeanData> call, Response<ModelBeanData> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                ModelBeanData modelBeanData = response.body();
                if ("00".equals(modelBeanData.getCode())) {
                    if (isSelectPay == 1) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"支付宝支付");
                        ac.payV2(modelBeanData.getData());
                    } else if (isSelectPay == 2) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"微信支付");
                        wcpc.WXPay(modelBeanData.getData());
                    } else if (isSelectPay == 3) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"找人支付");
                        Intent intent = new Intent(PayPhotoGraphyOrderActivity.this, WebViewActivity.class);
                        intent.putExtra("url", HttpUrlClient.PAYORDERSUCCESS);
                        intent.putExtra("title", "支付成功");
                        startActivity(intent);
                        finish();
                    }

                } else {
                    if (isSelectPay == 3) {
//                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this,"找人支付");
                        Intent intent = new Intent(PayPhotoGraphyOrderActivity.this, PayOrderFailedActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ToastUtil.showShort(PayPhotoGraphyOrderActivity.this, modelBeanData.getMsg());
                    }
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ModelBeanData> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSinglePayOrderEarnest(int orderid, String payType) {
        Call<ModelBeanData> call = mPayApi.getService().createCommitPayOrderEarnest(token, orderid, payType);
        call.enqueue(callbackpayorderEarnest);
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
