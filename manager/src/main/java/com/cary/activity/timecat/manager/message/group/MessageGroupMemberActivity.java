package com.cary.activity.timecat.manager.message.group;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.ToastUtil;
import com.cary.activity.timecat.manager.util.view.MyGridView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 群成员界面
 */
public class MessageGroupMemberActivity extends AppCompatActivity {
    private static final String TAG = MessageGroupMemberActivity.class.getSimpleName();
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_group_message_list_name_text)
    TextView tvGroupMessageListNameText;
    @BindView(R.id.tv_group_message_list_name)
    TextView tvGroupMessageListName;
    @BindView(R.id.rl_group_message_list_name)
    RelativeLayout rlGroupMessageListName;
    @BindView(R.id.temp_group_members)
    MyGridView tempGroupMembers;
    @BindView(R.id.btn_group_message_list)
    Button btnGroupMessageList;
    @BindView(R.id.rl_btn_group_message_list)
    LinearLayout rlBtnGroupMessageList;

    private ArrayList<GroupMemberItem> mGridData;
    private GroupMemberGridViewAdapter mGroupAdapter;

    private int postion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_message_list);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        titleText.setText("群消息");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));

        postion = getIntent().getIntExtra("message", 0);
        ToastUtil.showShort(this, "position:" + postion);

        setDataList();
        mGroupAdapter = new GroupMemberGridViewAdapter(this,
                R.layout.activity_group_message_list_item, mGridData);
        tempGroupMembers.setAdapter(mGroupAdapter);


    }

    private void setDataList() {
        mGridData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GroupMemberItem gmi = new GroupMemberItem();
            gmi.setImage("");
            gmi.setTitle("title" + i);
            mGridData.add(gmi);
        }

    }

    @OnClick({R.id.title_back, R.id.rl_group_message_list_name, R.id.btn_group_message_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.rl_group_message_list_name:

                break;
            case R.id.btn_group_message_list:
                ToastUtil.showShort(this, "解散群");
                break;
        }
    }
}
