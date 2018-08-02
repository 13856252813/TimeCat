package com.cary.activity.timecat.fragment.index.timeshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.detial.DefaultRecAddResult;
import com.cary.activity.timecat.fragment.index.fulldress.detial.FullDressDetialActivity;
import com.cary.activity.timecat.fragment.index.fulldress.expressmethod.ExpressResult;
import com.cary.activity.timecat.fragment.index.photography.PayPhotoGraphyOrderActivity;
import com.cary.activity.timecat.fragment.index.selectstore.detial.StoreDetialActivity;
import com.cary.activity.timecat.fragment.look.integral.exchange.IntegralDetialResult;
import com.cary.activity.timecat.fragment.person.receiveadd.NewReceivedResult;
import com.cary.activity.timecat.fragment.person.receiveadd.ReceivedAddressActivity;
import com.cary.activity.timecat.fragment.person.receiveadd.ReceivedAddressListApi;
import com.cary.activity.timecat.fragment.person.receiveadd.ReceivedAddressListResult;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.modelbean.ModelBeanData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/****
 * 确认订单 页面
 */
public class TimeShopOrderActivity extends BaseActivity {

    private static final String TAG = TimeShopOrderActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_confirm_order_now_setting_address)
    TextView tvConfirmOrderNowSettingAddress;
    @BindView(R.id.ll_confirm_order_no_adress)
    LinearLayout llConfirmOrderNoAdress;
    @BindView(R.id.iv_confirm_order_show_adress)
    ImageView ivConfirmOrderShowAdress;
    @BindView(R.id.tv_confirm_order_show_name)
    TextView tvConfirmOrderShowName;
    @BindView(R.id.tv_confirm_order_show_phone)
    TextView tvConfirmOrderShowPhone;
    @BindView(R.id.tv_confirm_order_show_address)
    TextView tvConfirmOrderShowAddress;
    @BindView(R.id.rl_confirm_order_show_adress)
    RelativeLayout rlConfirmOrderShowAdress;
    @BindView(R.id.ll_confirm_order_adress)
    LinearLayout llConfirmOrderAdress;
    @BindView(R.id.iv_detial_order_cloth_img)
    ImageView ivDetialOrderClothImg;
    @BindView(R.id.tv_detial_order_cloth_affect)
    TextView tvDetialOrderClothAffect;
    @BindView(R.id.tv_detial_order_cloth_price)
    TextView tvDetialOrderClothPrice;
    @BindView(R.id.tv_detial_order_cloth_name)
    TextView tvDetialOrderClothName;
    @BindView(R.id.tv_detial_order_cloth_store_name)
    TextView tvDetialOrderClothStoreName;
    @BindView(R.id.tv_detial_order_cloth_specification)
    TextView tvDetialOrderClothSpecification;
    @BindView(R.id.btn_detial_order_number_sub)
    TextView btnDetialOrderNumberSub;
    @BindView(R.id.tv_detial_order_cloth_number)
    TextView tvDetialOrderClothNumber;
    @BindView(R.id.btn_detial_order_number_add)
    TextView btnDetialOrderNumberAdd;
    @BindView(R.id.tv_detial_order_cloth_sendmethod)
    TextView tvDetialOrderClothSendmethod;
    @BindView(R.id.tv_detial_order_cloth_sendprice)
    TextView tvDetialOrderClothSendprice;
    @BindView(R.id.tv_full_dress_detial_order_summoney)
    TextView tvFullDressDetialOrderSummoney;
    @BindView(R.id.tv_full_dress_detial_order_summoney_two)
    TextView tvFullDressDetialOrderSummoneyTwo;
    @BindView(R.id.btn_full_dress_detial_order_confirm)
    Button btnFullDressDetialOrderConfirm;
    @BindView(R.id.rl_full_dress_order_detiall)
    RelativeLayout rlFullDressOrderDetiall;
    @BindView(R.id.rl_express_method)
    RelativeLayout rlExpressMethod;
    @BindView(R.id.rl_postage_money)
    RelativeLayout rlPostageMoney;

    private int photoFlag = 0;//是什么 拍照 购买 共享
    private String itemNorm = "";//规格
    private TimeShopDetialResult.DataBean mFdDetial;//服装详情的model

    //获取收货地址
    private ReceivedAddressListResult readressRes;
    private ReceivedAddressListApi ralApi;
    private SharedPreferencesHelper sharePH;
    private String token;
    private int uid;

    private DefaultRecAddResult defaultAddRes;//默认收货地址
    private int AddressId;//地址ID
    private ExpressResult.Data express;//快递方式
    private int expressId;//快递ID

    private int ClothMoney;//服装价格
    private int ExpressMoney;//运费
    private int SumMoney;//总费用 实际支付费用
    private String Title = "", StoreName = "", imgUrl = "", Sendtype = "", Postage = "";
    private IntegralDetialResult.Data mIntegral;//兑换详情 model


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_full_dress_detial_order);
        ButterKnife.bind(this);
        titleText.setText("确认订单");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        mFdDetial = (TimeShopDetialResult.DataBean) getIntent().getSerializableExtra("fulldressdetial");
        itemNorm = getIntent().getStringExtra("itemNorm");
        photoFlag = getIntent().getIntExtra("photoFlag", 0);
        mIntegral = (IntegralDetialResult.Data) getIntent().getSerializableExtra("exchangedetial");

        ralApi = ReceivedAddressListApi.getApi();
        sharePH = new SharedPreferencesHelper(this);
        token = (String) sharePH.getSharedPreference("token", "");
        uid = (int) sharePH.getSharedPreference("id", 0);

        //购买
        tvDetialOrderClothAffect.setText("服装购买");
        ClothMoney = mFdDetial.getAmount();
        tvDetialOrderClothPrice.setText("¥" + ClothMoney);


        String imgUrl = "";
        //根据接受的值 来展示是积分 还是购买
        if (mFdDetial != null) {
            Title = mFdDetial.getTitle();
            StoreName = mFdDetial.getStoreId()/*getStore().getStoreName()*/ + " >";
            imgUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + mFdDetial.getImgurl();
            Sendtype = mFdDetial.getSendtype();
            Postage = mFdDetial.getPostage() + "";
            ExpressMoney = mFdDetial.getPostage();
            //实际支付价格
            SumMoney = ClothMoney + ExpressMoney;
            imgUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + mFdDetial.getImgurl();
        } else if (mIntegral != null) {
            Title = mIntegral.getTitle();
            imgUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + mIntegral.getImgurl();
            rlExpressMethod.setVisibility(View.GONE);
            rlPostageMoney.setVisibility(View.GONE);
            SumMoney = mIntegral.getScore();
        }
        //设置衣服信息 门店信息
        tvDetialOrderClothName.setText(Title);
        tvDetialOrderClothStoreName.setText(StoreName);
        Glide.with(this).load(imgUrl).into(ivDetialOrderClothImg);

        //快递方式
        tvDetialOrderClothSendmethod.setText(Sendtype);
        //运费
        tvDetialOrderClothSendprice.setText("¥" + ExpressMoney);
        //规格
        if (!TextUtils.isEmpty(itemNorm)) {
            tvDetialOrderClothSpecification.setText(itemNorm);
        }

        tvFullDressDetialOrderSummoney.setText(SumMoney + "");

        //获取默认地址数据数据
        createSingleAddress();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_full_dress_detial_order;
    }

    @OnClick({R.id.title_back, R.id.tv_confirm_order_now_setting_address, R.id.rl_full_dress_order_detiall,
            R.id.tv_detial_order_cloth_store_name, R.id.ll_confirm_order_adress, R.id.rl_express_method,
            R.id.btn_detial_order_number_sub, R.id.btn_detial_order_number_add, R.id.btn_full_dress_detial_order_confirm})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tv_confirm_order_now_setting_address:
