package com.cary.activity.timecat.manager.client.pushmanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;
import com.cary.activity.timecat.manager.util.modelbean.ModelBeanData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 发送单个消息
 */
@SuppressLint("ValidFragment")
public class PushSignalMsgFragment extends Fragment {
    private static String TAG = PushSignalMsgFragment.class.getSimpleName();
    Unbinder unbinder;
    @BindView(R.id.et_push_mobile)
    EditText etPushMobile;
    @BindView(R.id.et_push_title)
    EditText etPushTitle;
    @BindView(R.id.et_push_content)
    EditText etPushContent;
    @BindView(R.id.btn_pushmessage_confirm)
    Button btnPushmessageConfirm;

    private PushAdapter mAdapter;
    private SharedPreferencesHelper sharePH;

    private PushApi mApi;
    private String token;
    private int uid;
    private int storeId;
    private ModelBeanData mRes;
    private int currentpage = 1;
    private int type = 1;

    @SuppressLint("ValidFragment")
    public PushSignalMsgFragment(int type) {
        // Required empty public constructor
        this.type = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_push_all_msg_comit, container, false);
        unbinder = ButterKnife.bind(this, view);
        etPushMobile.setVisibility(View.VISIBLE);

        mApi = PushApi.getApi();
        sharePH = new SharedPreferencesHelper(getActivity());
        token = (String) sharePH.getSharedPreference("token", "");
        uid = (int) sharePH.getSharedPreference("id", 0);
        storeId = (int) sharePH.getSharedPreference("storeId", 0);

        btnPushmessageConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etPushTitle.getText().toString().trim();
                String content = etPushContent.getText().toString().trim();
                String mobile = etPushMobile.getText().toString().trim();
                if (TextUtils.isEmpty(title)) {
                    ToastUtil.showShort(getActivity(), "请输入推送标题");
                    return;
                } else if (TextUtils.isEmpty(content)) {
                    ToastUtil.showShort(getActivity(), "请输入推送内容");
                    return;
                } else if (TextUtils.isEmpty(mobile)) {
                    ToastUtil.showShort(getActivity(), "请输入推送用户手机号");
                    return;
                } else {
                    //个人推送
                    createSinglePush(title, content, mobile);
                }
            }
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //加载数据
    private Callback<ModelBeanData> callback = new Callback<ModelBeanData>() {
        @Override
        public void onResponse(Call<ModelBeanData> call, Response<ModelBeanData> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    ToastUtil.showShort(getActivity(), mRes.getMsg());
                    getActivity().finish();
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ModelBeanData> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };


    private void createSinglePush(String title, String content, String mobile) {
        Call<ModelBeanData> call = mApi.getService().createCommitPushMsg(token, uid, storeId, title, content, mobile);
        call.enqueue(callback);
    }
}
