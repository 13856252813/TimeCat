package com.cary.activity.timecat.fragment.message.detial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.message.group.MessageGroupMemberActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageDetialActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.recylerView)
    RecyclerView recylerView;
    @BindView(R.id.et_message_detial)
    EditText etMessageDetial;
    @BindView(R.id.tvSend)
    TextView tvSend;
    private ChatAdapter adapter;
    private int messageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_deital);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        titleText.setText("消息详细");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleTextRight.setBackground(getResources().getDrawable(R.mipmap.student3));
        titleTextRight.setText("");
        titleTextRight.setPadding(0, 0, 20, 0);
        titleTextRight.setVisibility(View.VISIBLE);
        messageId = getIntent().getIntExtra("message", 0);

        recylerView = (RecyclerView) findViewById(R.id.recylerView);
        recylerView.setHasFixedSize(true);
        recylerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recylerView.setAdapter(adapter = new ChatAdapter());
        adapter.replaceAll(getTestAdData());

    }

    @OnClick({R.id.title_back, R.id.title_text_right, R.id.tvSend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_text_right:
                Intent intent = new Intent(this, MessageGroupMemberActivity.class);
                intent.putExtra("message", 1);
                startActivity(intent);
                break;
            case R.id.tvSend:
                String SendTextStr = etMessageDetial.getText().toString().trim();
                if (!TextUtils.isEmpty(SendTextStr)) {
                    //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                    EMMessage message = EMMessage.createTxtSendMessage(SendTextStr, String.valueOf(messageId));
//如果是群聊，设置chattype，默认是单聊
//                    if (chatType == CHATTYPE_GROUP)
                        message.setChatType(EMMessage.ChatType.GroupChat);
//发送消息
                    EMClient.getInstance().chatManager().sendMessage(message);
                    ArrayList<ItemModel> data = new ArrayList<>();
                    ChatModel model = new ChatModel();
                    model.setIcon("http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg");
                    model.setContent(SendTextStr);
                    data.add(new ItemModel(ItemModel.CHAT_B, model));
                    adapter.addAll(data);
                    etMessageDetial.setText("");

                    hideKeyBorad(etMessageDetial);
                }
                break;
        }
    }

    private void hideKeyBorad(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    private ArrayList<ItemModel> getTestAdData() {
        ArrayList<ItemModel> models = new ArrayList<>();
        ChatModel model = new ChatModel();
        model.setContent("你好？我们交个朋友吧！");
        model.setIcon("http://img.my.csdn.net/uploads/201508/05/1438760758_3497.jpg");
        models.add(new ItemModel(ItemModel.CHAT_A, model));
        ChatModel model2 = new ChatModel();
        model2.setContent("我是隔壁小王，你是谁？");
        model2.setIcon("http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg");
        models.add(new ItemModel(ItemModel.CHAT_B, model2));

        return models;
    }
}
