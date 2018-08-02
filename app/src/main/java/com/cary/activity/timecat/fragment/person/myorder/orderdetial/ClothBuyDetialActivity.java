package com.cary.activity.timecat.fragment.person.myorder.orderdetial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.detial.FullDressDetialActivity;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 服装购买订单
 */
public class ClothBuyDetialActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_cloth_buy_detial_state)
    TextView tvClothBuyDetialState;
    @BindView(R.id.tv_cloth_buy_detial_text)
    TextView tvClothBuyDetialText;
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
    @BindView(R.id.rl_order_detial)
    RelativeLayout rlOrderDetial;
    @BindView(R.id.tv_detial_order_cloth_specification)
    TextView tvDetialOrderClothSpecification;
    @BindView(R.id.tv_detial_order_cloth_specification_number)
    TextView tvDetialOrderClothSpecificationNumber;
    @BindView(R.id.tv_full_dress_detial_order_summoney)
    TextView tvFullDressDetialOrderSummoney;
    @BindView(R.id.tv_full_dress_detial_order_summoney_two)
    TextView tvFullDressDetialOrderSummoneyTwo;
    @BindView(R.id.btn_full_dress_detial_order_confirm)
    Button btnFullDressDetialOrderConfirm;

    private String idStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_buy_detial);
        ButterKnife.bind(this);
        titleText.setText("订单详情");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleTextRight.setVisibility(View.VISIBLE);
        titleTextRight.setText("");
        idStr = getIntent().getStringExtra("id");

    }

    @OnClick({R.id.title_back, R.id.rl_order_detial, R.id.btn_full_dress_detial_order_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.rl_order_detial:
                Intent intent = new Intent(this, FullDressDetialActivity.class);
                intent.putExtra("id", "123");
                startActivity(intent);
                break;
            case R.id.btn_full_dress_detial_order_confirm:
                ToastUtil.showShort(this, "确认收货 or 归还");
                Intent intentSuc = new Intent(this, ConfirmSuccessActivity.class);
                startActivity(intentSuc);
                break;
        }
    }
}
