package com.cary.activity.timecat.fragment.index.setmealdetial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.banner.BannerApi;
import com.cary.activity.timecat.fragment.index.banner.BannerCommitResult;
import com.cary.activity.timecat.fragment.index.basicser.BasicServiceActivity;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.ConfirmOrderActivity;
import com.cary.activity.timecat.fragment.index.selectsetmeal.SetMealApi;
import com.cary.activity.timecat.fragment.index.selectstore.detial.StoreCommentApi;
import com.cary.activity.timecat.fragment.index.selectstore.detial.StoreDetialActivity;
import com.cary.activity.timecat.fragment.index.selectstore.detial.StoreDetialCommentAdapter;
import com.cary.activity.timecat.fragment.index.selectstore.detial.StoreDetialCommentResult;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.view.BannerLayout;
import com.cary.activity.timecat.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 套餐详情
 */
public class SetMealDetialActivity extends BaseActivity {

    private static final String TAG = SetMealDetialActivity.class.getSimpleName();

    @BindView(R.id.banner_set_meal_detial)
    BannerLayout bannerSetMealDetial;
    @BindView(R.id.tv_set_meal_detial_service)
    TextView tvSetMealDetialService;
    @BindView(R.id.tv_set_meal_detial_telphone)
    TextView tvSetMealDetialTelphone;
    @BindView(R.id.tv_set_meal_detial_onlinereserve)
    TextView tvSetMealDetialOnlinereserve;
    @BindView(R.id.iv_set_meal_detial_back)
    ImageView ivSetMealDetial;
    @BindView(R.id.tv_set_meal_title)
    TextView tvSetMealTitle;
    @BindView(R.id.tv_set_meal_sell_number)
    TextView tvSetMealSellNumber;
    @BindView(R.id.ratingbar_set_meal)
    RatingBar ratingbarSetMeal;
    @BindView(R.id.tv_set_meal_money)
    TextView tvSetMealMoney;
    @BindView(R.id.tv_set_meal_old_money)
    TextView tvSetMealOldMoney;
    @BindView(R.id.tv_set_meal_collect)
    CheckBox tvSetMealCollect;
    @BindView(R.id.ll_set_meal_title)
    LinearLayout llSetMealTitle;
    @BindView(R.id.iv_set_meal_store_name)
    ImageView ivSetMealStoreName;
    @BindView(R.id.tv_set_meal_store_name)
    TextView tvSetMealStoreName;
    @BindView(R.id.rl_set_meal_store_name)
    LinearLayout rlSetMealStoreName;
    //    @BindView(R.id.iv_setmeal_detial_user_head)
//    ImageView ivSetmealDetialUserHead;
//    @BindView(R.id.tv_setmeal_detial_name)
//    TextView tvSetmealDetialName;
//    @BindView(R.id.tv_setmeal_detial_user_time)
//    TextView tvSetmealDetialUserTime;
//    @BindView(R.id.ratingbar_setmeal_detial)
//    RatingBar ratingbarSetmealDetial;
//    @BindView(R.id.tv_setmeal_detial_desc)
//    TextView tvSetmealDetialDesc;
//    @BindView(R.id.recycler_tv_setmeal_detial_img)
//    RecyclerView recyclerTvSetmealDetialImg;
    @BindView(R.id.tv_income_photo)
    TextView tvIncomePhoto;
    @BindView(R.id.rl_income_photo)
    RelativeLayout rlIncomePhoto;
    @BindView(R.id.tv_negative)
    TextView tvNegative;
    @BindView(R.id.rl_negative)
    RelativeLayout rlNegative;
    @BindView(R.id.tv_coloth)
    TextView tvColoth;
    @BindView(R.id.rl_coloth)
    RelativeLayout rlColoth;
    @BindView(R.id.recycler_set_meal_detial)
    RecyclerView recyclerSetMealDetial;
    @BindView(R.id.recycler_setmeal_detial_comment)
    RecyclerView recyclerSetmealDetialComment;
    @BindView(R.id.basic_serve)
    RelativeLayout mLayoutBasicServe;
    @BindView(R.id.layout_service)
    LinearLayout mLayoutService;

    //banner数据
    private BannerApi bannerApi;
    private List<String> bannerUrls = new ArrayList<>();
    private BannerCommitResult bannerComRes;

    //基础数据
    private SetMealApi mApi;
    private SetMealDetialResult mDetialRes;
    //关注
    private SetMealCollectResult mMealColRes;

    //留言评论
    private StoreDetialCommentAdapter mStoreDetialCommentAdapter;
    private StoreCommentApi mCommentApi;
    private StoreDetialCommentResult mStoreCommentRes;
    private List<StoreDetialCommentResult.Data> mCommentList;

