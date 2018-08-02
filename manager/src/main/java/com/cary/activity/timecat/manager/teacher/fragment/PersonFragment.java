package com.cary.activity.timecat.manager.teacher.fragment;

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
import com.cary.activity.timecat.manager.teacher.fragment.attention.MyAttentionActivity;
import com.cary.activity.timecat.manager.teacher.fragment.balance.BalanceActivity;
import com.cary.activity.timecat.manager.teacher.fragment.crashpledge.CashPledgeActivity;
import com.cary.activity.timecat.manager.teacher.fragment.integral.TimeCreditActivity;
import com.cary.activity.timecat.manager.teacher.fragment.invitaion.InvitaionCodeActivity;
import com.cary.activity.timecat.manager.teacher.fragment.newhelper.NewHelperActivity;
import com.cary.activity.timecat.manager.teacher.fragment.self.MyInfomationActivity;
import com.cary.activity.timecat.manager.teacher.fragment.self.PersonSelfApi;
import com.cary.activity.timecat.manager.teacher.fragment.self.PersonSelfResult;
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
 * 老师端个人中心
 */

public class PersonFragment extends Fragment {

    private static final String TAG = PersonFragment.class.getSimpleName();
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
    @BindView(R.id.tv_user_store)
    TextView tvUserStore;
    @BindView(R.id.tv_user_banlance)
    TextView tvUserBanlance;
    @BindView(R.id.ll_user_balance)
    LinearLayout llUserBalance;
    @BindView(R.id.tv_user_everylook)
    TextView tvUserEverylook;
    @BindView(R.id.ll_user_everylook)
    LinearLayout llUserEverylook;
    @BindView(R.id.tv_user_extralook)
    TextView tvUserExtralook;
    @BindView(R.id.ll_user_extralook)
    LinearLayout llUserExtralook;
    @BindView(R.id.iv_user_charge_setting)
    ImageView ivUserChargeSetting;
    @BindView(R.id.tv_user_charge_setting)
    TextView tvUserChargeSetting;
    @BindView(R.id.rl_user_charge_setting)
    RelativeLayout rlUserChargeSetting;
    @BindView(R.id.ll_user_charge_setting)
    LinearLayout llUserChargeSetting;
    @BindView(R.id.iv_user_mycasheldge)
    ImageView ivUserMycasheldge;
    @BindView(R.id.tv_user_mycasheldge)
    TextView tvUserMycasheldge;
    @BindView(R.id.rl_user_mycasheldge)
    RelativeLayout rlUserMycasheldge;
    @BindView(R.id.ll_user_mycasheldge)
    LinearLayout llUserMycasheldge;
    @BindView(R.id.iv_user_attention)
    ImageView ivUserAttention;
    @BindView(R.id.tv_user_myself)
    TextView tvUserMyself;
    @BindView(R.id.rl_user_myself)
    RelativeLayout rlUserMyself;
    @BindView(R.id.ll_user_myself)
    LinearLayout llUserMyself;
    @BindView(R.id.iv_user_moneyname)
    ImageView ivUserMoneyname;
    @BindView(R.id.tv_user_mycollect)
    TextView tvUserMycollect;
    @BindView(R.id.rl_user_mycollect)
    RelativeLayout rlUserMycollect;
    @BindView(R.id.ll_user_mycollect)
    LinearLayout llUserMycollect;
    @BindView(R.id.iv_user_worknotify)
    ImageView ivUserWorknotify;
    @BindView(R.id.tv_user_worknotify)
    TextView tvUserWorknotify;
    @BindView(R.id.rl_user_worknotify)
    RelativeLayout rlUserWorknotify;
    @BindView(R.id.ll_user_worknotify)
    LinearLayout llUserWorknotify;
    @BindView(R.id.iv_user_servicephone)
    ImageView ivUserServicephone;
    @BindView(R.id.tv_user_servicephone)
    TextView tvUserServicephone;
    @BindView(R.id.rl_user_servicephone)
    RelativeLayout rlUserServicephone;
    @BindView(R.id.ll_user_servicephone)
    LinearLayout llUserServicephone;
    @BindView(R.id.iv_user_setting)
    ImageView ivUserSetting;
    @BindView(R.id.tv_user_setting)
    TextView tvUserSetting;
    @BindView(R.id.rl_user_setting)
    RelativeLayout rlUserSetting;
    @BindView(R.id.ll_user_setting)
    LinearLayout llUserSetting;

