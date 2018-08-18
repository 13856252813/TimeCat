package com.cary.activity.timecat.fragment.person.systemsetting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.reglogin.ForgetPassActivity;
import com.cary.activity.timecat.reglogin.LoginActivity;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemSettingActivity extends BaseActivity {

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
        ButterKnife.bind(this);

        titleText.setText("系统设置");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

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

    @Override
    public int getLayout() {
        return R.layout.activity_system_setting;
    }

    @Override
    public boolean setCustomerView() {
        return false;
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
                sph.clear();
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
                EMClient.getInstance().logout(true);
                finish();
                break;
        }
    }
}
