package com.cary.activity.timecat.manager.teacher.fragment.crashpledge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 退押金
 */
public class ReturnCrashPledgeActivity extends AppCompatActivity {

    private static final String TAG = ReturnCrashPledgeActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.et_return_crash_pledge)
    EditText etReturnCrashPledge;
    @BindView(R.id.tv_all_return_crash_pledge)
    TextView tvAllReturnCrashPledge;
    @BindView(R.id.tv_return_crash_pledge_account_method)
    TextView tvReturnCrashPledgeAccountMethod;
    @BindView(R.id.ll_return_crash_pledge_account_method)
    LinearLayout llReturnCrashPledgeAccountMethod;
    @BindView(R.id.tv_return_crash_pledge_account_name)
    TextView tvReturnCrashPledgeAccountName;
    @BindView(R.id.tv_return_crash_pledge_account)
    TextView tvReturnCrashPledgeAccount;
    @BindView(R.id.ll_return_crash_pledge_account)
    LinearLayout llReturnCrashPledgeAccount;
    @BindView(R.id.btn_return_crash_pledge_commit)
    Button btnReturnCrashPledgeCommit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_crash_pledge);
        ButterKnife.bind(this);

        titleText.setText("退还押金");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

    }

    @OnClick({R.id.title_back, R.id.tv_all_return_crash_pledge, R.id.btn_return_crash_pledge_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.tv_all_return_crash_pledge:
                break;
            case R.id.btn_return_crash_pledge_commit:
                break;
        }
    }
}
