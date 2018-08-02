package com.cary.activity.timecat.fragment.index.timeclouddish.showimage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.setmealdetial.SetMealDetialActivity;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowImageActivity extends BaseActivity {
    private static final String TAG = ShowImageActivity.class.getSimpleName();

    String fileName = "";
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.imageview_show_image)
    ImageView imageviewShowImage;
    @BindView(R.id.textview_show_image_note)
    TextView textviewShowImageNote;
    @BindView(R.id.show_image_recycler)
    RecyclerView showImageRecycler;

    private List<ShowImageCommitResult.Data> mLists = new ArrayList<>();
    private ShowImageAdapter adapter;
    private ShowImageApi showImageApi;
    private ShowImageCommitResult showImageCR;
    private SharedPreferencesHelper shareHelper;
    private int currentPage = 0;
    private String token;
    private int idStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_image);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        ButterKnife.bind(this);
        titleText.setText("查看图片");
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        rlTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleBack.setPadding(20, 0, 0, 0);

        idStr= getIntent().getIntExtra("id",0);

//        showImageRecycler = (RecyclerView) findViewById(R.id.show_image_recycler);
        /**
         * 创建一个linearlayoutmaneger对象，并将他设置到recyclerview当中。layoutmanager用于指定
         * recyclerview的布局方式，这里是线性布局的意思。可以实现和listview类似的效果。
         * 接下来我们创建了Fruitadapter的实例，并将数据传进去
         */
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        showImageRecycler.setLayoutManager(linearLayoutManager);
//        adapter = new ShowImageAdapter(this);
//        //设置item间距，30dp
//        showImageRecycler.addItemDecoration(new SpaceItemDecoration(20));
//        showImageRecycler.setAdapter(adapter);

        shareHelper = new SharedPreferencesHelper(this);
        showImageApi = ShowImageApi.getApi();
        token = shareHelper.getSharedPreference("token", "")+"";
        currentPage = 1;
        //获取单个图片数据
        createSingleImage();
        createSingle(currentPage + "");

        adapter.setItemClickListener(new ShowImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ToastUtil.showShort(ShowImageActivity.this, "postion:" + position);
                Intent intent = new Intent(ShowImageActivity.this, SetMealDetialActivity.class);
                intent.putExtra("id", mLists.get(position).getId());
                startActivity(intent);
            }
        });

        /*********套餐推荐**********/
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
        showImageRecycler.setLayoutManager(gridLayoutManager);
        //添加Android自带的分割线
        showImageRecycler.addItemDecoration(new SpaceItemDecoration(5));
        adapter = new ShowImageAdapter(this);
        showImageRecycler.setAdapter(adapter);
        adapter.setItemClickListener(new ShowImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ShowImageActivity.this, SetMealDetialActivity.class);
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
        showImageRecycler.setNestedScrollingEnabled(false);
        showImageApi = ShowImageApi.getApi();
        currentPage = 1;
        createSingleRecommend(currentPage);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_show_image;
    }

    @OnClick({R.id.title_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }

    private Callback<ShowImageCommitResult> callback = new Callback<ShowImageCommitResult>() {
        @Override
        public void onResponse(Call<ShowImageCommitResult> call, Response<ShowImageCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                showImageCR = response.body();
                if ("00".equals(showImageCR.getCode())) {
                    mLists = showImageCR.getData();
                    adapter.setDatas(mLists);
                } else {
                    ToastUtil.showShort(ShowImageActivity.this, showImageCR.getMsg());
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

    private void createSingle(String currentPage) {
        Call<ShowImageCommitResult> call = showImageApi.getService().createCommit(token, currentPage);
        call.enqueue(callback);
    }  private Callback<ImageDataResult> callbackImage = new Callback<ImageDataResult>() {
        @Override
        public void onResponse(Call<ImageDataResult> call, Response<ImageDataResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                ImageDataResult showImageCR = response.body();
                if ("00".equals(showImageCR.getCode())) {
                    textviewShowImageNote.setText(showImageCR.getData().getNote());
                    if (!TextUtils.isEmpty(fileName)) {
                        fileName = HttpUrlClient.ALIYUNPHOTOBASEURL + showImageCR.getData().getBucketName();
                        RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                                .override(960, 480)
                                .placeholder(R.mipmap.ic_launcher)
                                .error(R.mipmap.ic_launcher)
                                .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
                        Glide.with(ShowImageActivity.this).load(fileName).apply(options2).into(imageviewShowImage);
                    }

                } else {
                    ToastUtil.showShort(ShowImageActivity.this, showImageCR.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ImageDataResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleImage() {
        Call<ImageDataResult> call = showImageApi.getService().createCommitImage(token,idStr);
        call.enqueue(callbackImage);
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
                    ToastUtil.showShort(ShowImageActivity.this, showImageCR.getMsg());
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

}
