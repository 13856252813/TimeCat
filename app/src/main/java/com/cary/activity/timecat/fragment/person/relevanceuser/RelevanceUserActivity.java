package com.cary.activity.timecat.fragment.person.relevanceuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 关联用户
 */
public class RelevanceUserActivity extends AppCompatActivity {

    private static final String TAG = RelevanceUserActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.et_add_relevance_user)
    EditText etAddRelevanceUser;
    @BindView(R.id.btn_add_relevance_user)
    Button btnAddRelevanceUser;
    @BindView(R.id.ll_norelevanceuser)
    LinearLayout llNorelevanceuser;
    @BindView(R.id.iv_relevance_user_my)
    ImageView ivRelevanceUserMy;
    @BindView(R.id.tv_relevance_user_my)
    TextView tvRelevanceUserMy;
    @BindView(R.id.iv_relevance_user)
    ImageView ivRelevanceUser;
    @BindView(R.id.tv_relevance_user)
    TextView tvRelevanceUser;
    @BindView(R.id.tv_relevance_user_change)
    TextView tvRelevanceUserChange;
    @BindView(R.id.tv_relevance_user_delete)
    TextView tvRelevanceUserDelete;
    @BindView(R.id.ll_relevance_user)
    LinearLayout llRelevanceUser;

    private int relevanceID;
    private int relevanceadd=0;

    private RelevanceUserApi mApi;
    private RelevanceResult mResList;
    private RelevanceDetialResult mResData;
    private SharedPreferencesHelper sharePH;
    private int uid;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relevance_user);
        ButterKnife.bind(this);

        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        sharePH = new SharedPreferencesHelper(this);
        token = (String) sharePH.getSharedPreference("token", "");
        uid = (int) sharePH.getSharedPreference("id", 0);
        mApi = RelevanceUserApi.getApi();

        relevanceID = getIntent().getIntExtra("relevance", 0);
        relevanceadd = getIntent().getIntExtra("",0);
        if (relevanceID == 0) {
            titleText.setText("添加关联用户");
            llNorelevanceuser.setVisibility(View.VISIBLE);
            llRelevanceUser.setVisibility(View.GONE);
        } else {
            titleText.setText("关联用户");
            llNorelevanceuser.setVisibility(View.GONE);
            llRelevanceUser.setVisibility(View.VISIBLE);
            createSingleList();
        }


    }

    @OnClick({R.id.title_back, R.id.btn_add_relevance_user, R.id.tv_relevance_user_change, R.id.tv_relevance_user_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_add_relevance_user:
                String mPhone = etAddRelevanceUser.getText().toString().trim();
                if (!TextUtils.isEmpty(mPhone)) {
                    if(relevanceadd == 0){
                        //增加
                        createSingle(mPhone);
                    }else {
                        //更换
                        createSingleChange(mPhone);
                    }
                } else {
                    ToastUtil.showShort(this, "请输入手机号");
                }
                break;
            case R.id.tv_relevance_user_change:
                ToastUtil.showShort(this, "更换关联用户：" + relevanceID);
                Intent intent = new Intent(this, RelevanceUserActivity.class);
                intent.putExtra("relevance", mResList.getData().get(0).getId());
                intent.putExtra("relevanceadd",1);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_relevance_user_delete:
//                ToastUtil.showShort(this, "删除关联用户：" + relevanceID);
                createSingleDel();
                break;
        }
    }

    //增加
    private Callback<RelevanceDetialResult> callback = new Callback<RelevanceDetialResult>() {
        @Override
        public void onResponse(Call<RelevanceDetialResult> call, Response<RelevanceDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mResData = response.body();
                if ("00".equals(mResData.getCode())) {
                    finish();
                } else {
                    ToastUtil.showShort(RelevanceUserActivity.this, mResData.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<RelevanceDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(String mobile) {
        Call<RelevanceDetialResult> call = mApi.getService().createCommitAdd(token, uid, mobile);
        call.enqueue(callback);
    }
    private void createSingleChange(String mobile) {
        Call<RelevanceDetialResult> call = mApi.getService().createCommitPutId(token, uid, mobile);
        call.enqueue(callback);
    }
    //删除
    private void createSingleDel() {
        Call<RelevanceDetialResult> call = mApi.getService().createCommitDelId(token, uid);
        call.enqueue(callback);
    }

    //获取关联用户
    private Callback<RelevanceResult> callbackList = new Callback<RelevanceResult>() {
        @Override
        public void onResponse(Call<RelevanceResult> call, Response<RelevanceResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mResList = response.body();
                if ("00".equals(mResList.getCode())) {
                    RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                            .override(60, 40)
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.ic_launcher)
                            .priority(Priority.HIGH);
//                    .transform(new GlideCircleTransform(mContext, 2, mContext.getResources().getColor(R.color.black)));
                    String imageUrl = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1685935631,4222374157&fm=27&gp=0.jpg";
//                HttpUrlClient.ALIYUNPHOTOBASEURL + data.getImgurl();
                    Glide.with(RelevanceUserActivity.this).load(imageUrl).apply(options2).into(ivRelevanceUser);
                } else {
                    ToastUtil.showShort(RelevanceUserActivity.this, mResData.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<RelevanceResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleList() {
        Call<RelevanceResult> call = mApi.getService().createCommitList(token, uid);
        call.enqueue(callbackList);
    }


}
