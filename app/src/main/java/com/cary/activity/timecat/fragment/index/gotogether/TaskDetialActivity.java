package com.cary.activity.timecat.fragment.index.gotogether;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.modelbean.ModelBeanData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 发布订单任务详情
 */

public class TaskDetialActivity extends BaseActivity {

    private static final String TAG = TaskDetialActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_task_detial_userhead)
    ImageView ivTaskDetialUserhead;
    @BindView(R.id.tv_task_detial_username)
    TextView tvTaskDetialUsername;
    @BindView(R.id.tv_task_detial_create_time)
    TextView tvTaskDetialCreateTime;
    @BindView(R.id.iv_task_detial_adderesscity)
    ImageView ivTaskDetialAdderesscity;
    @BindView(R.id.tv_task_detial_adderesscity)
    TextView tvTaskDetialAdderesscity;
    @BindView(R.id.iv_task_detial_money)
    ImageView ivTaskDetialMoney;
    @BindView(R.id.tv_task_detial_money)
    TextView tvTaskDetialMoney;
    @BindView(R.id.iv_take_photo)
    ImageView ivTakePhoto;
    @BindView(R.id.tv_task_detial_user_head_line)
    TextView tvTaskDetialUserHeadLine;
    @BindView(R.id.tv_task_detial_desction)
    TextView tvTaskDetialDesction;
    @BindView(R.id.tv_task_detial_grab_line)
    TextView tvTaskDetialGrabLine;
    @BindView(R.id.btn_task_detial_confirm_teacher)
    Button btnTaskDetialConfirmTeacher;
    @BindView(R.id.recycler_task_detial_select)
    RecyclerView recyclerTaskDetialSelect;
    private GrabTeacherAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;

    private SharedPreferencesHelper sharePH;
    private TaskApi taskApi;
    private String token;
    private int uid;//用户id
    private int id;
    private TaskDetialResult taskRes;
    private TaskDetialResult.Grabs taskGrabs;//抢单
    private int grabsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_task_detial);
        ButterKnife.bind(this);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        titleText.setText("任务详情");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        taskApi = TaskApi.getApi();
        sharePH = new SharedPreferencesHelper(this);
        token = (String) sharePH.getSharedPreference("token", "");
        uid = (int) sharePH.getSharedPreference("id", 0);
        id = getIntent().getIntExtra("id", 0);

        //在加载数据之前配置
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerTaskDetialSelect.setLayoutManager(linearLayoutManager);
        recyclerTaskDetialSelect.addItemDecoration(new DividerItemDecoration(this, 1));
        //创建一个适配器
        mAdapter = new GrabTeacherAdapter(this);
        recyclerTaskDetialSelect.setAdapter(mAdapter);
        mAdapter.setOnCheckListener(new GrabTeacherAdapter.OnCheckListener() {
            @Override
            public void onCheckListener(int grabsId) {
                TaskDetialActivity.this.grabsId = grabsId;
            }
        });
        createSingle();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_task_detial;
    }

    @OnClick({R.id.title_back, R.id.title_text_right, R.id.iv_task_detial_userhead, R.id.iv_take_photo, R.id.btn_task_detial_confirm_teacher})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_text_right:
                finish();
                break;
            case R.id.iv_task_detial_userhead:

                break;
            case R.id.iv_take_photo:

                break;
            case R.id.btn_task_detial_confirm_teacher:
//                ToastUtil.showShort(this, "确认选择");
                createSingleGrabs(taskRes.getData().getId(), grabsId);
                break;
        }
    }

    /****************任务详情*****************/
    private Callback<TaskDetialResult> callbackTask = new Callback<TaskDetialResult>() {
        @Override
        public void onResponse(Call<TaskDetialResult> call, Response<TaskDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                taskRes = response.body();
                if ("00".equals(taskRes.getCode())) {
                    String type = taskRes.getData().getType();
                    if ("gp".equals(type)) {
                        //跟拍
                        ivTakePhoto.setImageDrawable(getResources().getDrawable(R.mipmap.photographer));
                    } else if ("sx".equals(type)) {
                        //摄像
                        ivTakePhoto.setImageDrawable(getResources().getDrawable(R.mipmap.mainicon7));
                    } else if ("gz".equals(type)) {
                        //化妆
                        ivTakePhoto.setImageDrawable(getResources().getDrawable(R.mipmap.dresser));
                    }
                    tvTaskDetialCreateTime.setText(taskRes.getData().getCreateTime());
                    tvTaskDetialAdderesscity.setText(taskRes.getData().getServiceCity());
                    tvTaskDetialMoney.setText(taskRes.getData().getAmount() + "");
                    tvTaskDetialDesction.setText(taskRes.getData().getContent());
                    boolean isSelect = false;//是否有选中的老师
                    for (int i = 0; i < taskRes.getData().getGrabs().size(); i++) {
                        TaskDetialResult.Grabs grabs = taskRes.getData().getGrabs().get(i);
                        if (grabs.getSelected()) {
                            isSelect = true;//有选中的老师
                        }
                    }
                    if (isSelect) {
                        btnTaskDetialConfirmTeacher.setVisibility(View.GONE);
                    } else if (taskRes.getData().getGrabs().size() > 0) {
                        btnTaskDetialConfirmTeacher.setVisibility(View.VISIBLE);
                    }
                    mAdapter.reSetData(taskRes.getData().getGrabs());
                    mAdapter.setisSelect(isSelect);
                } else {
                    ToastUtil.showShort(TaskDetialActivity.this, taskRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<TaskDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<TaskDetialResult> call = taskApi.getService().createCommitId(token, id);
        call.enqueue(callbackTask);
    }

    /***************选择抢单******************/
    private Callback<ModelBeanData> callbackgabs = new Callback<ModelBeanData>() {
        @Override
        public void onResponse(Call<ModelBeanData> call, Response<ModelBeanData> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                ModelBeanData taskRes = response.body();
                if ("00".equals(taskRes.getCode())) {
                    btnTaskDetialConfirmTeacher.setVisibility(View.GONE);
                    mAdapter.setisSelect(true);
                    mAdapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showShort(TaskDetialActivity.this, taskRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ModelBeanData> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleGrabs(int taskId, int Grabsid) {
        Call<ModelBeanData> call = taskApi.getService().createCommitGrabsId(token, taskId, Grabsid);
        call.enqueue(callbackgabs);
    }
}
