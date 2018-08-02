package com.cary.activity.timecat.fragment.message.group;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.message.myfriend.FriendListApi;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.cary.activity.timecat.util.modelbean.ModelBeanData;
import com.cary.activity.timecat.util.view.MyGridView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private FriendListApi mFriendApi;
    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int id;

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
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        mFriendApi = FriendListApi.getApi();
        sharePh = new SharedPreferencesHelper(this);
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);

        id = getIntent().getIntExtra("groupmodel", 0);

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

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                AlertDialog dialog = builder.create();  //创建对话框
                builder.setTitle("请输入群组名称");    //设置对话框标题
                builder.setIcon(android.R.drawable.btn_star);
                final EditText edit = new EditText(this);
                edit.setHint("请输入群组名称");
                edit.setHintTextColor(getResources().getColor(R.color.gray));
                builder.setView(edit);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String groupName = edit.getText().toString().trim();
                        Toast.makeText(MessageGroupMemberActivity.this, "你输入的是: " + groupName, Toast.LENGTH_SHORT).show();
                        if(!TextUtils.isEmpty(groupName)){
                            createSingleChangeGroup(groupName);
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(MessageGroupMemberActivity.this, "你点了取消", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setCancelable(true);    //设置按钮是否可以按返回键取消,false则不可以取消
                dialog.setCanceledOnTouchOutside(true); //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
                dialog.show();
                break;
            case R.id.btn_group_message_list:
                createSingleDelGroup();
                break;
        }
    }

    /*****************解散群*********************/
    private Callback<ModelBeanData> callbackFriend = new Callback<ModelBeanData>() {
        @Override
        public void onResponse(Call<ModelBeanData> call, Response<ModelBeanData> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                ModelBeanData mModel = response.body();
                ToastUtil.showShort(MessageGroupMemberActivity.this, mModel.getMsg());
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ModelBeanData> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleDelGroup() {
        Call<ModelBeanData> call = mFriendApi.getService().createCommitDelUser(token, uid, id);
        call.enqueue(callbackFriend);
    }

    /*****************修改群名称*********************/
    private Callback<GroupChangeModel> callbackDel = new Callback<GroupChangeModel>() {
        @Override
        public void onResponse(Call<GroupChangeModel> call, Response<GroupChangeModel> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                GroupChangeModel mModel = response.body();
                ToastUtil.showShort(MessageGroupMemberActivity.this, mModel.getMsg());
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<GroupChangeModel> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleChangeGroup(String groupname) {
        Call<GroupChangeModel> call = mFriendApi.getService().createCommitChangeName(token, id, groupname);
        call.enqueue(callbackDel);
    }
}
