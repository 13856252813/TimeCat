package com.cary.activity.timecat.manager.client.pushmanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

@SuppressLint("Registered")
public class PushDetialActivity extends AppCompatActivity {

    private static final String TAG = PushDetialActivity.class.getSimpleName();

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

    private PushApi mApi;
    private PushDetialResult mRes;
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
        setContentView(R.layout.activity_sys_msg_detial);
        ButterKnife.bind(this);

        titleText.setText("推送消息");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        mApi = PushApi.getApi();
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

    //  加载数据
    private Callback<PushDetialResult> callback = new Callback<PushDetialResult>() {
        @Override
        public void onResponse(Call<PushDetialResult> call, Response<PushDetialResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    if (mRes != null) {
                        titleStr = mRes.getData().getTitle();
                        createTimeStr = mRes.getData().getPushTime();
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
                    }
                } else {
                    ToastUtil.showShort(PushDetialActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<PushDetialResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleMsg(int id) {
        Call<PushDetialResult> call = mApi.getService().createCommitId(token, id);
        call.enqueue(callback);
    }


}
