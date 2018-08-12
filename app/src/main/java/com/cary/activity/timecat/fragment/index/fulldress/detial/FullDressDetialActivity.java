package com.cary.activity.timecat.fragment.index.fulldress.detial;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.FullDressTabApi;
import com.cary.activity.timecat.fragment.index.fulldress.selection.FullDressColothCollect;
import com.cary.activity.timecat.fragment.index.fulldress.selection.SelectionPopuWindow;
import com.cary.activity.timecat.fragment.index.selectstore.detial.StoreDetialActivity;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.ShowImageAdapter;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.ShowImageApi;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.ShowImageCommitResult;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.view.BannerLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullDressDetialActivity extends BaseActivity {

    private static final String TAG = FullDressDetialActivity.class.getSimpleName();

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
    @BindView(R.id.commend_recycler)
    RecyclerView commendRecycler;
    @BindView(R.id.ll_full_dress_match_recommend)
    LinearLayout llFullDressMatchRecommend;
    @BindView(R.id.ll_full_dress_detial_desc)
    LinearLayout llFullDressDetialDesc;
    @BindView(R.id.tv_fulldress_detial_service)
    TextView tvFulldressDetialService;
    @BindView(R.id.tv_fulldress_detial_telphone)
    TextView tvFulldressDetialTelphone;
    @BindView(R.id.tv_fulldress_detial_onlinereserve)
    TextView tvFulldressDetialOnlinereserve;
    @BindView(R.id.tv_fulldress_detial_lease)
    TextView tvFulldressDetialLease;
    @BindView(R.id.tv_fulldress_detial_photograph)
    TextView tvFulldressDetialPhotograph;
    @BindView(R.id.webview_full_dress_detial_desc)
    WebView webviewFullDressDetialDesc;

    //详情
    private FullDressTabApi mFDApi;
    private List<String> bannerUrls = new ArrayList<>();
    private FullDressDetialResult FDDetialComRes;

    //显示搭配推荐
    private List<ShowImageCommitResult.Data> mLists = new ArrayList<>();
    //    private FullDessGridAdapter mAdapter;
    private ShowImageAdapter adapter;//搭配推荐的adapter
    private ShowImageApi showImageApi;
    private ShowImageCommitResult showImageCR;
    private int currentPage = 0;

    //规格选项
    private FullDressColothNorm mFdcolothNorm;
    private List<FullDressColothNorm.Data> mNormData;

    private String idFlag;//是什么模块  售买 共享
    private String idStr;//接收 详情的id
    private String token;
    private int userId;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private int storeId;//门店id

    private SelectionPopuWindow selectPopu;
    private int photoFlag = 0;

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

        /****** 展示下面的 购买 预定 拍照 *******/
        idFlag = getIntent().getStringExtra("flagTag");
        if ("0".equals(idFlag)) {
            //售卖
            tvFulldressDetialOnlinereserve.setVisibility(View.VISIBLE);
            tvFulldressDetialPhotograph.setVisibility(View.GONE);
            tvFulldressDetialLease.setVisibility(View.GONE);
        } else if ("1".equals(idFlag)) {
            //共享
            tvFulldressDetialOnlinereserve.setVisibility(View.GONE);
            tvFulldressDetialPhotograph.setVisibility(View.VISIBLE);
            tvFulldressDetialLease.setVisibility(View.VISIBLE);
        }

        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        userId = (int) sharedPreferencesHelper.getSharedPreference("id", 0);
        idStr = getIntent().getIntExtra("id", 0) + "";
        if (!TextUtils.isEmpty(idStr)) {
            Log.v(TAG,"我是接收到的Id:" + idStr);
        } else {
            Log.v(TAG, "我没有接收到id");
        }
        //获取详情信息
        mFDApi = FullDressTabApi.getApi();
        createSingleDetial();
        //获取规格
        createSingleColothNorm();

        bannerFulldressDetial.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });


        /*********搭配推荐**********/
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        commendRecycler.setLayoutManager(linearLayoutManager);
//        adapter = new ShowImageAdapter(this);
//        //设置item间距，30dp
//        commendRecycler.addItemDecoration(new SpaceItemDecoration(20));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        //通过布局管理器可以控制条目排列的顺序 true反向显示 false正常显示(默认)
        gridLayoutManager.setReverseLayout(false);
        //设置RecycleView显示的方向是水平还是垂直
        //GridLayout.HORIZONTAL水平 GridLayout.VERTICAL默认垂直
        // 三元运算符
        gridLayoutManager.setOrientation(false ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
        //设置布局管理器， 参数linearLayoutManager对象
        commendRecycler.setLayoutManager(gridLayoutManager);
        //添加Android自带的分割线
        commendRecycler.addItemDecoration(new SpaceItemDecoration(5));
        adapter = new ShowImageAdapter(this);
        commendRecycler.setAdapter(adapter);
        adapter.setItemClickListener(new ShowImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(FullDressDetialActivity.this, FullDressDetialActivity.class);
                intent.putExtra("id", mLists.get(position).getId());
                startActivity(intent);
            }
        });
