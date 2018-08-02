package com.cary.activity.timecat.fragment.index.hotscenic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.selection.FullDressColothCollect;
import com.cary.activity.timecat.fragment.index.selectstore.detial.StoreDetialActivity;
import com.cary.activity.timecat.fragment.index.timeshop.TimeShopActivity;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.view.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: create by Cary
 * Date: on 2018/7/15 19:34
 * Comment:热门景点详情
 */
@SuppressLint("Registered")
public class HotScenicDetialActivity extends BaseActivity {
    private static final String TAG = TimeShopActivity.class.getSimpleName();
    @BindView(R.id.iv_fulldress_back)
    ImageView ivFulldressBack;
    @BindView(R.id.banner_fulldress_detial)
    BannerLayout bannerFulldressDetial;
    @BindView(R.id.tv_full_dress_title)
    TextView tvFullDressTitle;
    @BindView(R.id.tv_full_dress_sell_number)
    TextView tvFullDressSellNumber;
    @BindView(R.id.ratingbar_full_dess)
    RatingBar ratingbarFullDess;
    @BindView(R.id.tv_full_dress_money)
    TextView tvFullDressMoney;
    @BindView(R.id.tv_full_dress_old_money)
    TextView tvFullDressOldMoney;
    @BindView(R.id.tv_full_dress_collect)
    CheckBox tvFullDressCollect;
    @BindView(R.id.ll_full_dress_title)
    LinearLayout llFullDressTitle;
    @BindView(R.id.iv_full_dress_store_name)
    ImageView ivFullDressStoreName;
    @BindView(R.id.tv_full_dress_store_name)
    TextView tvFullDressStoreName;
    @BindView(R.id.rl_full_dress_store_name)
    RelativeLayout rlFullDressStoreName;

    @BindView(R.id.tv_fulldress_detial_service)
    TextView tvFulldressDetialService;
    @BindView(R.id.tv_fulldress_detial_telphone)
    TextView tvFulldressDetialTelphone;
    @BindView(R.id.tv_fulldress_detial_onlinereserve)
    TextView tvFulldressDetialOnlinereserve;
    @BindView(R.id.webview_full_dress_detial_desc)
    WebView webviewFullDressDetialDesc;

    //详情
    private HotScenicApi mApi;
    private List<String> bannerUrls = new ArrayList<>();
    private HotScenicDetialResult detialComRes;

    private int idStr;//接收 详情的id
    private String token;
    private int userId;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private int storeId;//门店id
    private FullDressColothCollect mMealColRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        //默认API 最低19
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup contentView = window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
            contentView.getChildAt(0).setFitsSystemWindows(false);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  //该参数指布局能延伸到navigationbar，我们场景中不应加这个参数
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT); //设置navigationbar颜色为透明
        }

        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        userId = (int) sharedPreferencesHelper.getSharedPreference("id", 0);
        idStr = getIntent().getIntExtra("id", 0);
        //获取详情信息
        mApi = HotScenicApi.getApi();
        createSingleDetial();

        bannerFulldressDetial.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_hotscenic_detial;
    }

    @OnClick({R.id.iv_fulldress_back, R.id.tv_full_dress_collect, R.id.rl_full_dress_store_name,
            R.id.tv_fulldress_detial_service, R.id.tv_fulldress_detial_telphone,
            R.id.tv_fulldress_detial_onlinereserve})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fulldress_back:
                finish();
                break;
            case R.id.tv_full_dress_collect:
                if (tvFullDressCollect.isChecked()) {
                    //选中
                    createSingleFullDressCollect();
                } else {
                    //取消 未选中
                    createSingleFullDressUnCollect();
                }

                break;
            case R.id.rl_full_dress_store_name:
                Intent intent1 = new Intent(this, StoreDetialActivity.class);
                intent1.putExtra("id", storeId);//传递的是门店id
                startActivity(intent1);
                break;
            case R.id.tv_fulldress_detial_service:
                break;
            case R.id.tv_fulldress_detial_telphone:
                break;
            case R.id.tv_fulldress_detial_onlinereserve:

