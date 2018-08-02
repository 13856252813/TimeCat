package com.cary.activity.timecat.fragment.person.allmessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 此类 是总的消息 其他的没用到
 */
public class AllMessageListActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_sysmessage)
    ImageView ivSysmessage;
    @BindView(R.id.iv_sysmessage_arrow)
    ImageView ivSysmessageArrow;
    @BindView(R.id.tv_sysmssage_num)
    TextView tvSysmssageNum;
    @BindView(R.id.rl_sysmessage)
    RelativeLayout rlSysmessage;
    @BindView(R.id.iv_applyfriend)
    ImageView ivApplyfriend;
    @BindView(R.id.iv_applyfriend_arrow)
    ImageView ivApplyfriendArrow;
    @BindView(R.id.tv_applyfriend_num)
    TextView tvApplyfriendNum;
    @BindView(R.id.rl_applyfriend)
    RelativeLayout rlApplyfriend;
    @BindView(R.id.iv_groupmessage)
    ImageView ivGroupmessage;
    @BindView(R.id.iv_groupmessage_arrow)
    ImageView ivGroupmessageArrow;
    @BindView(R.id.tv_groupmessage_num)
    TextView tvGroupmessageNum;
    @BindView(R.id.rl_groupmessage)
    RelativeLayout rlGroupmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_message_list);
        ButterKnife.bind(this);
        titleText.setText("消息");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

    }

    @OnClick({R.id.title_back, R.id.rl_sysmessage, R.id.rl_applyfriend, R.id.rl_groupmessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.rl_sysmessage:
                ToastUtil.showShort(this, "系统消息");
                break;
            case R.id.rl_applyfriend:
                ToastUtil.showShort(this, "申请好友");
                break;
            case R.id.rl_groupmessage:
                ToastUtil.showShort(this, "群组消息");
                break;
        }
    }
}
