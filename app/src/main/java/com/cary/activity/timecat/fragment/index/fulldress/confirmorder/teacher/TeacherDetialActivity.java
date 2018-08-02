package com.cary.activity.timecat.fragment.index.fulldress.confirmorder.teacher;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.TeacherApi;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.view.GlideCircleTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherDetialActivity extends BaseActivity {

    private static final String TAG = TeacherDetialActivity.class.getSimpleName();

    @BindView(R.id.iv_teacher_detial_back)
    ImageView ivTeacherDetialBack;
    @BindView(R.id.iv_teacher_detial_head)
    ImageView ivTeacherDetialHead;
    @BindView(R.id.tv_teacher_detial_name)
    TextView tvTeacherDetialName;
    @BindView(R.id.tv_teacher_detial_store)
    TextView tvTeacherDetialStore;
    @BindView(R.id.tv_teacher_detial_look_date)
    TextView tvTeacherDetialLookDate;
    @BindView(R.id.tv_teacher_detial_collect)
    TextView tvTeacherDetialCollect;
    @BindView(R.id.ll_teacher_detial_name_store)
    RelativeLayout llTeacherDetialNameStore;
    @BindView(R.id.tv_teacher_detial_assign_text)
    TextView tvTeacherDetialAssignText;
    @BindView(R.id.tv_teacher_detial_assign)
    TextView tvTeacherDetialAssign;
    @BindView(R.id.tv_teacher_detial_assign_text_two)
    TextView tvTeacherDetialAssignTextTwo;
    @BindView(R.id.tv_teacher_detial_qualificationsn_text)
    TextView tvTeacherDetialQualificationsnText;
    @BindView(R.id.tv_teacher_detial_qualifications)
    TextView tvTeacherDetialQualifications;
    @BindView(R.id.tv_teacher_detial_qualifications_text_two)
    TextView tvTeacherDetialQualificationsTextTwo;
    @BindView(R.id.tv_teacher_detial_timecredit)
    TextView tvTeacherDetialTimecredit;
    @BindView(R.id.tv_teacher_detial_timecredit_text)
    TextView tvTeacherDetialTimecreditText;
    @BindView(R.id.ll_teacher_detial_timecredit)
    LinearLayout llTeacherDetialTimecredit;
    @BindView(R.id.iv_teacher_detial_leave_message_more)
    ImageView ivTeacherDetialLeaveMessageMore;
    @BindView(R.id.rl_teacher_detial_leave_message)
    RelativeLayout rlTeacherDetialLeaveMessage;
//    @BindView(R.id.iv_teacher_detial_leave_message_user_head)
//    ImageView ivTeacherDetialLeaveMessageUserHead;
//    @BindView(R.id.tv_teacher_detial_leave_message_user_name)
//    TextView tvTeacherDetialLeaveMessageUserName;
//    @BindView(R.id.tv_teacher_detial_leave_message_user_time)
//    TextView tvTeacherDetialLeaveMessageUserTime;
//    @BindView(R.id.teacher_detial_leave_message_user_time_ratingbar)
//    RatingBar teacherDetialLeaveMessageUserTimeRatingbar;
//    @BindView(R.id.recycler_teacher_detial_user_img)
//    RecyclerView recyclerTeacherDetialUserImg;

    @BindView(R.id.iv_teacher_detial_works_apprection_more)
    ImageView ivTeacherDetialWorksApprectionMore;
    @BindView(R.id.rl_teacher_detial_works_apprection)
    RelativeLayout rlTeacherDetialWorksApprection;
    @BindView(R.id.recycler_teacher_detial_woroks_apprection)
    RecyclerView recyclerTeacherDetialWoroksApprection;
    @BindView(R.id.btn_teacher_detial_online_reservation)
    Button btnTeacherDetialOnlineReservation;
    @BindView(R.id.tv_teacher_detial_infomation)
    TextView tvTeacherDetialInfomation;
    @BindView(R.id.recycler_teacher_detial_comment)
    RecyclerView recyclerTeacherDetialComment;

    private int id;
    Drawable drawable1, drawable2;
    int flag = 0;

    private TeacherApi teahApi;
    private TeacherDetialResult mteahRes;

    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int storeId;
    private int currentpage = 1;
    private String teacherType = "";
    //留言评论
    private TeacherDetialCommentAdapter mCommentAdapter;
    private TeacherCommentResult mCommentRes;
    private List<TeacherCommentResult.Data> mCommentList;
    //老师作品
    private TeacherWorkResult mWorkRes;
    private List<TeacherWorkResult.Data> mWorkList;
    private TeacherWorkAppcitionAdapter mWorkAppcitionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_teacher_detial);
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

        id = getIntent().getIntExtra("id", 0);

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);
        storeId = (int) sharePh.getSharedPreference("storeId", 0);

        teahApi = TeacherApi.getApi();

        drawable2 = getResources().getDrawable(R.mipmap.heart4);// 找到资源图片