//                Intent intent = new Intent(this, PayPhotoGraphyOrderActivity.class);
//                startActivity(intent);
                break;

        }
    }


    /**************** 根据id 获取数据****************/
    private Callback<HotScenicDetialResult> callbackDetial = new Callback<HotScenicDetialResult>() {
        @Override
        public void onResponse(Call<HotScenicDetialResult> call, Response<HotScenicDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                detialComRes = response.body();
                if ("00".equals(detialComRes.getCode())) {
                    tvFullDressTitle.setText(detialComRes.getData().getTitle());
                    tvFullDressSellNumber.setText("已售:" + detialComRes.getData().getSellCount() + "笔");
                    ratingbarFullDess.setRating(detialComRes.getData().getStarCount());
                    tvFullDressMoney.setText("¥" + detialComRes.getData().getAmount());
                    //售卖
                    tvFullDressOldMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    tvFullDressOldMoney.setText("¥" + detialComRes.getData().getMarkerPrice());

                    tvFullDressStoreName.setText(detialComRes.getData().getStoreId() + "");//.getStoreName());//门店的title
                    String storeImg = HttpUrlClient.ALIYUNPHOTOBASEURL + detialComRes.getData().getImgurl();
                    Glide.with(HotScenicDetialActivity.this).load(storeImg).into(ivFullDressStoreName);
                    storeId = detialComRes.getData().getStoreId();
                    String imageBanner = detialComRes.getData().getImgurls();
                    if (!TextUtils.isEmpty(imageBanner)) {
                        String imgUrls[] = imageBanner.split(",");
                        for (int i = 0; i < imgUrls.length; i++) {
                            String imageStr = HttpUrlClient.ALIYUNPHOTOBASEURL + imgUrls[i];
                            bannerUrls.add(imageStr);
                        }
                        if (bannerFulldressDetial != null) {
                            bannerFulldressDetial.setViewUrls(bannerUrls, null);
                        }
                    }
                    //详细内容
                    webviewFullDressDetialDesc.loadDataWithBaseURL(null, detialComRes.getData().getContent(), "text/html", "utf-8", null);

                } else {
                    ToastUtil.showShort(HotScenicDetialActivity.this, detialComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<HotScenicDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleDetial() {
        Call<HotScenicDetialResult> call = mApi.getService().createCommitId(token, idStr, userId);
        call.enqueue(callbackDetial);
    }

    /**************关注服装**************/
    private Callback<FullDressColothCollect> callbackStoreCollect = new Callback<FullDressColothCollect>() {
        @Override
        public void onResponse(Call<FullDressColothCollect> call, Response<FullDressColothCollect> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mMealColRes = response.body();
                if ("00".equals(mMealColRes.getCode())) {
                    setColloect(true);
                } else {
                    ToastUtil.showShort(HotScenicDetialActivity.this, mMealColRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<FullDressColothCollect> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleFullDressCollect() {
        Call<FullDressColothCollect> call = mApi.getService().createCommitCollectId(token, idStr, userId + "");
        call.enqueue(callbackStoreCollect);
    }

    /**********************取消关注服装***********************/
    private Callback<FullDressColothCollect> callbackStoreUnCollect = new Callback<FullDressColothCollect>() {
        @Override
        public void onResponse(Call<FullDressColothCollect> call, Response<FullDressColothCollect> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mMealColRes = response.body();
                if ("00".equals(mMealColRes.getCode())) {
                    setColloect(false);
                } else {
                    ToastUtil.showShort(HotScenicDetialActivity.this, mMealColRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<FullDressColothCollect> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleFullDressUnCollect() {
        Call<FullDressColothCollect> call = mApi.getService().createCommitUnCollectId(token, idStr);
        call.enqueue(callbackStoreUnCollect);
    }

    private void setColloect(boolean flag) {
        Drawable colloect, colloect2;
        Resources res = getResources();
        colloect = res.getDrawable(R.mipmap.heart);
        colloect2 = res.getDrawable(R.mipmap.heart2);
        colloect.setBounds(0, 0, colloect.getMinimumWidth(), colloect.getMinimumHeight());
        colloect2.setBounds(0, 0, colloect2.getMinimumWidth(), colloect2.getMinimumHeight());

        if (flag) {
            ToastUtil.showShort(this, "已经关注");
            tvFullDressCollect.setChecked(true);
            tvFullDressCollect.setCompoundDrawables(null, colloect2, null, null);
        } else {
            ToastUtil.showShort(this, "取消关注");
            tvFullDressCollect.setChecked(false);
            tvFullDressCollect.setCompoundDrawables(null, colloect, null, null);
        }
    }


}
