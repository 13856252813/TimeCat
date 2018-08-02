package com.cary.activity.timecat.manager.client.people;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;
import com.cary.activity.timecat.manager.util.modelbean.ModelBeanData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 添加员工
 */
public class EmployeeAddActivity extends AppCompatActivity {
    private static final String TAG = EmployeeManagerActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.et_employee_mobile)
    EditText etEmployeeMobile;
    @BindView(R.id.et_employee_postion)
    EditText etEmployeePostion;
    @BindView(R.id.et_employee_realname)
    EditText etEmployeeRealname;
    @BindView(R.id.btn_employee_add)
    Button btnEmployeeAdd;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int storeId;
    private int currentpage = 1;
    private int type;

    private EmployeeManagerApi mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_employee_add);
        ButterKnife.bind(this);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
        titleText.setText("添加员工");
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);
        storeId = (int) sharePh.getSharedPreference("storeId", 0);
        mApi = EmployeeManagerApi.getApi();
    }

    @OnClick({R.id.title_back, R.id.btn_employee_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_employee_add:
                String mobile = etEmployeeMobile.getText().toString().trim();
                String position = etEmployeePostion.getText().toString().trim();
                String realname = etEmployeeRealname.getText().toString().trim();
                if (TextUtils.isEmpty(mobile)) {
                    ToastUtil.showShort(this, "请输入手机号码");
                    break;
                } else if (TextUtils.isEmpty(position)) {
                    ToastUtil.showShort(this, "请输入职位");
                    break;
                } else if (TextUtils.isEmpty(realname)) {
                    ToastUtil.showShort(this, "请输入真实姓名");
                    break;
                } else {
                    createSingle(mobile, position, realname);
                }

                break;
        }
    }

    private Callback<ModelBeanData> callbackPage = new Callback<ModelBeanData>() {
        @Override
        public void onResponse(Call<ModelBeanData> call, Response<ModelBeanData> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                ModelBeanData mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    ToastUtil.showShort(EmployeeAddActivity.this, "添加成功");
                    finish();
                } else {
                    Log.e(TAG, "+++" + response.message());
                }
            }
        }

        @Override
        public void onFailure(Call<ModelBeanData> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(String mobile, String position, String realname) {
        Call<ModelBeanData> call = mApi.getService().createCommitId(token, storeId, mobile, position, realname);
        call.enqueue(callbackPage);
    }
}
