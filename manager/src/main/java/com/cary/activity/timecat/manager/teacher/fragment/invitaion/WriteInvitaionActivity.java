package com.cary.activity.timecat.manager.teacher.fragment.invitaion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 填写邀请码
 */
public class WriteInvitaionActivity extends AppCompatActivity {

    private static final String TAG = WriteInvitaionActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.et_write_invitaion_code)
    EditText etWriteInvitaionCode;
    @BindView(R.id.btn_add_write_invitaion_code)
    Button btnAddWriteInvitaionCode;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private InvitaionApi mApi;
    private InvitaionResult mRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_invitaion);
        ButterKnife.bind(this);
        titleText.setText("填写邀请码");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        mApi = InvitaionApi.getApi();

    }

    @OnClick({R.id.title_back, R.id.title_text_right, R.id.btn_add_write_invitaion_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_add_write_invitaion_code:
                String code = etWriteInvitaionCode.getText().toString().trim();
                if (!TextUtils.isEmpty(code)) {
                    createSingle(code);
                } else {
                    ToastUtil.showShort(this, "请输入邀请码");
                }
                break;
        }
    }

    private Callback<InvitaionResult> callback = new Callback<InvitaionResult>() {
        @Override
        public void onResponse(Call<InvitaionResult> call, Response<InvitaionResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    finish();
                } else {
                    ToastUtil.showShort(WriteInvitaionActivity.this, mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }
        }

        @Override
        public void onFailure(Call<InvitaionResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(String code) {
        Call<InvitaionResult> call = mApi.getService().createCommitCode(token, uid, code);
        call.enqueue(callback);
    }
}
