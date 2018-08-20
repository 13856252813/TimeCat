package com.cary.activity.timecat.fragment.index.star;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 明星榜
 */
public class StarListActivity extends BaseActivity {
    private static final String TAG = StarListActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tvStarNameText)
    TextView tvStarNameText;
    @BindView(R.id.activity_select_store_list_item_img)
    ImageView activitySelectStoreListItemImg;
    @BindView(R.id.activity_select_store_list_item_title)
    TextView activitySelectStoreListItemTitle;
    @BindView(R.id.activity_select_store_list_item_address)
    TextView activitySelectStoreListItemAddress;
    @BindView(R.id.activity_select_store_list_item_customer)
    TextView activitySelectStoreListItemCustomer;
    @BindView(R.id.tv_select_store)
    TextView tvSelectStore;
    @BindView(R.id.activity_select_store_list_item_address_layout)
    RelativeLayout activitySelectStoreListItemAddressLayout;
    @BindView(R.id.activity_select_store_list_item_ratingbar)
    RatingBar activitySelectStoreListItemRatingbar;
    @BindView(R.id.ivStarhead)
    ImageView ivStarhead;
    @BindView(R.id.tvStarName)
    TextView tvStarName;
    @BindView(R.id.tvStarStoreName)
    TextView tvStarStoreName;
    @BindView(R.id.tv_select_star)
    TextView tvSelectStar;
    @BindView(R.id.recycler_select_teacher)
    RecyclerView recyclerSelectTeacher;

    private StarTeacherAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private StarListResult mRes;
    private StarApi sApi;
    private String token;
    private SharedPreferencesHelper sharedPreferencesHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleText.setText("明星榜");
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sApi = StarApi.getApi();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        createSingle();

        mLayoutManager = new LinearLayoutManager(this);
        recyclerSelectTeacher.setLayoutManager(mLayoutManager);
        recyclerSelectTeacher.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new StarTeacherAdapter(this);
        recyclerSelectTeacher.setAdapter(mAdapter);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_star_list;
    }

    private Callback<StarListResult> callbackPage = new Callback<StarListResult>() {
        @Override
        public void onResponse(Call<StarListResult> call, Response<StarListResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    //                    loadListDate(false, true, recyclerViewSelectStore, R.layout.activity_select_store_item_layout, mSStoreData);
//                    recyclerViewSelectStore.setNestedScrollingEnabled(false);
                    //给imageView设置图片数据
//            mImageView.setImageResource(data.icon);
                    RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                            .override(420, 360)
//                    .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.avatarw)
                            .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
                    String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL + mRes.getData().getStores().get(0).getImgurl();
                    Glide.with(StarListActivity.this).load(imageUrl).apply(options2).into(activitySelectStoreListItemImg);
                    //给TextView设置文本数据
                    tvStarStoreName.setText(mRes.getData().getStores().get(0).getStoreName());
                    activitySelectStoreListItemAddress.setText(mRes.getData().getStores().get(0).getProvince() + mRes.getData().getStores().get(0).getCity() + mRes.getData().getStores().get(0).getDetail());
                    activitySelectStoreListItemCustomer.setText("服务顾客：" + mRes.getData().getStores().get(0).getServiceCount() + "人次");
                    activitySelectStoreListItemRatingbar.setRating(mRes.getData().getStores().get(0).getStarCount());
                    mAdapter.reSetData(mRes.getData().getTeachers());

                } else {
                    ToastUtil.showShort(StarListActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<StarListResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<StarListResult> call = sApi.getService().createCommitList(token);
        call.enqueue(callbackPage);
    }
}
