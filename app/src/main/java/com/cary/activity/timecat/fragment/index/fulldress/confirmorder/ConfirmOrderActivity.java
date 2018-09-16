package com.cary.activity.timecat.fragment.index.fulldress.confirmorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.activity.SceneListActivity;
import com.cary.activity.timecat.fragment.index.fulldress.FullDressTabActivity;
import com.cary.activity.timecat.fragment.index.fulldress.fragment.FullDressColtheResult;
import com.cary.activity.timecat.fragment.index.photography.PayPhotoGraphyOrderActivity;
import com.cary.activity.timecat.fragment.index.setmealdetial.SetMealDetialResult;
import com.cary.activity.timecat.http.base.ApiClient;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.model.AttractionBean;
import com.cary.activity.timecat.model.BasicMealInfo;
import com.cary.activity.timecat.model.Order;
import com.cary.activity.timecat.reglogin.LoginCommitResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 确认订单
 */
public class ConfirmOrderActivity extends BaseActivity {
    private static final String TAG = ConfirmOrderActivity.class.getSimpleName();
    private static final int REQUEST_CODE_SECENE = 1000;
    private static final int REQUEST_CLOTH_CODE = 1001;
    private static final int REQUEST_BASIC_INFO = 1002;
    private static final int REQUEST_TEACHER = 1002;



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
    @BindView(R.id.iv_confirm_order_secnic_add)
    ImageView ivConfirmOrderSecnicAdd;
    @BindView(R.id.tv_confirm_order_secnic_two)
    TextView tvConfirmOrderSecnicTwo;
    @BindView(R.id.tv_confirm_order_secnic_name_two)
    TextView tvConfirmOrderSecnicNameTwo;
    @BindView(R.id.tv_confirm_order_setmealmoney_text)
    TextView tvConfirmOrderSetmealmoneyText;
    @BindView(R.id.tv_confirm_order_setmealmoney_flag)
    TextView tvConfirmOrderSetmealmoneyFlag;
    @BindView(R.id.tv_confirm_order_setmealmoney)
    TextView tvConfirmOrderSetmealmoney;
    @BindView(R.id.tv_confirm_order_commit_money)
    TextView tvConfirmOrderCommitMoney;
    @BindView(R.id.ll_confirm_order_dresser)
    LinearLayout llConfirmOrderDresser;
    @BindView(R.id.ll_confirm_order_camcerman)
    LinearLayout llConfirmOrderCamcerman;

    private SelectSniecAdapter adapter;

    private SetMealDetialResult mMealDetailBean;
    private ImageView mCurrentImageView;

    @BindView(R.id.iv_confirm_order_user_head)
    CircleImageView mImageIcon;
    @BindView(R.id.tv_confirm_order_user_name)
    TextView mTextUserName;

    private double mTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mMealDetailBean = (SetMealDetialResult) getIntent().getSerializableExtra("detialresult");

        if (getCurrentUser() != null) {
            Glide.with(this).load(HttpUrlClient.ALIYUNPHOTOBASEURL + getCurrentUser().
                    getImgurl()).into(mImageIcon);
            mTextUserName.setText(getCurrentUser().getNickname());
        }

