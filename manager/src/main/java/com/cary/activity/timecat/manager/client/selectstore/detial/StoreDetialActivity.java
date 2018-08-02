package com.cary.activity.timecat.manager.client.selectstore.detial;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;
import com.cary.activity.timecat.manager.adapter.SpaceItemDecoration;
import com.cary.activity.timecat.manager.client.banner.BannerApi;
import com.cary.activity.timecat.manager.client.banner.BannerCommitResult;
import com.cary.activity.timecat.manager.client.myorder.confirmorder.teacher.TeacherDateActivity;
import com.cary.activity.timecat.manager.client.selectsetmeal.SelectSetMealActivity;
import com.cary.activity.timecat.manager.client.selectsetmeal.SelectSetMealRecyclerAdapter;
import com.cary.activity.timecat.manager.client.selectsetmeal.SetMealApi;
import com.cary.activity.timecat.manager.client.selectsetmeal.SetMealResult;
import com.cary.activity.timecat.manager.client.selectstore.SelectStoreActivity;
import com.cary.activity.timecat.manager.client.selectstore.SelectStoreApi;
import com.cary.activity.timecat.manager.client.setmealdetial.SetMealDetialActivity;
import com.cary.activity.timecat.manager.util.LogUtils;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;
import com.cary.activity.timecat.manager.util.view.BannerLayout;
import com.cary.activity.timecat.manager.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 门店 详情
 */
public class StoreDetialActivity extends AppCompatActivity {

    private static final String TAG = StoreDetialActivity.class.getSimpleName();
    @BindView(R.id.iv_store_detial_back)
    ImageView ivStoreDetialBack;
    @BindView(R.id.banner_store_detial)
    BannerLayout bannerStoreDetial;
    @BindView(R.id.tv_store_detial_name)
    TextView tvStoreDetialName;
    @BindView(R.id.ratingbar_store_detial)
    RatingBar ratingbarStoreDetial;
    @BindView(R.id.tv_store_detial_customer)
    TextView tvStoreDetialCustomer;
    @BindView(R.id.tv_store_detial_collect)
    CheckBox tvStoreDetialCollect;
    @BindView(R.id.rl_store_detial_title)
    RelativeLayout rlStoreDetialTitle;
    @BindView(R.id.tv_store_detial_address)
    TextView tvStoreDetialAddress;
    @BindView(R.id.rl_store_detial_address)
    RelativeLayout rlStoreDetialAddress;
    @BindView(R.id.tv_store_detial_desc_more)
    TextView tvStoreDetialDescMore;
    @BindView(R.id.tv_line_desc)
    TextView tvLineDesc;
    @BindView(R.id.tv_store_detial_desc)
    TextView tvStoreDetialDesc;
    @BindView(R.id.recycler_store_detial_desc)
    RecyclerView recyclerStoreDetialDesc;
    @BindView(R.id.rl_store_detial_desc)
    RelativeLayout rlStoreDetialDesc;
    @BindView(R.id.tv_store_detial_hotmeal_more)
    TextView tvStoreDetialHotmealMore;
    @BindView(R.id.tv_line_hotmeal)
    TextView tvLineHotmeal;
    @BindView(R.id.recycler_store_detial_hotmeal)
    RecyclerView recyclerStoreDetialHotmeal;
    @BindView(R.id.rl_store_detial_hotmeal)
    RelativeLayout rlStoreDetialHotmeal;
    @BindView(R.id.tv_store_detial_teacher_more)
    TextView tvStoreDetialTeacherMore;
    @BindView(R.id.tv_line_teacher)
    TextView tvLineTeacher;
    @BindView(R.id.recycler_store_detial_teacher)
    RecyclerView recyclerStoreDetialTeacher;
    @BindView(R.id.rl_store_detial_teacher)
    RelativeLayout rlStoreDetialTeacher;
    @BindView(R.id.tv_store_detial_photo_more)
    TextView tvStoreDetialPhotoMore;
    @BindView(R.id.tv_line_photo)
    TextView tvLinePhoto;
    @BindView(R.id.recycler_store_detial_photo)
    RecyclerView recyclerStoreDetialPhoto;
    @BindView(R.id.rl_store_detial_photo)
    RelativeLayout rlStoreDetialPhoto;
    @BindView(R.id.tv_store_detial_comment_more)
    TextView tvStoreDetialCommentMore;
    @BindView(R.id.tv_line_comment)
    TextView tvLineComment;
    @BindView(R.id.recycler_store_detial_comment)
    RecyclerView recyclerStoreDetialComment;
    @BindView(R.id.rl_store_detial_comment)
    RelativeLayout rlStoreDetialComment;
    @BindView(R.id.tv_store_detial_change)
    TextView tvStoreDetialChange;

