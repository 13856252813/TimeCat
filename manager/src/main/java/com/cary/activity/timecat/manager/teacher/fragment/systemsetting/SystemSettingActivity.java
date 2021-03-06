package com.cary.activity.timecat.manager.teacher.fragment.systemsetting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.login.LoginActivity;
import com.cary.activity.timecat.manager.register.ForgetPassActivity;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("Registered")
public class SystemSettingActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.checkbox_push_onoff)
    CheckBox checkboxPushOnoff;
    @BindView(R.id.rl_push_onoff)
    RelativeLayout rlPushOnoff;
    @BindView(R.id.rl_change_loginpass)
    RelativeLayout rlChangeLoginpass;
    @BindView(R.id.rl_version_)
    RelativeLayout rlVersion;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    SharedPreferencesHelper sph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        titleText.setText("系统设置");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

        sph = new SharedPreferencesHelper(this);
        checkboxPushOnoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ToastUtil.showShort(SystemSettingActivity.this, "推送");
                } else {
                    ToastUtil.showShort(SystemSettingActivity.this, "不推送");
                }
            }
        });
    }

    @OnClick({R.id.title_back, R.id.rl_push_onoff, R.id.rl_change_loginpass, R.id.rl_version_, R.id.btn_logout})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.rl_push_onoff:

                break;
            case R.id.rl_change_loginpass:
                intent.setClass(this, ForgetPassActivity.class);
                intent.putExtra("id", "1");
                startActivity(intent);
                break;
            case R.id.rl_version_:
                intent.setClass(this, VersionActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_logout:
                sph.put("token", "");
                sph.put("id", "");
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