    private SharedPreferencesHelper sharedPreferencesHelper;
    private String token;
    private int uid;
    private PersonSelfResult mRes;
    private PersonSelfApi mApi;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_person, container, false);
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

    @OnClick({R.id.rl_notify_user, R.id.riv_user_head, R.id.ll_user_balance, R.id.ll_user_everylook, R.id.ll_user_extralook, R.id.ll_user_charge_setting, R.id.ll_user_mycasheldge, R.id.ll_user_myself, R.id.ll_user_mycollect, R.id.ll_user_worknotify, R.id.ll_user_servicephone, R.id.ll_user_setting})
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
            case R.id.ll_user_balance:
//                ToastUtil.showShort(getActivity(), "余额");
                intent.setClass(getActivity(), BalanceActivity.class);
                intent.putExtra("balance", mRes.getData().getAmount());
                startActivity(intent);
                break;
            case R.id.ll_user_everylook:
//                ToastUtil.showShort(getActivity(), "时光信用");
                intent.setClass(getActivity(), TimeCreditActivity.class);
                intent.putExtra("integral", mRes.getData().getScore());
                startActivity(intent);
                break;
            case R.id.ll_user_extralook:
//                ToastUtil.showShort(getActivity(), "邀请码");
                intent.setClass(getActivity(), InvitaionCodeActivity.class);
                intent.putExtra("myself", mRes.getData().getRequestCode());
                intent.putExtra("invitaionperson", mRes.getData().getPid());
                startActivity(intent);
                break;
            case R.id.ll_user_charge_setting:
                ToastUtil.showShort(getActivity(), "收费设置");
                break;
            case R.id.ll_user_mycasheldge:
                ToastUtil.showShort(getActivity(), "我的押金");
                intent.setClass(getActivity(), CashPledgeActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_user_myself:
//                ToastUtil.showShort(getActivity(), "我的资料");
                intent.setClass(getActivity(), MyInfomationActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_user_mycollect:
//                ToastUtil.showShort(getActivity(), "我的关注");
                intent.setClass(getActivity(), MyAttentionActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_user_worknotify:
//                ToastUtil.showShort(getActivity(), "工作须知");
                intent.setClass(getActivity(), NewHelperActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_user_servicephone:
                intent.setClass(getActivity(), ServicePhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_user_setting:
                intent.setClass(getActivity(), SystemSettingActivity.class);
                startActivity(intent);
                break;
        }
    }

    private Callback<PersonSelfResult> callback = new Callback<PersonSelfResult>() {
        @Override
        public void onResponse(Call<PersonSelfResult> call, Response<PersonSelfResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    String imageUrl =  HttpUrlClient.ALIYUNPHOTOBASEURL+mRes.getData().getImgurl();
                    RequestOptions options2 = new RequestOptions()
//                    .centerCrop()
                            .override(88, 88)
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.avatarw)
                            .priority(Priority.HIGH)
                            .transform(new GlideCircleTransform(getActivity(), 2, getActivity().getResources().getColor(R.color.black)));
                    Glide.with(getActivity()).load(imageUrl).apply(options2).into(ivUserHead);
                    tvUserNick.setText(mRes.getData().getNickname());
                    tvUserBanlance.setText(mRes.getData().getAmount() + "");
                    tvUserEverylook.setText(mRes.getData().getScore() + "");
                    tvUserExtralook.setText(mRes.getData().getRequestCode() + "");

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

}