//        mAdapter = new FullDessGridAdapter(this);
//        commendRecycler.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int postion) {
//                Intent intent = new Intent(FullDressDetialActivity.this, FullDressDetialActivity.class);
//                intent.putExtra("id", mLists.get(postion).getId());
//                startActivity(intent);
//            }
//        });
        commendRecycler.setNestedScrollingEnabled(false);
        showImageApi = ShowImageApi.getApi();
        currentPage = 1;
        createSingleRecommend(currentPage);
        /***********搭配推荐 end*********/


    }

    @Override
    public int getLayout() {
        return R.layout.activity_full_dess_detial;
    }


    @OnClick({R.id.iv_fulldress_back, R.id.tv_full_dress_collect, R.id.rl_full_dress_store_name,
            R.id.tv_fulldress_detial_service, R.id.tv_fulldress_detial_telphone,
            R.id.tv_fulldress_detial_onlinereserve, R.id.tv_fulldress_detial_lease,
            R.id.tv_fulldress_detial_photograph})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fulldress_back:
                finish();
                break;
            case R.id.tv_full_dress_collect:
//                setColloect(tvFullDressCollect.isChecked());
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
//                ToastUtil.showShort(this, "购买");
                selectPopu = new SelectionPopuWindow(this, clickListener,FDDetialComRes);
                selectPopu.show();
//                Intent intent = new Intent(this, PayPhotoGraphyOrderActivity.class);
//                startActivity(intent);
                photoFlag = 1;
                break;
            case R.id.tv_fulldress_detial_lease:
//                ToastUtil.showShort(this, "租借");
                selectPopu = new SelectionPopuWindow(this, clickListener,FDDetialComRes);
                selectPopu.show();
                photoFlag = 2;
                break;
            case R.id.tv_fulldress_detial_photograph:
//                ToastUtil.showShort(this, "拍照");
                selectPopu = new SelectionPopuWindow(this, clickListener,FDDetialComRes);
                selectPopu.show();
                photoFlag = 3;
                break;


        }
    }

    //确认 所选的值
    private SelectionPopuWindow.OnClickConfrmListener clickListener = new SelectionPopuWindow.OnClickConfrmListener() {
        @Override
        public void onClick(View v,String itemStr) {
//            mListItem.add(title);
            switch (v.getId()) {
                case R.id.tv_selection_confirm:
                        selectPopu.cancel();
                        Intent intent = new Intent(FullDressDetialActivity.this, FullDressDetialOrderActivity.class);
                        intent.putExtra("photoFlag", photoFlag);
                        if(!TextUtils.isEmpty(itemStr)) intent.putExtra("itemNorm",itemStr.substring(0,itemStr.length()-1));
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("fulldressdetial", (Serializable) FDDetialComRes.getData());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    break;
            }
        }
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.tv_selection_confirm:
//                    selectPopu.cancel();
//                    Intent intent = new Intent(FullDressDetialActivity.this, FullDressDetialOrderActivity.class);
//                    intent.putExtra("photoFlag", photoFlag);
//                    startActivity(intent);
//                    break;
//            }
//        }
    };

    /**************** 根据id 获取数据****************/
    private Callback<FullDressDetialResult> callbackDetial = new Callback<FullDressDetialResult>() {
        @Override
        public void onResponse(Call<FullDressDetialResult> call, Response<FullDressDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                FDDetialComRes = response.body();
                if ("00".equals(FDDetialComRes.getCode())) {
                    tvFullDressTitle.setText(FDDetialComRes.getData().getTitle());
                    tvFullDressSellNumber.setText("已售:" + FDDetialComRes.getData().getSellCount() + "笔");
                    ratingbarFullDess.setRating(FDDetialComRes.getData().getStarCount());
                    tvFullDressMoney.setText("¥" + FDDetialComRes.getData().getAmount());
                    if (FDDetialComRes.getData().getSellType() == 1) {
                        tvFullDressOldMoney.setText("  租金 ¥" + FDDetialComRes.getData().getPaiAmount());
                    } else if (FDDetialComRes.getData().getSellType() == 0) {
                        //售卖
                        tvFullDressOldMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        tvFullDressOldMoney.setText("¥原价");//+FDDetialComRes.getData().get);
                    }
                    tvFullDressStoreName.setText(FDDetialComRes.getData().getStore().getStoreName());//门店的title
                    String storeImg =  HttpUrlClient.ALIYUNPHOTOBASEURL + FDDetialComRes.getData().getStore().getImgurl();
                    Glide.with(FullDressDetialActivity.this).load(storeImg).into(ivFullDressStoreName);
                    storeId = FDDetialComRes.getData().getStoreId();
                    String imagebannerUrls = FDDetialComRes.getData().getImgurls();
                    if(!TextUtils.isEmpty(imagebannerUrls)) {
                        String imgUrls[] = imagebannerUrls.split(",");
                        for (int i = 0; i < imgUrls.length; i++) {
                            String imageStr = HttpUrlClient.ALIYUNPHOTOBASEURL + imgUrls[i];
                            bannerUrls.add(imageStr);
                        }
                        if (bannerFulldressDetial != null) {
                            bannerFulldressDetial.setViewUrls(bannerUrls, null);
                        }
                    }
                    //详细内容
                    webviewFullDressDetialDesc.loadDataWithBaseURL(null, FDDetialComRes.getData().getContent(), "text/html", "utf-8", null);

                } else {
                    ToastUtil.showShort(FullDressDetialActivity.this, FDDetialComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<FullDressDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleDetial() {
        Call<FullDressDetialResult> call = mFDApi.getService().createCommitColothId(token, idStr);
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
                    ToastUtil.showShort(FullDressDetialActivity.this, mMealColRes.getMsg());
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
        Call<FullDressColothCollect> call = mFDApi.getService().createCommitCollectId(token, idStr, userId + "");
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
                    ToastUtil.showShort(FullDressDetialActivity.this, mMealColRes.getMsg());
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
        Call<FullDressColothCollect> call = mFDApi.getService().createCommitUnCollectId(token, idStr);
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

    /***************** 搭配推荐 *********************/
    private Callback<ShowImageCommitResult> callbackrecommend = new Callback<ShowImageCommitResult>() {
        @Override
        public void onResponse(Call<ShowImageCommitResult> call, Response<ShowImageCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                showImageCR = response.body();
                if ("00".equals(showImageCR.getCode())) {
                    mLists = showImageCR.getData();
                    adapter.setDatas(mLists);
//                    mAdapter.reSetData(mLists);
                } else {
                    ToastUtil.showShort(FullDressDetialActivity.this, showImageCR.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ShowImageCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleRecommend(int currentPage) {
        Call<ShowImageCommitResult> call = showImageApi.getService().createCommit(token, currentPage + "");
        call.enqueue(callbackrecommend);
    }


    /*************************************/
    private Callback<FullDressColothNorm> callbackNorm = new Callback<FullDressColothNorm>() {
        @Override
        public void onResponse(Call<FullDressColothNorm> call, Response<FullDressColothNorm> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mFdcolothNorm = response.body();
                if ("00".equals(mFdcolothNorm.getCode())) {
                    mNormData = mFdcolothNorm.getData();
                } else {
                    ToastUtil.showShort(FullDressDetialActivity.this, mFdcolothNorm.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<FullDressColothNorm> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleColothNorm() {
        Call<FullDressColothNorm> call = mFDApi.getService().createCommitColothNorm(token, idStr);
        call.enqueue(callbackNorm);
    }

}