    private String token;
    private String id;//套餐
    private int userId;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private int storeId;//门店id
    private String titleStr[] = {"底片全送", "专车接送", "满意在付款", "送货上门", "赠送U盘", "一对一拍摄", "VIP化妆间"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT); //设置navigationbar颜色为透明
        }
        ButterKnife.bind(this);

        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        userId = (int) sharedPreferencesHelper.getSharedPreference("id", 0);
        id = getIntent().getIntExtra("id", 0) + "";

        mApi = SetMealApi.getApi();
        createSingleMeal();

        /*****banner 图片点击*****/
        bannerApi = BannerApi.getApi();
        createSingleBanner();
        bannerSetMealDetial.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.v(TAG, "bannerComRes.getData().get(position).getType():" + bannerComRes.getData().get(position).getType());
                if (0 == bannerComRes.getData().get(position).getType()) {
                    Toast.makeText(SetMealDetialActivity.this, "跳转到商品:" + position, Toast.LENGTH_SHORT).show();
                } else if (1 == bannerComRes.getData().get(position).getType()) {
                    Intent intent = new Intent(SetMealDetialActivity.this, WebViewActivity.class);
                    intent.putExtra("url", bannerComRes.getData().get(position).getUrl());
                    startActivity(intent);
                } else {
                    Toast.makeText(SetMealDetialActivity.this, "点击的此处位置position:" + position, Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*********时光留言*********/
        LinearLayoutManager linearLayoutManagerComment = new LinearLayoutManager(this);
        linearLayoutManagerComment.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerSetmealDetialComment.setLayoutManager(linearLayoutManagerComment);
        mStoreDetialCommentAdapter = new StoreDetialCommentAdapter(this);
        recyclerSetmealDetialComment.addItemDecoration(new SpaceItemDecoration(20));
        recyclerSetmealDetialComment.setAdapter(mStoreDetialCommentAdapter);
        recyclerSetmealDetialComment.setNestedScrollingEnabled(false);

        mCommentApi = StoreCommentApi.getApi();
        createSingleComment();
        initBasicServiceView();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_set_meal_detial;
    }

    /**
     * 初始化基础模块
     */
    @SuppressLint("ResourceAsColor")
    private void initBasicServiceView() {
        int length;
        if (titleStr.length / 3 == 0) {
            length = 1;
        } else if (titleStr.length % 3 == 0 && titleStr.length / 3 != 0) {
            length = titleStr.length / 3;
        } else {
            length = titleStr.length / 3 + 1;
        }
        for (int i = 0; i < length; i++) {
            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            layout.setLayoutParams(params);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < 3; j++) {
                TextView textView = new TextView(this);
                textView.setTextColor(R.color.color_333333);
                textView.setTextSize(13);
                textView.setPadding(3, 3, 3, 3);
                textView.setText("示范方安抚");
                layout.addView(textView);
            }
            mLayoutService.addView(layout);
        }

    }

    @OnClick({R.id.iv_set_meal_detial_back, R.id.basic_serve, R.id.rl_set_meal_store_name, R.id.tv_set_meal_collect, R.id.tv_set_meal_detial_service, R.id.tv_set_meal_detial_telphone, R.id.tv_set_meal_detial_onlinereserve})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_set_meal_detial_back:
                finish();
                break;
            case R.id.tv_set_meal_collect:
                if (tvSetMealCollect.isChecked()) {
                    //选中
                    createSingleMealCollect();
                } else {
                    //取消 未选中
                    createSingleMealUnCollect();
                }
                break;
            case R.id.tv_set_meal_detial_service:
                ToastUtil.showShort(this, "客服");
                break;
            case R.id.tv_set_meal_detial_telphone:
                ToastUtil.showShort(this, "电话");
                break;
            case R.id.tv_set_meal_detial_onlinereserve:
