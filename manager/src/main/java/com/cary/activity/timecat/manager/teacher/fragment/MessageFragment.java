package com.cary.activity.timecat.manager.teacher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;
import com.cary.activity.timecat.manager.easeui.ui.MainActivity;
import com.cary.activity.timecat.manager.message.detial.MessageDetialActivity;
import com.cary.activity.timecat.manager.message.list.MessageListApi;
import com.cary.activity.timecat.manager.message.list.MessageListCommitResult;
import com.cary.activity.timecat.manager.message.list.RecyclerViewMessageListAdapter;
import com.cary.activity.timecat.manager.message.myfriend.MyFriendListActivity;
import com.cary.activity.timecat.manager.util.ToastUtil;

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

    private String searchStr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_message_list, container, false);

        unbinder = ButterKnife.bind(this, view);
        //获取热门景点数据
        messageListApi = messageListApi.getApi();
        createSingleMessageList();
        ivMessageListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MyFriendListActivity.class);
                intent.putExtra("myself","12");
                startActivity(intent);
            }
        });
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
                    ToastUtil.showShort(getActivity(),"fragment:"+etStr);
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        StatusBarUtil.setPaddingSmart(getActivity(), view.findViewById(R.id.toolbar));
        ButterKnife.bind(getActivity());
        startActivity(new Intent(getActivity(), MainActivity.class));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
                    //热门景点
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

    private void createSingleMessageList() {
        Call<MessageListCommitResult> call = messageListApi.getService().createCommit();
        call.enqueue(callbackMessage);
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
                Intent intent = new Intent(getActivity(), MessageDetialActivity.class);
                intent.putExtra("message", postion);
                startActivity(intent);
            }

            @Override
            public void onItemClick(int postion) {

            }
        });
    }
}