//                ToastUtil.showShort(this, "设置地址");
                intent.setClass(this, ReceivedAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_confirm_order_adress:
                intent.setClass(this, ReceivedAddressActivity.class);
                startActivityForResult(intent, 1001);

                break;
            case R.id.rl_full_dress_order_detiall:
                //跳转到详情界面
                intent.setClass(this, FullDressDetialActivity.class);
                intent.putExtra("id", mFdDetial.getId());
                startActivity(intent);
                break;
            case R.id.tv_detial_order_cloth_store_name:
                intent.setClass(this, StoreDetialActivity.class);
                intent.putExtra("storeId", mFdDetial.getStoreId());
                startActivity(intent);
                break;
            case R.id.btn_detial_order_number_sub:
                String buyNumberStr = tvDetialOrderClothNumber.getText().toString().trim();
                int buyNumber = Integer.parseInt(buyNumberStr);
                if (buyNumber > 1) {
                    buyNumber--;
                } else {
                    ToastUtil.showShort(this, "购买数量最少为1件");
                }
                tvDetialOrderClothNumber.setText(buyNumber + "");
                SumMoney -= ClothMoney;
                tvFullDressDetialOrderSummoney.setText(SumMoney + "");
                break;
            case R.id.btn_detial_order_number_add:
                String buyNumberStrAdd = tvDetialOrderClothNumber.getText().toString().trim();
                int buyNumberAdd = Integer.parseInt(buyNumberStrAdd);
                buyNumberAdd++;
                tvDetialOrderClothNumber.setText(buyNumberAdd + "");
                SumMoney += ClothMoney;
                tvFullDressDetialOrderSummoney.setText(SumMoney + "");
                //实际支付也要加双倍
                break;
            case R.id.rl_express_method:
//                    intent.setClass(this,ExpressMethodActivity.class);
//                    startActivityForResult(intent,1002);
                break;
            case R.id.btn_full_dress_detial_order_confirm:
//                ToastUtil.showShort(this, "确认支付");
                String SumMoney = tvFullDressDetialOrderSummoney.getText().toString().trim();
                String orderCount = tvDetialOrderClothNumber.getText().toString().trim();
                if (photoFlag == 1 || photoFlag == 2 || photoFlag == 3) {
                    intent.setClass(this, PayPhotoGraphyOrderActivity.class);
                    intent.putExtra("clothId", mFdDetial.getId());
                    intent.putExtra("orderCount", orderCount);
                    intent.putExtra("SumMoney", SumMoney);
                    intent.putExtra("addressId", AddressId);
                    startActivity(intent);
                } else if (photoFlag == 4) {
                    createSingleExChange(mIntegral.getId(), orderCount);
                }


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1001:
                //选择地址返回的
                if (data != null) {
                    AddressId = data.getIntExtra("id", 0);
                    createSingleGetAddress(AddressId);
                }
                break;
            case 1002:
                //选择快递方式 返回的
                if (data != null) {
                    express = (ExpressResult.Data) data.getSerializableExtra("express");
                    tvDetialOrderClothSendmethod.setText(express.getName());
                    expressId = express.getId();
                }

                break;
        }
    }

    /*****************获取地址*********************/
    private Callback<NewReceivedResult> callbackAdd = new Callback<NewReceivedResult>() {
        @Override
        public void onResponse(Call<NewReceivedResult> call, Response<NewReceivedResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                NewReceivedResult defaultAddRes = response.body();
                if ("00".equals(defaultAddRes.getCode())) {
                    if (defaultAddRes.getData() != null) {
                        //有默认地址
                        rlConfirmOrderShowAdress.setVisibility(View.VISIBLE);
                        llConfirmOrderNoAdress.setVisibility(View.GONE);
                        tvConfirmOrderShowName.setText(defaultAddRes.getData().getName());
                        String mAddress = defaultAddRes.getData().getProvince() + defaultAddRes.getData().getCity() +
                                defaultAddRes.getData().getArea() + defaultAddRes.getData().getDetail();
                        tvConfirmOrderShowAddress.setText(mAddress);
                        tvConfirmOrderShowPhone.setText(defaultAddRes.getData().getMobile());
                        AddressId = defaultAddRes.getData().getId();
                    } else {
                        //没有默认地址
                        rlConfirmOrderShowAdress.setVisibility(View.GONE);
                        llConfirmOrderNoAdress.setVisibility(View.VISIBLE);
                    }
                } else {
                    ToastUtil.showShort(TimeShopOrderActivity.this, defaultAddRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NewReceivedResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleGetAddress(int addressId) {
        Call<NewReceivedResult> call = ralApi.getService().createCommitGetId(token, addressId);
        call.enqueue(callbackAdd);
    }

    /*****************判断是否有默认地址***********/
    private Callback<DefaultRecAddResult> callbackDefault = new Callback<DefaultRecAddResult>() {
        @Override
        public void onResponse(Call<DefaultRecAddResult> call, Response<DefaultRecAddResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                defaultAddRes = response.body();
                if ("00".equals(defaultAddRes.getCode())) {
                    if (defaultAddRes.getData() != null) {
                        //有默认地址
                        rlConfirmOrderShowAdress.setVisibility(View.VISIBLE);
                        llConfirmOrderNoAdress.setVisibility(View.GONE);
                        tvConfirmOrderShowName.setText(defaultAddRes.getData().getName());
                        String mAddress = defaultAddRes.getData().getProvince() + defaultAddRes.getData().getCity() +
                                defaultAddRes.getData().getArea() + defaultAddRes.getData().getDetail();
                        tvConfirmOrderShowAddress.setText(mAddress);
                        tvConfirmOrderShowPhone.setText(defaultAddRes.getData().getMobile());
                        AddressId = defaultAddRes.getData().getId();
                    } else {
                        //没有默认地址
                        rlConfirmOrderShowAdress.setVisibility(View.GONE);
                        llConfirmOrderNoAdress.setVisibility(View.VISIBLE);
                    }
                } else {
                    ToastUtil.showShort(TimeShopOrderActivity.this, defaultAddRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<DefaultRecAddResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleAddress() {
        Call<DefaultRecAddResult> call = ralApi.getService().createCommitGetDefault(token, uid);
        call.enqueue(callbackDefault);
    }

    /*****************兑换***********/
    private Callback<ModelBeanData> callbackExchange = new Callback<ModelBeanData>() {
        @Override
        public void onResponse(Call<ModelBeanData> call, Response<ModelBeanData> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                ModelBeanData beanData = response.body();
                if ("00".equals(beanData.getCode())) {
                    if (beanData.getData() != null) {

                    } else {

                    }
                } else {
                    ToastUtil.showShort(TimeShopOrderActivity.this, beanData.getMsg());
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

    private void createSingleExChange(int pid, String productCount) {
        Call<ModelBeanData> call = ralApi.getService().createCommitPostExchange(token, uid, pid, productCount, AddressId, "1");
        call.enqueue(callbackExchange);
    }

}
