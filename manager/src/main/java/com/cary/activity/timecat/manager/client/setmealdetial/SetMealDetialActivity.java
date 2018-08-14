package com.cary.activity.timecat.manager.client.setmealdetial;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.SpaceItemDecoration;
import com.cary.activity.timecat.manager.client.selectsetmeal.SetMealApi;
import com.cary.activity.timecat.manager.client.selectstore.detial.StoreCommentApi;
import com.cary.activity.timecat.manager.client.selectstore.detial.StoreDetialActivity;
import com.cary.activity.timecat.manager.client.selectstore.detial.StoreDetialCommentAdapter;
import com.cary.activity.timecat.manager.client.selectstore.detial.StoreDetialCommentResult;
import com.cary.activity.timecat.manager.crashpledge.PayPhotoGraphyOrderActivity;
import com.cary.activity.timecat.manager.util.LogUtils;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;
import com.cary.activity.timecat.manager.util.view.BannerLayout;

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
public class SetMealDetialActivity extends AppCompatActivity {

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
    //基础数据
    private SetMealApi mApi;
    private List<String> bannerUrls = new ArrayList<>();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_meal_detial);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
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
        if (!TextUtils.isEmpty(id)) {
            ToastUtil.showShort(this, "我是接收到的Id:" + id);
        } else {
            LogUtils.v("我没有接收到id");
        }
        mApi = SetMealApi.getApi();
        createSingleMeal();

        /*****banner 图片点击*****/
        bannerSetMealDetial.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
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
    }


    @OnClick({R.id.iv_set_meal_detial_back, R.id.rl_set_meal_store_name, R.id.tv_set_meal_collect, R.id.tv_set_meal_detial_service, R.id.tv_set_meal_detial_telphone, R.id.tv_set_meal_detial_onlinereserve})
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
                Intent intent = new Intent(this, PayPhotoGraphyOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("detialresult",mDetialRes);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.rl_set_meal_store_name://跳转到店铺
                Intent intent1 = new Intent(this, StoreDetialActivity.class);
                intent1.putExtra("id", storeId);//传递的是门店id
                startActivity(intent1);
                break;
        }
    }

    //banner 获取数据
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
                    String storeImg = "https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=93938f548e8ba61ec0eece2f713597cc/0e2442a7d933c895f3e511e5dd1373f083020090.jpg";
//                            HttpUrlClient.ALIYUNPHOTOBASEURL + mDetialRes.getData().getStoreImgurl();
                    Glide.with(SetMealDetialActivity.this).load(storeImg).into(ivSetMealStoreName);
                    storeId = mDetialRes.getData().getStoreId();
                    Log.v(TAG,"storeId:"+storeId);
                    tvColoth.setText(mDetialRes.getData().getClothCount()+"");
                    tvIncomePhoto.setText(mDetialRes.getData().getRucheCount()+"");
                    tvNegative.setText(mDetialRes.getData().getFilmCount()+"");


                    //banner 图片
                    String imageUrlList[] = mDetialRes.getData().getImgurls().split(",");
                    for (int i = 0; i < imageUrlList.length; i++) {
                        String imageStr = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3405134160,3879998990&fm=27&gp=0.jpg";
                        //HttpUrlClient.ALIYUNPHOTOBASEURL+imageUrlList[i];
                        bannerUrls.add(imageStr);
                    }
                    if (bannerSetMealDetial != null) {
                        bannerSetMealDetial.setViewUrls(bannerUrls, null);
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
        colloect = res.getDrawable(R.mipmap.heart4);
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

}
