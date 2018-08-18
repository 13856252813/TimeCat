package com.cary.activity.timecat.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.MyRealApplication;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.selectstore.detial.StoreCommentApi;
import com.cary.activity.timecat.fragment.index.selectstore.detial.StoreDetialCommentAdapter;
import com.cary.activity.timecat.fragment.index.selectstore.detial.StoreDetialCommentResult;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends BaseActivity {

    private static final String TAG=CommentActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.comment_list)
    RecyclerView listView;
    private StoreDetialCommentAdapter mStoreDetialCommentAdapter;
    private StoreCommentApi mCommentApi;
    private String token;
    private int userId;
    private String id;

    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferencesHelper=new SharedPreferencesHelper(this);

        mCommentApi = StoreCommentApi.getApi();
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        userId = (int) sharedPreferencesHelper.getSharedPreference("id", 0);
        id = getIntent().getStringExtra("id");
        titleText.setText("时光留言");
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        rlTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        initRecyclerView();
        createSingleComment();

    }


    public void initRecyclerView(){
        /*********时光留言*********/
        LinearLayoutManager linearLayoutManagerComment = new LinearLayoutManager(this);
        linearLayoutManagerComment.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(linearLayoutManagerComment);
        mStoreDetialCommentAdapter = new StoreDetialCommentAdapter(this);
        listView.addItemDecoration(new SpaceItemDecoration(20));
        listView.setAdapter(mStoreDetialCommentAdapter);
        listView.setNestedScrollingEnabled(false);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_comment;
    }


    @OnClick({R.id.title_back})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.title_back:
                finish();
                break;
        }

    }

    /********时光留言-最新评价*********/
    private Callback<StoreDetialCommentResult> callbackComment = new Callback<StoreDetialCommentResult>() {
        @Override
        public void onResponse(Call<StoreDetialCommentResult> call, Response<StoreDetialCommentResult> response) {
            if (response.isSuccessful()) {
                StoreDetialCommentResult mStoreCommentRes = response.body();
                if ("00".equals(mStoreCommentRes.getCode())) {
                    List<StoreDetialCommentResult.DataBean> mCommentList = mStoreCommentRes.getData();
                    mStoreDetialCommentAdapter.setDatas(mCommentList);
                } else {
                    ToastUtil.showShort(MyRealApplication.getInstance(), mStoreCommentRes.getMsg());
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

}
