package com.cary.activity.timecat.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.message.detial.MessageDetialActivity;
import com.cary.activity.timecat.fragment.message.list.MessageListApi;
import com.cary.activity.timecat.fragment.message.list.MessageListCommitResult;
import com.cary.activity.timecat.fragment.message.list.RecyclerViewMessageListAdapter;
import com.cary.activity.timecat.fragment.message.myfriend.MyFriendListActivity;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;
import com.cary.activity.timecat.reglogin.LoginActivity;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.NetUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cary on 2018/4/8.
 */

public class MessageFragment extends Fragment {
    private static final String TAG = MessageFragment.class.getSimpleName();

    @BindView(R.id.et_message_list_user)
    EditText etMessageListUser;
    @BindView(R.id.iv_message_list_user)
    ImageView ivMessageListUser;
    @BindView(R.id.recyler_message_list)
    RecyclerView recylerMessageList;
    Unbinder unbinder;
    private MessageListApi messageListApi;
    private MessageListCommitResult messageListComRes;
    private List<MessageListCommitResult.Data> mMessageListData;

    private SharedPreferencesHelper mSharedPreferencesHelper;
    private String token;
    private int uid;
    private String searchStr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_message_list, container, false);

        unbinder = ButterKnife.bind(this, view);

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        token = (String) mSharedPreferencesHelper.getSharedPreference("token", "");
        uid = (int) mSharedPreferencesHelper.getSharedPreference("uid", 0);

        //获取热门景点数据
        messageListApi = messageListApi.getApi();
        createSingleMessageList();
        ivMessageListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyFriendListActivity.class);
                startActivity(intent);
            }
        });
        etMessageListUser.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//搜索按键action
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    String content = etMessageListUser.getText().toString().trim();
                    if (TextUtils.isEmpty(content)) {
                        return true;
                    }
                    Log.d(TAG, "开始搜索:" + content);
                    return true;
                }
                return false;
            }
        });

        //注册一个监听连接状态的listener
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        StatusBarUtil.setPaddingSmart(getActivity(), view.findViewById(R.id.toolbar));
        ButterKnife.bind(getActivity());
//        startActivity(new Intent(getActivity(), MainActivity.class));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        messageListCommitResultCall.cancel();
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
                    ToastUtil.showShort(getActivity(), messageListComRes.getMsg());
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


    private Call<MessageListCommitResult> messageListCommitResultCall;
    private void createSingleMessageList() {
        messageListCommitResultCall = messageListApi.getService().createCommit(token, uid);
        messageListCommitResultCall.enqueue(callbackMessage);
    }

    //RecyclerView实现ListView效果，实际就是布局管理器参数改为GridLayoutManager
    private void loadListDate(Boolean inversion, Boolean orientation,
                              final RecyclerView recyclerViewGrid, int layoutId, List<MessageListCommitResult.Data> mHotScenicData) {

//创建适配器adapter对象 参数1.上下文 2.数据加载集合
        RecyclerViewMessageListAdapter recyclerViewGridAdapter = new RecyclerViewMessageListAdapter(getActivity(), mMessageListData, layoutId);
//设置适配器
        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
//布局管理器对象 参数1.上下文 2.规定显示的行数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
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
                if (mMessageListData.size() > 0) {
                    Intent intent = new Intent(getActivity(), MessageDetialActivity.class);
                    Bundle buldle =new Bundle();
                    buldle.putSerializable("groupmodel",mMessageListData.get(postion));
                    intent.putExtras(buldle);
                    startActivity(intent);
                }
            }
        });
    }

    //实现ConnectionListener接口
    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {
            Log.v(TAG, "连接聊天服务器成功");
        }

        @Override
        public void onDisconnected(final int error) {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (error == EMError.USER_REMOVED) {
                        // 显示帐号已经被移除
                        ToastUtil.showShort(getActivity(), "账号移除");
                        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        // 显示帐号在其他设备登录
                        ToastUtil.showShort(getActivity(), "帐号在其他设备登录");
                        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    } else {
                        if (NetUtils.hasNetwork(getActivity())) {
                            //连接不到聊天服务器
                            ToastUtil.showShort(getActivity(), "连接服务器失败，请稍后重试");
                        } else {
                            //当前网络不可用，请检查网络设置
                            ToastUtil.showShort(getActivity(), "当前网络不可用，请检查网络设置");
                        }
                    }
                }
            });
        }
    }
}