    private BannerApi bannerApi;
    private List<String> bannerUrls = new ArrayList<>();
    private BannerCommitResult bannerComRes;

    private SelectStoreApi mSStoreApi;
    private StoreDetialResult mSStoreComRes;

    private StoreCollectApi mStoreCollectApi;
    private StoreDetialCollect mStoreColRes;
    /***热门套餐***/
    private SetMealApi mApi;
    private SetMealResult mMealRes;
    private List<SetMealResult.Data> mData;
    private SelectSetMealRecyclerAdapter mAdapter;
    /****时光老师*****/
    private ShowTeacherAdapter mTeacherAdapter;
    private List<ShowTeacherComResult.Data> mLists;
    private ShowTeacherApi mShowTeacherApi;
    private ShowTeacherComResult showImageCR;

    /******客片欣赏*****/

    private LookCustomAdapter mLookCustomAdapter;

    /********时光留言-最新评价*********/
    private StoreDetialCommentAdapter mStoreDetialCommentAdapter;
    private StoreCommentApi mCommentApi;
    private StoreDetialCommentResult mStoreCommentRes;
    private List<StoreDetialCommentResult.Data> mCommentList;


    private SharedPreferencesHelper sharePh;
    private String token;
    private int storeId;//店铺id
    private int userId;//用户id
    private String storeName;//保存城市


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detial);
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
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        storeId = getIntent().getIntExtra("id", 0);//门店的ID
        if (storeId != 0) {
            ToastUtil.showShort(this, "我是接收到的StoreId:" + storeId);
        } else {
            LogUtils.v("我没有接收到id");
        }

        bannerApi = BannerApi.getApi();
        createSingleBanner();
        bannerStoreDetial.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.v(TAG, "bannerComRes.getData().get(position).getType():" + bannerComRes.getData().get(position).getType());
                if (0 == bannerComRes.getData().get(position).getType()) {
                    Toast.makeText(StoreDetialActivity.this, "跳转到商品:" + position, Toast.LENGTH_SHORT).show();
                } else if (1 == bannerComRes.getData().get(position).getType()) {
                    Intent intent = new Intent(StoreDetialActivity.this, WebViewActivity.class);
                    intent.putExtra("url", bannerComRes.getData().get(position).getUrl());
                    startActivity(intent);
                } else {
                    Toast.makeText(StoreDetialActivity.this, "点击的此处位置position:" + position, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mSStoreApi = SelectStoreApi.getApi();
        //获取门店数据
        createSingleStore();
        //取消关注 关注的操作
        mStoreCollectApi = StoreCollectApi.getApi();
        /****热门套餐***/
        mApi = SetMealApi.getApi();
        recyclerStoreDetialHotmeal.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SelectSetMealRecyclerAdapter(this);
        recyclerStoreDetialHotmeal.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(StoreDetialActivity.this, SetMealDetialActivity.class);
                intent.putExtra("id", mData.get(postion).getId());
                startActivity(intent);
            }

            @Override
            public void onItemClick(int postion) {

            }
        });
        createSingleMeal();
        /*******时光老师*****/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerStoreDetialTeacher.setLayoutManager(linearLayoutManager);
        mTeacherAdapter = new ShowTeacherAdapter(this);
        //设置item间距，30dp
        recyclerStoreDetialTeacher.addItemDecoration(new SpaceItemDecoration(20));
        recyclerStoreDetialTeacher.setAdapter(mTeacherAdapter);
        recyclerStoreDetialTeacher.setNestedScrollingEnabled(false);

        mShowTeacherApi = ShowTeacherApi.getApi();
        createSingleTeacher();
        mTeacherAdapter.setItemClickListener(new ShowTeacherAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(StoreDetialActivity.this, TeacherDateActivity.class);
                intent.putExtra("id", mLists.get(position).getId());
                startActivity(intent);
            }
        });
        /********客片欣赏*********/
        LinearLayoutManager linearLayoutManagerPhoto = new LinearLayoutManager(this);
        linearLayoutManagerPhoto.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerStoreDetialPhoto.setLayoutManager(linearLayoutManagerPhoto);
        mLookCustomAdapter = new LookCustomAdapter(this);
        //设置item间距，30dp
        recyclerStoreDetialPhoto.addItemDecoration(new SpaceItemDecoration(20));
        mLookCustomAdapter = new LookCustomAdapter(this);
        recyclerStoreDetialPhoto.setAdapter(mLookCustomAdapter);
        recyclerStoreDetialPhoto.setNestedScrollingEnabled(false);

        /*********时光留言*********/
        LinearLayoutManager linearLayoutManagerComment = new LinearLayoutManager(this);
        linearLayoutManagerComment.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerStoreDetialComment.setLayoutManager(linearLayoutManagerComment);
        mStoreDetialCommentAdapter = new StoreDetialCommentAdapter(this);
        recyclerStoreDetialComment.addItemDecoration(new SpaceItemDecoration(20));
        recyclerStoreDetialComment.setAdapter(mStoreDetialCommentAdapter);
        recyclerStoreDetialComment.setNestedScrollingEnabled(false);

        mCommentApi = StoreCommentApi.getApi();
        createSingleComment();

    }


    @OnClick({R.id.iv_store_detial_back, R.id.tv_store_detial_collect, R.id.tv_store_detial_desc_more, R.id.tv_store_detial_hotmeal_more, R.id.tv_store_detial_teacher_more, R.id.tv_store_detial_photo_more, R.id.tv_store_detial_comment_more, R.id.tv_store_detial_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_store_detial_back:
                finish();
                break;
            case R.id.tv_store_detial_collect:
                if (tvStoreDetialCollect.isChecked()) {
                    //选中
                    createSingleStoreCollect();
                } else {
                    //取消 未选中
                    createSingleStoreUnCollect();
                }
                break;
            case R.id.tv_store_detial_desc_more:

                break;
            case R.id.tv_store_detial_hotmeal_more:
                Intent intent = new Intent(StoreDetialActivity.this, SelectSetMealActivity.class);
                intent.putExtra("id", storeId);
                startActivity(intent);
                break;
            case R.id.tv_store_detial_teacher_more:

                break;
            case R.id.tv_store_detial_photo_more:

                break;
            case R.id.tv_store_detial_comment_more:

                break;
            case R.id.tv_store_detial_change:
                sharePh.put("storeId", storeId);
                sharePh.put("storeName",storeName);
                Intent intent1 = new Intent(this, SelectStoreActivity.class);
                setResult(1001,intent1);
                finish();
                break;
        }
    }

    //banner 获取数据
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
                    if (bannerStoreDetial != null) {
                        bannerStoreDetial.setViewUrls(bannerUrls, null);
                    }
                } else {
                    ToastUtil.showShort(StoreDetialActivity.this, bannerComRes.getMsg());
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
        Call<BannerCommitResult> call = bannerApi.getService().createCommit();
        call.enqueue(callbackbanner);
    }

    //门店获取数据
    private Callback<StoreDetialResult> callbackStore = new Callback<StoreDetialResult>() {
        @Override
        public void onResponse(Call<StoreDetialResult> call, Response<StoreDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mSStoreComRes = response.body();
                if ("00".equals(mSStoreComRes.getCode())) {
                    storeName = mSStoreComRes.getData().getCity();
                    tvStoreDetialName.setText(mSStoreComRes.getData().getStoreName());
                    ratingbarStoreDetial.setRating(mSStoreComRes.getData().getStarCount());
                    tvStoreDetialCustomer.setText("服务顾客：" + mSStoreComRes.getData().getServiceCount() + "人次");
//                   缺少字段 判断是否是关注
// if()
                    String AddressStr = mSStoreComRes.getData().getProvince() + mSStoreComRes.getData().getCity() + mSStoreComRes.getData().getDetail();
                    tvStoreDetialAddress.setText(AddressStr);
                    tvStoreDetialDesc.setText(mSStoreComRes.getData().getContent());
                } else {
                    ToastUtil.showShort(StoreDetialActivity.this, mSStoreComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<StoreDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleStore() {
        Call<StoreDetialResult> call = mSStoreApi.getService().createCommitID(token, storeId + "");
        call.enqueue(callbackStore);
    }

    //关注门店
    private Callback<StoreDetialCollect> callbackStoreCollect = new Callback<StoreDetialCollect>() {
        @Override
        public void onResponse(Call<StoreDetialCollect> call, Response<StoreDetialCollect> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mStoreColRes = response.body();
                if ("00".equals(mStoreColRes.getCode())) {
                    setColloect(true);
                } else {
                    ToastUtil.showShort(StoreDetialActivity.this, mStoreColRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<StoreDetialCollect> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleStoreCollect() {
        userId = (int) sharePh.getSharedPreference("id", 0);
        Call<StoreDetialCollect> call = mStoreCollectApi.getService().createCommitCollect(token, storeId + "", userId);
        call.enqueue(callbackStoreCollect);
    }

    //取消关注门店
    private Callback<StoreDetialCollect> callbackStoreUnCollect = new Callback<StoreDetialCollect>() {
        @Override
        public void onResponse(Call<StoreDetialCollect> call, Response<StoreDetialCollect> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mStoreColRes = response.body();
                if ("00".equals(mStoreColRes.getCode())) {
                    setColloect(false);
                } else {
                    ToastUtil.showShort(StoreDetialActivity.this, mStoreColRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<StoreDetialCollect> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleStoreUnCollect() {
        Call<StoreDetialCollect> call = mStoreCollectApi.getService().createCommitUnCollect(token, storeId + "");
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
            tvStoreDetialCollect.setChecked(true);
            tvStoreDetialCollect.setCompoundDrawables(null, colloect2, null, null);
        } else {
//            ToastUtil.showShort(this, "取消关注");
            tvStoreDetialCollect.setChecked(false);
            tvStoreDetialCollect.setCompoundDrawables(null, colloect, null, null);
        }
    }

    /**************热门套餐****************/
    //    热门套餐 加载数据
    private Callback<SetMealResult> callbackMeal = new Callback<SetMealResult>() {
        @Override
        public void onResponse(Call<SetMealResult> call, Response<SetMealResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mMealRes = response.body();
                if ("00".equals(mMealRes.getCode())) {
                    mData = mMealRes.getData();
                    mAdapter.reSetData(mData);
                } else {
                    ToastUtil.showShort(StoreDetialActivity.this, mMealRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<SetMealResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleMeal() {
        Call<SetMealResult> call = mApi.getService().createCommit(token, storeId + "");
        call.enqueue(callbackMeal);
    }

    /****时光老师********/
    private Callback<ShowTeacherComResult> callbackTeacher = new Callback<ShowTeacherComResult>() {
        @Override
        public void onResponse(Call<ShowTeacherComResult> call, Response<ShowTeacherComResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                showImageCR = response.body();
                if ("00".equals(showImageCR.getCode())) {
                    mLists = showImageCR.getData();
                    mTeacherAdapter.setDatas(mLists);
                } else {
                    ToastUtil.showShort(StoreDetialActivity.this, showImageCR.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ShowTeacherComResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleTeacher() {
        Call<ShowTeacherComResult> call = mShowTeacherApi.getService().createCommitList(token, storeId + "");
        call.enqueue(callbackTeacher);
    }
    /********客片欣赏*********/


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
                    ToastUtil.showShort(StoreDetialActivity.this, mStoreCommentRes.getMsg());
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
        Call<StoreDetialCommentResult> call = mCommentApi.getService().createCommitList(token, storeId + "");
        call.enqueue(callbackComment);
    }
}
