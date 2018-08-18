package com.cary.activity.timecat.fragment.index.fulldress.confirmorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.setmealdetial.SetMealDetialResult;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 确认订单
 */
public class ConfirmOrderActivity extends BaseActivity {
    private static final String TAG = ConfirmOrderActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_confirm_order_commodity)
    ImageView ivConfirmOrderCommodity;
    @BindView(R.id.tv_confirm_order_commodity_title)
    TextView tvConfirmOrderCommodityTitle;
    @BindView(R.id.tv_confirm_order_commodity_price)
    TextView tvConfirmOrderCommodityPrice;
    @BindView(R.id.tv_confirm_order_commodity_desc)
    TextView tvConfirmOrderCommodityDesc;
    @BindView(R.id.tv_confirm_order_commodity_store)
    TextView tvConfirmOrderCommodityStore;
    @BindView(R.id.iv_confirm_order_user_arrow)
    ImageView ivConfirmOrderUserArrow;
    @BindView(R.id.rl_confirm_order_user)
    RelativeLayout rlConfirmOrderUser;
    @BindView(R.id.iv_confirm_order_user_head)
    ImageView ivConfirmOrderUserHead;
    @BindView(R.id.tv_confirm_order_user_name)
    TextView tvConfirmOrderUserName;
    @BindView(R.id.tv_confirm_order_user_bridegroom_text)
    TextView tvConfirmOrderUserBridegroomText;
    @BindView(R.id.tv_confirm_order_user_bridegroom)
    TextView tvConfirmOrderUserBridegroom;
    @BindView(R.id.tv_confirm_order_user_brideg_text)
    TextView tvConfirmOrderUserBridegText;
    @BindView(R.id.tv_confirm_order_user_bride)
    TextView tvConfirmOrderUserBride;
    @BindView(R.id.tv_confirm_order_user_weddingday_text)
    TextView tvConfirmOrderUserWeddingdayText;
    @BindView(R.id.tv_confirm_order_user_weddingday)
    TextView tvConfirmOrderUserWeddingday;
    @BindView(R.id.tv_confirm_order_user_weddingtime_text)
    TextView tvConfirmOrderUserWeddingtimeText;
    @BindView(R.id.tv_confirm_order_user_weddingtime)
    TextView tvConfirmOrderUserWeddingtime;
    @BindView(R.id.tv_confirm_order_user_scneic_text)
    TextView tvConfirmOrderUserScneicText;
    @BindView(R.id.tv_confirm_order_user_scneic)
    TextView tvConfirmOrderUserScneic;
    @BindView(R.id.tv_confirm_order_user_reception_text)
    TextView tvConfirmOrderUserReceptionText;
    @BindView(R.id.tv_confirm_order_user_reception)
    TextView tvConfirmOrderUserReception;
    @BindView(R.id.iv_confirm_order_camcerman)
    ImageView ivConfirmOrderCamcerman;
    @BindView(R.id.tv_confirm_order_camcerman)
    TextView tvConfirmOrderCamcerman;
    @BindView(R.id.tv_confirm_order_camcerman_text)
    TextView tvConfirmOrderCamcermanText;
    @BindView(R.id.tv_confirm_order_camcerman_price)
    TextView tvConfirmOrderCamcermanPrice;
    @BindView(R.id.tv_confirm_order_camcerman_setmeal)
    TextView tvConfirmOrderCamcermanSetmeal;
    @BindView(R.id.iv_confirm_order_dresser)
    ImageView ivConfirmOrderDresser;
    @BindView(R.id.tv_confirm_order_dresser)
    TextView tvConfirmOrderDresser;
    @BindView(R.id.tv_confirm_order_dresser_text)
    TextView tvConfirmOrderDresserText;
    @BindView(R.id.tv_confirm_order_dresser_price)
    TextView tvConfirmOrderDresserPrice;
    @BindView(R.id.tv_confirm_order_dresser_setmeal)
    TextView tvConfirmOrderDresserSetmeal;
    @BindView(R.id.iv_confirm_order_numerical)
    ImageView ivConfirmOrderNumerical;
    @BindView(R.id.tv_confirm_order_numerical)
    TextView tvConfirmOrderNumerical;
    @BindView(R.id.tv_confirm_order_numerical_text)
    TextView tvConfirmOrderNumericalText;
    @BindView(R.id.tv_confirm_order_numical_price)
    TextView tvConfirmOrderNumicalPrice;
    @BindView(R.id.tv_confirm_order_clothing_text)
    TextView tvConfirmOrderClothingText;
    @BindView(R.id.iv_confirm_order_clothing)
    ImageView ivConfirmOrderClothing;
    @BindView(R.id.tv_confirm_order_clothing_name)
    TextView tvConfirmOrderClothingName;
    @BindView(R.id.iv_confirm_order_clothing_add_boy)
    ImageView ivConfirmOrderClothingAddBoy;
    @BindView(R.id.ll_confirm_order_clothing_add_boy)
    LinearLayout llConfirmOrderClothingAddBoy;
    @BindView(R.id.tv_confirm_order_clothing_name_add_boy)
    TextView tvConfirmOrderClothingNameAddBoy;
    @BindView(R.id.tv_confirm_order_clothing_text_two)
    TextView tvConfirmOrderClothingTextTwo;
    @BindView(R.id.iv_confirm_order_clothing_two)
    ImageView ivConfirmOrderClothingTwo;
    @BindView(R.id.tv_confirm_order_clothing_name_two)
    TextView tvConfirmOrderClothingNameTwo;
    @BindView(R.id.iv_confirm_order_clothing_add_boy_two)
    ImageView ivConfirmOrderClothingAddBoyTwo;
    @BindView(R.id.ll_confirm_order_clothing_add_girl)
    LinearLayout llConfirmOrderClothingAddGirl;
    @BindView(R.id.tv_confirm_order_clothing_name_add_girl)
    TextView tvConfirmOrderClothingNameAddGirl;
    @BindView(R.id.recyclerview_confirm_order_secnic)
    RecyclerView recyclerviewConfirmOrderSecnic;
    @BindView(R.id.iv_confirm_order_secnic_add)
    ImageView ivConfirmOrderSecnicAdd;
    @BindView(R.id.tv_confirm_order_secnic_two)
    TextView tvConfirmOrderSecnicTwo;
    @BindView(R.id.tv_confirm_order_secnic_name_two)
    TextView tvConfirmOrderSecnicNameTwo;
    @BindView(R.id.rl_confirm_order_secnic_add)
    RelativeLayout rlConfirmOrderSecnicAdd;
    @BindView(R.id.tv_confirm_order_setmealmoney_text)
    TextView tvConfirmOrderSetmealmoneyText;
    @BindView(R.id.tv_confirm_order_setmealmoney_flag)
    TextView tvConfirmOrderSetmealmoneyFlag;
    @BindView(R.id.tv_confirm_order_setmealmoney)
    TextView tvConfirmOrderSetmealmoney;
    @BindView(R.id.tv_confirm_order_setmealmoney_two)
    TextView tvConfirmOrderSetmealmoneyTwo;
    @BindView(R.id.tv_confirm_order_commit_money)
    TextView tvConfirmOrderCommitMoney;
    @BindView(R.id.ll_confirm_order_dresser)
    LinearLayout llConfirmOrderDresser;
    @BindView(R.id.ll_confirm_order_camcerman)
    LinearLayout llConfirmOrderCamcerman;
    private SelectSniecAdapter adapter;

    private SetMealDetialResult mMealDetailBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mMealDetailBean= (SetMealDetialResult) getIntent().getSerializableExtra("detialresult");


        titleText.setText("确认订单");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        /**
         * 创建一个linearlayoutmaneger对象，并将他设置到recyclerview当中。layoutmanager用于指定
         * recyclerview的布局方式，这里是线性布局的意思。可以实现和listview类似的效果。
         *
         * 接下来我们创建了Fruitadapter的实例，并将数据传进去
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerviewConfirmOrderSecnic.setLayoutManager(linearLayoutManager);
        adapter = new SelectSniecAdapter(this);
        //设置item间距，30dp
        recyclerviewConfirmOrderSecnic.addItemDecoration(new SpaceItemDecoration(20));
        recyclerviewConfirmOrderSecnic.setAdapter(adapter);
        setDatas();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_confirm_order;
    }

    private void setDatas(){
        if(mMealDetailBean==null){
            return;
        }
        tvConfirmOrderCommodityDesc.setText(mMealDetailBean.getData().getTitle());
        tvConfirmOrderCommodityStore.setText(mMealDetailBean.getData().getStoreName());
        tvConfirmOrderCommodityPrice.setText(mMealDetailBean.getData().getPrice()+"");
    }

    @OnClick({R.id.title_back, R.id.iv_confirm_order_commodity, R.id.ll_confirm_order_camcerman,
            R.id.ll_confirm_order_dresser, R.id.rl_confirm_order_user,
            R.id.ll_confirm_order_clothing_add_boy, R.id.ll_confirm_order_clothing_add_girl,
            R.id.rl_confirm_order_secnic_add})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.iv_confirm_order_commodity:

                break;
            case R.id.ll_confirm_order_camcerman:
                intent.setClass(this, SelectTeacherActivity.class);
                intent.putExtra("teacherflag", "camcer");
                startActivity(intent);
                break;
            case R.id.ll_confirm_order_dresser:
                intent.setClass(this, SelectTeacherActivity.class);
                intent.putExtra("teacherflag", "dresser");
                intent.setClass(this, SelectTeacherActivity.class);
                intent.putExtra("teacherflag", "camcer");
                startActivity(intent);
                break;
            case R.id.rl_confirm_order_user:
                intent.setClass(this, BaseInfoMessageActivity.class);

                startActivity(intent);
                break;
            case R.id.ll_confirm_order_clothing_add_boy:
                intent.setClass(this, SelectColothActivity.class);
                intent.putExtra("sex", "2");
                startActivity(intent);
                break;
            case R.id.ll_confirm_order_clothing_add_girl:
                intent.setClass(this, SelectColothActivity.class);
                intent.putExtra("sex", "1");
                startActivity(intent);
                break;
            case R.id.rl_confirm_order_secnic_add:
                intent.setClass(this,SelectScenicActivity.class);
                startActivity(intent);
                break;

        }
    }
}
