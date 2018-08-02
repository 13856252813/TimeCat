package com.cary.activity.timecat.fragment.message.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.message.detial.MessageDetialActivity;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageSearchNameActivity extends AppCompatActivity {
    private static final String TAG = MessageSearchNameActivity.class.getSimpleName();

    @BindView(R.id.iv_message_list_back)
    ImageView ivMessageListBack;
    @BindView(R.id.et_message_list_user)
    EditText etMessageListUser;
    @BindView(R.id.recyler_message_list)
    RecyclerView recylerMessageList;

    private MessageListApi messageListApi;
    private MessageListCommitResult messageListComRes;
    private List<MessageListCommitResult.Data> mMessageListData;
    private int message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list_search);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        message = getIntent().getIntExtra("message", 0);
        ivMessageListBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ToastUtil.showShort(this, "message:" + message);
        messageListApi = messageListApi.getApi();
        createSingleMessageList();
        etMessageListUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String etStr = s.toString().trim();
                if(!TextUtils.isEmpty(etStr)){
                    ToastUtil.showShort(MessageSearchNameActivity.this,"SearchActivity:"+etStr);
                }
            }
        });
    }

    private Callback<MessageListCommitResult> callbackMessage = new Callback<MessageListCommitResult>() {
        @Override
        public void onResponse(Call<MessageListCommitResult> call, Response<MessageListCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                messageListComRes = response.body();
                if ("00".equals(messageListComRes.getCode())) {
                    mMessageListData = messageListComRes.getData();
                    loadListDate(false, true, recylerMessageList, R.layout.activity_message_list_item, mMessageListData);
                    recylerMessageList.setNestedScrollingEnabled(false);
                } else {
                    ToastUtil.showShort(MessageSearchNameActivity.this, messageListComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<MessageListCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingleMessageList() {
        Call<MessageListCommitResult> call = messageListApi.getService().createCommit("",0);
        call.enqueue(callbackMessage);
    }

    //RecyclerView实现ListView效果，实际就是布局管理器参数改为GridLayoutManager
    private void loadListDate(Boolean inversion, Boolean orientation,
                              final RecyclerView recyclerViewGrid, int layoutId, List<MessageListCommitResult.Data> mHotScenicData) {

//创建适配器adapter对象 参数1.上下文 2.数据加载集合
        RecyclerViewMessageListAdapter recyclerViewGridAdapter = new RecyclerViewMessageListAdapter(this, mMessageListData, layoutId);
//设置适配器
        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
//布局管理器对象 参数1.上下文 2.规定显示的行数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
//通过布局管理器可以控制条目排列的顺序 true反向显示 false正常显示(默认)
        gridLayoutManager.setReverseLayout(inversion);
//设置RecycleView显示的方向是水平还是垂直
//GridLayout.HORIZONTAL水平 GridLayout.VERTICAL默认垂直
// 三元运算符
        gridLayoutManager.setOrientation(orientation ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
//设置布局管理器， 参数linearLayoutManager对象
        recyclerViewGrid.setLayoutManager(gridLayoutManager);
        recyclerViewGridAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(MessageSearchNameActivity.this, MessageDetialActivity.class);
                intent.putExtra("message", postion);
                startActivity(intent);
            }
        });
    }
}