//                ToastUtil.showShort(this, "在线预定");
//                Intent intent = new Intent(this, PayPhotoGraphyOrderActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("detialresult", mDetialRes);
//                intent.putExtras(bundle);
//                startActivity(intent);
                Intent intent = new Intent(this, ConfirmOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("detialresult", mDetialRes);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.rl_set_meal_store_name://跳转到店铺
                Intent intent1 = new Intent(this, StoreDetialActivity.class);
                intent1.putExtra("id", storeId);//传递的是门店id
                startActivity(intent1);
                break;
            case R.id.basic_serve:
                startActivity(new Intent(this, BasicServiceActivity.class));
                break;

        }
    }

    // 获取数据
    private Callback<SetMealDetialResult> callbackdetial = new Callback<SetMealDetialResult>() {
        @Override
        public void onResponse(Call<SetMealDetialResult> call, Response<SetMealDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mDetialRes = response.body();
                if ("00".equals(mDetialRes.getCode())) {
                    tvSetMealTitle.setText(mDetialRes.getData().getTitle());
                    tvSetMealSellNumber.setText("已售:" + mDetialRes.getData().getSellCount() + "笔");
                    ratingbarSetMeal.setRating(mDetialRes.getData().getEvaStar());
                    tvSetMealMoney.setText("¥" + mDetialRes.getData().getPrice());
                    tvSetMealOldMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    tvSetMealOldMoney.setText("¥:" + mDetialRes.getData().getMarketPrice());
                    tvSetMealStoreName.setText(mDetialRes.getData().getStoreName());//门店的title
                    String storeImg = HttpUrlClient.ALIYUNPHOTOBASEURL + mDetialRes.getData().getStoreImgurl();
                    Glide.with(SetMealDetialActivity.this).load(storeImg).into(ivSetMealStoreName);
                    storeId = mDetialRes.getData().getStoreId();
                    tvColoth.setText(mDetialRes.getData().getClothCount() + "");
                    tvIncomePhoto.setText(mDetialRes.getData().getRucheCount() + "");
                    tvNegative.setText(mDetialRes.getData().getFilmCount() + "");
                    //设置banner
                    String imagebanners = mDetialRes.getData().getImgurls();
                    if (!TextUtils.isEmpty(imagebanners)) {
                        String imageUrls[] = imagebanners.split(",");
                        for (int i = 0; i < imageUrls.length; i++) {
                            bannerUrls.add(HttpUrlClient.ALIYUNPHOTOBASEURL + imageUrls[i]);
                        }
                        if (bannerSetMealDetial != null) {
                            bannerSetMealDetial.setViewUrls(bannerUrls, null);
                        }
                    }
                    //判断是否是关注
                    if (!TextUtils.isEmpty(mDetialRes.getData().getLoginUid())) {
                        setColloect(true);
                    } else {
                        setColloect(false);
                    }
                } else {
                    ToastUtil.showShort(SetMealDetialActivity.this, mDetialRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<SetMealDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleMeal() {
        Call<SetMealDetialResult> call = mApi.getService().createCommitId(token, id);
        call.enqueue(callbackdetial);
    }

    /********时光留言-最新评价*********/
    private Callback<StoreDetialCommentResult> callbackComment = new Callback<StoreDetialCommentResult>() {
        @Override
        public void onResponse(Call<StoreDetialCommentResult> call, Response<StoreDetialCommentResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mStoreCommentRes = response.body();
                if ("00".equals(mStoreCommentRes.getCode())) {
                    mCommentList = mStoreCommentRes.getData();
                    mStoreDetialCommentAdapter.setDatas(mCommentList);
                } else {
                    ToastUtil.showShort(SetMealDetialActivity.this, mStoreCommentRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<StoreDetialCommentResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleComment() {
        Call<StoreDetialCommentResult> call = mCommentApi.getService().createCommitMealList(token, id + "");
        call.enqueue(callbackComment);
    }

    //关注套餐
    private Callback<SetMealCollectResult> callbackStoreCollect = new Callback<SetMealCollectResult>() {
        @Override
        public void onResponse(Call<SetMealCollectResult> call, Response<SetMealCollectResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mMealColRes = response.body();
                if ("00".equals(mMealColRes.getCode())) {
                    setColloect(true);
                } else {
                    ToastUtil.showShort(SetMealDetialActivity.this, mMealColRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<SetMealCollectResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleMealCollect() {
        userId = (int) sharedPreferencesHelper.getSharedPreference("id", 0);
        Call<SetMealCollectResult> call = mApi.getService().createCommitCollectId(token, id + "", userId + "");
        call.enqueue(callbackStoreCollect);
    }

    //取消关注套餐
    private Callback<SetMealCollectResult> callbackStoreUnCollect = new Callback<SetMealCollectResult>() {
        @Override
        public void onResponse(Call<SetMealCollectResult> call, Response<SetMealCollectResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mMealColRes = response.body();
                if ("00".equals(mMealColRes.getCode())) {
                    setColloect(false);
                } else {
                    ToastUtil.showShort(SetMealDetialActivity.this, mMealColRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<SetMealCollectResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleMealUnCollect() {
        Call<SetMealCollectResult> call = mApi.getService().createCommitUnCollectId(token, id + "");
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
//            ToastUtil.showShort(this, "关注");
            tvSetMealCollect.setChecked(true);
            tvSetMealCollect.setCompoundDrawables(null, colloect2, null, null);
        } else {
//            ToastUtil.showShort(this, "取消关注");
            tvSetMealCollect.setChecked(false);
            tvSetMealCollect.setCompoundDrawables(null, colloect, null, null);
        }
    }

    //套餐详情里面的banner是从url里面获取  banner 获取数据
    private Callback<BannerCommitResult> callbackbanner = new Callback<BannerCommitResult>() {
        @Override
        public void onResponse(Call<BannerCommitResult> call, Response<BannerCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                bannerComRes = response.body();
                if ("00".equals(bannerComRes.getCode())) {
                    for (int i = 0; i < bannerComRes.getData().size(); i++) {
                        bannerUrls.add(bannerComRes.getData().get(i).getImgurl());
                    }
                    if (bannerSetMealDetial != null) {
                        bannerSetMealDetial.setViewUrls(bannerUrls, null);
                    }
                } else {
                    ToastUtil.showShort(SetMealDetialActivity.this, bannerComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<BannerCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleBanner() {
        Call<BannerCommitResult> call = bannerApi.getService().createCommitStore();
        call.enqueue(callbackbanner);
    }

}