// 这一步必须要做，否则不会显示。
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());// 设置图片宽高
        drawable1 = getResources().getDrawable(R.mipmap.heart3);// 找到资源图片
// 这一步必须要做，否则不会显示。
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());// 设置图片宽高

        /*********时光留言 用户的评价作品 留言 *********/
        LinearLayoutManager linearLayoutManagerComment = new LinearLayoutManager(this);
        linearLayoutManagerComment.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerTeacherDetialComment.setLayoutManager(linearLayoutManagerComment);
        mCommentAdapter = new TeacherDetialCommentAdapter(this);
        recyclerTeacherDetialComment.addItemDecoration(new SpaceItemDecoration(20));
        recyclerTeacherDetialComment.setAdapter(mCommentAdapter);
        recyclerTeacherDetialComment.setNestedScrollingEnabled(false);

        //作品欣赏
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerTeacherDetialWoroksApprection.setLayoutManager(linearLayoutManager);
//        mWorkAppcitionAdapter = new TeacherWorkAppcitionAdapter(this);
//        //设置item间距，30dp
//        recyclerTeacherDetialWoroksApprection.addItemDecoration(new SpaceItemDecoration(20));
//        recyclerTeacherDetialWoroksApprection.setAdapter(mWorkAppcitionAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        //通过布局管理器可以控制条目排列的顺序 true反向显示 false正常显示(默认)
        gridLayoutManager.setReverseLayout(false);
        //设置RecycleView显示的方向是水平还是垂直
        //GridLayout.HORIZONTAL水平 GridLayout.VERTICAL默认垂直
        // 三元运算符
        gridLayoutManager.setOrientation(false ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
        //设置布局管理器， 参数linearLayoutManager对象
        recyclerTeacherDetialWoroksApprection.setLayoutManager(gridLayoutManager);
        //添加Android自带的分割线
        recyclerTeacherDetialWoroksApprection.addItemDecoration(new SpaceItemDecoration(5));
        mWorkAppcitionAdapter = new TeacherWorkAppcitionAdapter(this);
        recyclerTeacherDetialWoroksApprection.setAdapter(mWorkAppcitionAdapter);
        mWorkAppcitionAdapter.setItemClickListener(new TeacherWorkAppcitionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳转到详情
//                Intent intent = new Intent(TeacherDetialActivity.this, TeacherWorkGridActivity.class);
//                intent.putExtra("id", mWorkList.get(position).getId());
//                startActivity(intent);
            }
        });

        //获取详情数据
        createSingleDetial();
        //获取留言评论
        createSingleComment();
        //获取老师作品
        createSinglework();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_teacher_detial;
    }

    @OnClick({R.id.iv_teacher_detial_back, R.id.tv_teacher_detial_collect, R.id.tv_teacher_detial_look_date,
            R.id.rl_teacher_detial_leave_message, R.id.rl_teacher_detial_works_apprection,
            R.id.btn_teacher_detial_online_reservation})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_teacher_detial_back:
                finish();
                break;
            case R.id.tv_teacher_detial_look_date:
//                ToastUtil.showShort(this, "查看排期");
                intent.setClass(this, TeacherDateActivity.class);
                intent.putExtra("id", "234");
                startActivity(intent);
                break;
            case R.id.tv_teacher_detial_collect:
                if (flag == 0) {
                    tvTeacherDetialCollect.setCompoundDrawables(null, drawable1, null, null);// 设置到控件中
                    flag = 1;
                } else if (flag == 1) {
                    tvTeacherDetialCollect.setCompoundDrawables(null, drawable2, null, null);// 设置到控件中
                    flag = 0;
                }
                break;
            case R.id.rl_teacher_detial_leave_message:
                ToastUtil.showShort(this, "最新留言");
                break;
            case R.id.rl_teacher_detial_works_apprection:
