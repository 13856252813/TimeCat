package com.cary.activity.timecat.manager.client.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.http.base.HttpUrlClient;
import com.cary.activity.timecat.manager.register.PerfectInformationActivity;
import com.cary.activity.timecat.manager.teacher.fragment.invitaion.InvitaionCodeActivity;
import com.cary.activity.timecat.manager.teacher.fragment.newhelper.NewHelperActivity;
import com.cary.activity.timecat.manager.teacher.fragment.self.PersonSelfApi;
import com.cary.activity.timecat.manager.teacher.fragment.self.PersonSelfResult;
import com.cary.activity.timecat.manager.teacher.fragment.self.WorkStatusResult;
import com.cary.activity.timecat.manager.teacher.fragment.servicephone.ServicePhoneActivity;
import com.cary.activity.timecat.manager.teacher.fragment.systemmessage.SystemMessageActivity;
import com.cary.activity.timecat.manager.teacher.fragment.systemsetting.SystemSettingActivity;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.ToastUtil;
import com.cary.activity.timecat.manager.util.view.GlideCircleTransform;
import com.cary.activity.timecat.manager.util.view.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cary on 2018/4/8.
 * 管理端 账户
 */

public class AccountFragment extends Fragment {

    private static final String TAG = AccountFragment.class.getSimpleName();
    Unbinder unbinder;
    @BindView(R.id.rl_notify_user)
    RelativeLayout rlNotifyUser;
    @BindView(R.id.riv_user_head)
    RoundImageView rivUserHead;
    @BindView(R.id.iv_user_head)
    ImageView ivUserHead;
    @BindView(R.id.tv_user_nick)
    TextView tvUserNick;
    @BindView(R.id.tv_user_real_name)
    TextView tvUserRealName;
    @BindView(R.id.tv_start_work)
    TextView tvStartWork;
    @BindView(R.id.rl_work_status)
    RelativeLayout rlWorkStatus;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.ll_worknotify)
    LinearLayout llWorknotify;
    @BindView(R.id.ll_telephone)
    LinearLayout llTelephone;
    @BindView(R.id.ll_setting)
    LinearLayout llSetting;

    private SharedPreferencesHelper sharedPreferencesHelper;
    private String token;
    private int uid;
    private PersonSelfResult mRes;
    private PersonSelfApi mApi;

    private boolean workstatus ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        StatusBarUtil.setPaddingSmart(getActivity(), view.findViewById(R.id.toolbar));
        ButterKnife.bind(getActivity());

        sharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
//        if (TextUtils.isEmpty(token)) {
//            startActivity(new Intent(getActivity(), LoginActivity.class));
//        }
        uid = (int) sharedPreferencesHelper.getSharedPreference("id", 0);
        mApi = PersonSelfApi.getApi();
        createSingle(uid);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_notify_user, R.id.riv_user_head, R.id.ll_share, R.id.rl_work_status,
            R.id.ll_worknotify, R.id.ll_telephone, R.id.ll_setting})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_notify_user:
                intent.setClass(getActivity(), SystemMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.riv_user_head:
//                ToastUtil.showShort(getActivity(), "头像");
                intent.setClass(getActivity(), PerfectInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_share:
//                ToastUtil.showShort(getActivity(), "邀请码");
                intent.setClass(getActivity(), InvitaionCodeActivity.class);
                intent.putExtra("myself", mRes.getData().getRequestCode());
                intent.putExtra("invitaionperson", mRes.getData().getPid());
                startActivity(intent);
                break;
            case R.id.rl_work_status:
//                ToastUtil.showShort(getActivity(), "上班");
                createSinglework((!workstatus));
                break;
            case R.id.ll_worknotify:
//                ToastUtil.showShort(getActivity(), "工作须知");
                intent.setClass(getActivity(), NewHelperActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_telephone:
                intent.setClass(getActivity(), ServicePhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_setting:
                intent.setClass(getActivity(), SystemSettingActivity.class);
                startActivity(intent);
                break;
        }
    }

//获取个人数据
    private Callback<PersonSelfResult> callback = new Callback<PersonSelfResult>() {
        @Override
        public void onResponse(Call<PersonSelfResult> call, Response<PersonSelfResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    String imageUrl = HttpUrlClient.ALIYUNPHOTOBASEURL+mRes.getData().getImgurl();
                    RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                            .override(88, 88)
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.avatarw)
                            .priority(Priority.HIGH)
                            .transform(new GlideCircleTransform(getActivity(), 2, getActivity().getResources().getColor(R.color.black)));
                    Glide.with(getActivity()).load(imageUrl).apply(options2).into(ivUserHead);
                    tvUserNick.setText(mRes.getData().getNickname());
                    workstatus = mRes.getData().isWork();
                    if(workstatus){
                        tvStartWork.setText("下班");
                    }else{
                        tvStartWork.setText("上班");
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<PersonSelfResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(int uid) {
        Call<PersonSelfResult> call = mApi.getService().createCommit(token, uid);
        call.enqueue(callback);
    }

    //上班状态
    private Callback<WorkStatusResult> callbackwork = new Callback<WorkStatusResult>() {
        @Override
        public void onResponse(Call<WorkStatusResult> call, Response<WorkStatusResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                WorkStatusResult mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    workstatus = mRes.getData().getWork();
                    if(workstatus){
                        tvStartWork.setText("下班");
                    }else{
                        tvStartWork.setText("上班");
                    }
                } else {
                    ToastUtil.showShort(getActivity(), mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<WorkStatusResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };
    private void createSinglework(boolean workstatus){
        Log.v(TAG,"workstatus:"+workstatus);
        Call<WorkStatusResult> call = mApi.getService().createCommitWorkStatus(token, uid,workstatus);
        call.enqueue(callbackwork);
    }
}
