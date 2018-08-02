package com.cary.activity.timecat.manager.teacher.fragment.systemmessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SysMsgDetialActivity extends AppCompatActivity {

    private static final String TAG = SysMsgDetialActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_sysmsg_title)
    TextView tvSysmsgTitle;
    @BindView(R.id.tv_sysmsg_time)
    TextView tvSysmsgTime;
    @BindView(R.id.tv_sysmsg_content)
    TextView tvSysmsgContent;
    private String idStr;

    private SysMsgApi mApi;
    private SysMsgDetialresult mRes;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int id;
    private int uid;
    private String titleStr;
    private String createTimeStr;
    private String contentStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sys_msg_detial);
        ButterKnife.bind(this);

        titleText.setText("系统消息");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        mApi = SysMsgApi.getApi();
        id = getIntent().getIntExtra("id", 0);
        uid = (int) sharePh.getSharedPreference("id", 0);

        if (id != 0) {
            createSingleMsg(id);
        } else {
            ToastUtil.showShort(this, "获取数据失败，消息不正确");
        }

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //    动态咨询 加载数据
    private Callback<SysMsgDetialresult> callbackMsg = new Callback<SysMsgDetialresult>() {
        @Override
        public void onResponse(Call<SysMsgDetialresult> call, Response<SysMsgDetialresult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    if (mRes != null) {
                        titleStr = mRes.getData().getTitle();
                        createTimeStr = mRes.getData().getCreateTime();
                        contentStr = mRes.getData().getContent();
                        if (!TextUtils.isEmpty(titleStr)) {
                            tvSysmsgTitle.setText(titleStr);
                        }
                        if (!TextUtils.isEmpty(createTimeStr)) {
                            tvSysmsgTime.setText(createTimeStr);
                        }
                        if (!TextUtils.isEmpty(contentStr)) {
                            tvSysmsgContent.setText(contentStr);
                        }
                        createSingleMsgRead(id);
                    }
                } else {
                    ToastUtil.showShort(SysMsgDetialActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<SysMsgDetialresult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleMsg(int id) {
        Call<SysMsgDetialresult> call = mApi.getService().createCommitID(token, id);
        call.enqueue(callbackMsg);
    }

    private void createSingleMsgRead(int id) {
        Call<SysMsgDetialresult> call = mApi.getService().createCommitReadID(token, id, uid);
        call.enqueue(callbackMsgRead);
    }
    //    消息已读
    private Callback<SysMsgDetialresult> callbackMsgRead = new Callback<SysMsgDetialresult>() {
        @Override
        public void onResponse(Call<SysMsgDetialresult> call, Response<SysMsgDetialresult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    if (mRes != null) {
                        Log.v(TAG,"消息已读");
                    }
                } else {
                    ToastUtil.showShort(SysMsgDetialActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<SysMsgDetialresult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };
}