//                ToastUtil.showShort(this, "作品欣赏");
                intent.setClass(this, TeacherWorkGridActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.btn_teacher_detial_online_reservation:
                ToastUtil.showShort(this, "在线预订");
                break;
        }
    }

    private Callback<TeacherDetialResult> callbackDetial = new Callback<TeacherDetialResult>() {
        @Override
        public void onResponse(Call<TeacherDetialResult> call, Response<TeacherDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mteahRes = response.body();
                if ("00".equals(mteahRes.getCode())) {
                    StringBuilder text = new StringBuilder();
                    text.append(mteahRes.getData().getWebUser().getNickname()+"  ");
                    if (!TextUtils.isEmpty(mteahRes.getData().getWebUser().getTeacherType())) {
                        text.append("|" + mteahRes.getData().getWebUser().getTeacherType());
                    }
                    if (!TextUtils.isEmpty(mteahRes.getData().getWebUser().getPosition())) {
                        text.append("|" + mteahRes.getData().getWebUser().getPosition());
                    }
                    if (!TextUtils.isEmpty(mteahRes.getData().getWebUser().getType())) {
                        text.append("|" + mteahRes.getData().getWebUser().getPosition());
                    }
                    tvTeacherDetialName.setText(text);

                    tvTeacherDetialStore.setText(mteahRes.getData().getStoreId() + "根据ID需要自己查吗");
                    tvTeacherDetialAssign.setText("¥ " + mteahRes.getData().getWebUser().getPhotoAmount());
                    tvTeacherDetialQualifications.setText("¥ " + mteahRes.getData().getWebUser().getExtraAmount());
                    tvTeacherDetialTimecredit.setText("" + mteahRes.getData().getWebUser().getCredit());
                    tvTeacherDetialInfomation.setText(mteahRes.getData().getSelfIntroduction());

                    RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                            .override(90, 80)
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.ic_launcher)
                            .priority(Priority.HIGH)
                    .transform(new GlideCircleTransform(TeacherDetialActivity.this, 2, TeacherDetialActivity.this.getResources().getColor(R.color.black)));
                    String imageUrl =  HttpUrlClient.ALIYUNPHOTOBASEURL + mteahRes.getData().getWebUser().getImgurl();
                    Glide.with(TeacherDetialActivity.this).load(imageUrl).apply(options2).into(ivTeacherDetialHead);

                } else {
                    ToastUtil.showShort(TeacherDetialActivity.this, mteahRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<TeacherDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleDetial() {
        Call<TeacherDetialResult> call = teahApi.getService().createCommitId(token, id);
        call.enqueue(callbackDetial);
    }

    /********时光留言-最新评价*********/
    private Callback<TeacherCommentResult> callbackComment = new Callback<TeacherCommentResult>() {
        @Override
        public void onResponse(Call<TeacherCommentResult> call, Response<TeacherCommentResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mCommentRes = response.body();
                if ("00".equals(mCommentRes.getCode())) {
                    mCommentList = mCommentRes.getData();
                    mCommentAdapter.setDatas(mCommentList);
                } else {
                    ToastUtil.showShort(TeacherDetialActivity.this, mCommentRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<TeacherCommentResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleComment() {
        Call<TeacherCommentResult> call = teahApi.getService().createCommitComment(token, id);
        call.enqueue(callbackComment);
    }
    /***************** 老师作品 *********************/
    private Callback<TeacherWorkResult> callbackrework = new Callback<TeacherWorkResult>() {
        @Override
        public void onResponse(Call<TeacherWorkResult> call, Response<TeacherWorkResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mWorkRes = response.body();
                if ("00".equals(mWorkRes.getCode())) {
                    mWorkList = mWorkRes.getData();
                    mWorkAppcitionAdapter.reSetData(mWorkList);
//                    mAdapter.reSetData(mLists);
                } else {
                    ToastUtil.showShort(TeacherDetialActivity.this, mWorkRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<TeacherWorkResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSinglework() {
        Call<TeacherWorkResult> call = teahApi.getService().createCommitTeachWork(token, id);
        call.enqueue(callbackrework);
    }
}