        titleText.setText("确认订单");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setDatas();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_confirm_order;
    }

    private void setDatas() {
        if (mMealDetailBean == null) {
            return;
        }
        mTotalPrice = mMealDetailBean.getData().getPrice();
        tvConfirmOrderCommodityDesc.setText(mMealDetailBean.getData().getTitle());
        tvConfirmOrderCommodityStore.setText(mMealDetailBean.getData().getStoreName());
        tvConfirmOrderCommodityPrice.setText(mMealDetailBean.getData().getPrice() + "");
        tvConfirmOrderSetmealmoney.setText(mTotalPrice + "");
    }

    @OnClick({R.id.title_back, R.id.iv_confirm_order_commodity, R.id.ll_confirm_order_camcerman,
            R.id.ll_confirm_order_dresser, R.id.rl_confirm_order_user,
            R.id.ll_confirm_order_clothing_add_boy, R.id.ll_confirm_order_clothing_add_girl,
            R.id.rl_confirm_order_secnic_add, R.id.iv_confirm_order_secnic_add, R.id.iv_confirm_order_clothing,
            R.id.iv_confirm_order_clothing_add_boy, R.id.iv_confirm_order_clothing_add_boy_two, R.id.iv_confirm_order_clothing_two,
            R.id.tv_confirm_order_commit_money})
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
                startActivityForResult(intent,REQUEST_TEACHER);
                break;
            case R.id.ll_confirm_order_dresser:
                intent.setClass(this, SelectTeacherActivity.class);
                intent.putExtra("teacherflag", "dresser");
                startActivity(intent);
                break;
            case R.id.rl_confirm_order_user:
                intent.setClass(this, BaseInfoMessageActivity.class);
                startActivityForResult(intent, REQUEST_BASIC_INFO);
                break;
            case R.id.iv_confirm_order_secnic_add:
                intent.setClass(this, SceneListActivity.class);
                intent.putExtra("id", mMealDetailBean.getData().getId() + "");
                startActivityForResult(intent, REQUEST_CODE_SECENE);
                break;
            case R.id.iv_confirm_order_clothing:
            case R.id.iv_confirm_order_clothing_two:
            case R.id.iv_confirm_order_clothing_add_boy:
            case R.id.iv_confirm_order_clothing_add_boy_two:
                int sex;
                if (view.getId() == R.id.iv_confirm_order_clothing || view.getId() ==
                        R.id.iv_confirm_order_clothing_two) {
                    sex = 1;
                } else {
                    sex = 0;
                }
                mCurrentImageView = (ImageView) view;
                intent.setClass(this, FullDressTabActivity.class);
                intent.putExtra("flagtag", "1");
                intent.putExtra("isOrder", true);
                intent.putExtra("sex", sex);
                startActivityForResult(intent, REQUEST_CLOTH_CODE);
                break;
            case R.id.tv_confirm_order_commit_money:
//                loadOrder();
                Intent intent2 = new Intent(this, PayPhotoGraphyOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("detialresult", mMealDetailBean);
                intent2.putExtras(bundle);
                startActivity(intent2);
                break;

        }
    }

    public void loadOrder() {
        LoginCommitResult.Data user = getCurrentUser();
        if (user != null) {
            Call<Order> call = ApiClient.getApi().getService().loadOrder(user.getToken(), mMealDetailBean.getData().getId() + "",
                    user.getId() + "");
            call.enqueue(new Callback<Order>() {
                @Override
                public void onResponse(Call<Order> call, Response<Order> response) {
                    if (response.isSuccessful()) {
                        Log.i(TAG, "success!!!");
                        Log.i(TAG, "---" + response.body().toString());
                        Order orer = response.body();
                        if ("00".equals(orer.getCode())) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<Order> call, Throwable t) {

                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE_SECENE) {
            AttractionBean.DataBean bean = (AttractionBean.DataBean) data.getSerializableExtra("data");
            Glide.with(this).load(HttpUrlClient.ALIYUNPHOTOBASEURL + bean.getImgurl()).into(ivConfirmOrderSecnicAdd);
            tvConfirmOrderSecnicTwo.setText("¥" + bean.getAmount());
            tvConfirmOrderSecnicNameTwo.setText(bean.getTitle());
            mTotalPrice = mTotalPrice + bean.getAmount();
            tvConfirmOrderSetmealmoney.setText(mTotalPrice + "");


        } else if (requestCode == REQUEST_CLOTH_CODE) {
            FullDressColtheResult.Data bean = (FullDressColtheResult.Data) data.getSerializableExtra("dress");
            Glide.with(this).load(HttpUrlClient.ALIYUNPHOTOBASEURL + bean.getImgurl()).into(mCurrentImageView);
            mTotalPrice = mTotalPrice + bean.getAmount();
            tvConfirmOrderSetmealmoney.setText(mTotalPrice + "");
        } else if (requestCode == REQUEST_BASIC_INFO) {
            BasicMealInfo info = (BasicMealInfo) data.getSerializableExtra("data");
            tvConfirmOrderUserBridegroom.setText(info.getBridegroom());
            tvConfirmOrderUserWeddingtime.setText(info.getShootTime());
            tvConfirmOrderUserReception.setText(info.getStoreReceptionStr());
            tvConfirmOrderUserWeddingday.setText(info.getMarryTime());
            tvConfirmOrderUserBride.setText(info.getBrideg());
        }else if(requestCode ==REQUEST_TEACHER){
            TeacherListResult bean = (TeacherListResult) data.getSerializableExtra("teacher");
        }
    }
}
