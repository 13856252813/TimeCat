package com.cary.activity.timecat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.person.advice.ComPlaintAdviceActivity;
import com.cary.activity.timecat.fragment.person.attention.MyAttentionActivity;
import com.cary.activity.timecat.fragment.person.balance.BalanceActivity;
import com.cary.activity.timecat.fragment.person.crashpledge.CashPledgeActivity;
import com.cary.activity.timecat.fragment.person.integral.IntegralActivity;
import com.cary.activity.timecat.fragment.person.invitaion.InvitaionCodeActivity;
import com.cary.activity.timecat.fragment.person.myorder.MyOrderActivity;
import com.cary.activity.timecat.fragment.person.newhelper.NewHelperActivity;
import com.cary.activity.timecat.fragment.person.receiveadd.ReceivedAddressActivity;
import com.cary.activity.timecat.fragment.person.relevanceuser.RelevanceUserActivity;
import com.cary.activity.timecat.fragment.person.self.PersonSelfApi;
import com.cary.activity.timecat.fragment.person.self.PersonSelfResult;
import com.cary.activity.timecat.fragment.person.servicephone.ServicePhoneActivity;
import com.cary.activity.timecat.fragment.person.sharemoney.ShareMoneyActivity;
import com.cary.activity.timecat.fragment.person.systemmessage.SystemMessageActivity;
import com.cary.activity.timecat.fragment.person.systemsetting.SystemSettingActivity;
import com.cary.activity.timecat.http.base.HttpUrlClient;
import com.cary.activity.timecat.reglogin.LoginActivity;
import com.cary.activity.timecat.reglogin.PerfectInformationActivity;
import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.cary.activity.timecat.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cary on 2018/4/8.
 * 个人中心
 */

public class PersonFragment extends Fragment {

    private static final String TAG = PersonFragment.class.getSimpleName();

    @BindView(R.id.rl_notify_user)
    RelativeLayout rlNotifyUser;
    @BindView(R.id.riv_user_head)
    CircleImageView rivUserHead;
    @BindView(R.id.tv_user_nick)
    TextView tvUserNick;
    @BindView(R.id.iv_user_link)
    ImageView ivUserLink;
    @BindView(R.id.tv_user_link)
    TextView tvUserLink;
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
    @BindView(R.id.iv_user_myorder)
    ImageView ivUserMyorder;
    @BindView(R.id.tv_user_adverist)
    TextView tvUserAdverist;
    @BindView(R.id.rl_user_myorder)
    RelativeLayout rlUserMyorder;
    @BindView(R.id.ll_user_myorder)
    LinearLayout llUserMyorder;
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
    @BindView(R.id.tv_user_attention)
    TextView tvUserAttention;
    @BindView(R.id.rl_user_attention)
    RelativeLayout rlUserAttention;
    @BindView(R.id.ll_user_attention)
    LinearLayout llUserAttention;
    @BindView(R.id.iv_user_reciveinfomation)
    ImageView ivUserReciveinfomation;
    @BindView(R.id.tv_user_reciveinfomation)
    TextView tvUserReciveinfomation;
    @BindView(R.id.rl_user_reciveinfomation)
    RelativeLayout rlUserReciveinfomation;
    @BindView(R.id.ll_user_reciveinfomation)
    LinearLayout llUserReciveinfomation;
    @BindView(R.id.iv_user_moneyname)
    ImageView ivUserMoneyname;
    @BindView(R.id.tv_user_moneyname)
    TextView tvUserMoneyname;
    @BindView(R.id.rl_user_moneyname)
    RelativeLayout rlUserMoneyname;
    @BindView(R.id.ll_user_moneyname)
    LinearLayout llUserMoneyname;
    @BindView(R.id.iv_user_newhelp)
    ImageView ivUserNewhelp;
    @BindView(R.id.tv_user_newhelp)
    TextView tvUserNewhelp;
    @BindView(R.id.rl_user_newhelp)
    RelativeLayout rlUserNewhelp;
    @BindView(R.id.ll_user_newhelp)
    LinearLayout llUserNewhelp;
    @BindView(R.id.iv_user_servicephone)
    ImageView ivUserServicephone;
    @BindView(R.id.tv_user_servicephone)
    TextView tvUserServicephone;
    @BindView(R.id.rl_user_servicephone)
    RelativeLayout rlUserServicephone;
    @BindView(R.id.ll_user_servicephone)
    LinearLayout llUserServicephone;
    @BindView(R.id.iv_user_sharemoney)
    ImageView ivUserSharemoney;
    @BindView(R.id.tv_user_sharemoney)
    TextView tvUserSharemoney;
    @BindView(R.id.rl_user_sharemoney)
    RelativeLayout rlUserSharemoney;
    @BindView(R.id.ll_user_sharemoney)
    LinearLayout llUserSharemoney;
    @BindView(R.id.iv_user_advice)
    ImageView ivUserAdvice;
    @BindView(R.id.tv_user_advice)
    TextView tvUserAdvice;
    @BindView(R.id.rl_user_advice)
    RelativeLayout rlUserAdvice;
    @BindView(R.id.ll_user_advice)
    LinearLayout llUserAdvice;
    @BindView(R.id.iv_user_setting)
    ImageView ivUserSetting;
    @BindView(R.id.tv_user_setting)
    TextView tvUserSetting;
    @BindView(R.id.rl_user_setting)
    RelativeLayout rlUserSetting;
    @BindView(R.id.ll_user_setting)
    LinearLayout llUserSetting;
    Unbinder unbinder;

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
        ButterKnife.bind(getActivity());

        sharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        if (TextUtils.isEmpty(token)) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
        uid = (int) sharedPreferencesHelper.getSharedPreference("id", 0);
        mApi = PersonSelfApi.getApi();
        createSingle(uid);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_notify_user,R.id.riv_user_head, R.id.tv_user_nick, R.id.iv_user_link, R.id.tv_user_link, R.id.ll_user_balance, R.id.ll_user_everylook, R.id.ll_user_extralook, R.id.rl_user_myorder, R.id.ll_user_mycasheldge, R.id.ll_user_attention, R.id.ll_user_reciveinfomation, R.id.ll_user_moneyname, R.id.ll_user_newhelp, R.id.ll_user_servicephone, R.id.ll_user_sharemoney, R.id.ll_user_advice, R.id.ll_user_setting})
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
            case R.id.tv_user_nick:
                ToastUtil.showShort(getActivity(), "昵称");

                break;
            case R.id.iv_user_link:
//                ToastUtil.showShort(getActivity(), "关联用户头像");
                intent.setClass(getActivity(), RelevanceUserActivity.class);
                intent.putExtra("relevance", mRes.getData().getRelatedUser());
                intent.putExtra("relevanceadd",0);
                startActivity(intent);
                break;
            case R.id.tv_user_link:
//                ToastUtil.showShort(getActivity(), "关联用户名字");
                intent.setClass(getActivity(), RelevanceUserActivity.class);
                intent.putExtra("relevance", mRes.getData().getRelatedUser());
                intent.putExtra("relevanceadd",0);
                startActivity(intent);
                break;
            case R.id.ll_user_balance:
//                ToastUtil.showShort(getActivity(), "余额");
                intent.setClass(getActivity(), BalanceActivity.class);
                intent.putExtra("balance", mRes.getData().getAmount());
                startActivity(intent);
                break;
            case R.id.ll_user_everylook:
//                ToastUtil.showShort(getActivity(), "积分");
                intent.setClass(getActivity(), IntegralActivity.class);
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
            case R.id.rl_user_myorder:
//                ToastUtil.showShort(getActivity(), "我的订单");
                intent.setClass(getActivity(), MyOrderActivity.class);
                if(mRes!=null)intent.putExtra("balance", mRes.getData().getAmount());
                startActivity(intent);
                break;
            case R.id.ll_user_mycasheldge:
//                ToastUtil.showShort(getActivity(), "我的押金");
                intent.setClass(getActivity(), CashPledgeActivity.class);
                intent.putExtra("totalDeposit",mRes.getData().getTotalDeposit());
                startActivity(intent);
                break;
            case R.id.ll_user_attention:
//                ToastUtil.showShort(getActivity(), "我的关注");
                intent.setClass(getActivity(), MyAttentionActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_user_reciveinfomation:
//                ToastUtil.showShort(getActivity(), "收货信息管理");
                intent.setClass(getActivity(), ReceivedAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_user_moneyname:
//                ToastUtil.showShort(getActivity(), "我的提现账户");
                intent.setClass(getActivity(), BalanceActivity.class);
                intent.putExtra("balance", mRes.getData().getAmount());
                startActivity(intent);
                break;
            case R.id.ll_user_newhelp:
//                ToastUtil.showShort(getActivity(), "新手帮助");
                intent.setClass(getActivity(), NewHelperActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_user_servicephone:
                intent.setClass(getActivity(), ServicePhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_user_sharemoney:
//                ToastUtil.showShort(getActivity(), "分享赚钱");
                intent.setClass(getActivity(), ShareMoneyActivity.class);
                intent.putExtra("myself", mRes.getData().getRequestCode());
                intent.putExtra("invitaionperson", mRes.getData().getPid());
                startActivity(intent);
                break;
            case R.id.ll_user_advice:
//                ToastUtil.showShort(getActivity(), "投诉建议");
                intent.setClass(getActivity(), ComPlaintAdviceActivity.class);
                intent.putExtra("id", "4");
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
            if(getActivity()==null){
                return;
            }
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    int relatedId = mRes.getData().getRelatedUser();//用户id
                    if (relatedId != 0) {
                        createSingle(relatedId);
                    }
                    String imageUrl =  HttpUrlClient.ALIYUNPHOTOBASEURL+mRes.getData().getImgurl();
                    Glide.with(getActivity()).load(imageUrl).into(rivUserHead);
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
    private Callback<PersonSelfResult> callbackrel = new Callback<PersonSelfResult>() {
        @Override
        public void onResponse(Call<PersonSelfResult> call, Response<PersonSelfResult> response) {
            if (response.isSuccessful()) {
                if(getActivity()==null){
                    return;
                }
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
//                    RequestOptions options2 = new RequestOptions()
//                            .override(88, 88)
//                            .placeholder(R.mipmap.avatarw)
//                            .error(R.mipmap.avatarw)
//                            .priority(Priority.HIGH)
//                            .transform(new GlideCircleTransform(getActivity(), 2, getActivity().getResources().getColor(R.color.black)));
                    String imageUrl = //"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2595140314,823568551&fm=27&gp=0.jpg";
                    HttpUrlClient.ALIYUNPHOTOBASEURL+mRes.getData().getImgurl();
                    Glide.with(getActivity()).load(imageUrl).into(rivUserHead);

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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void createSingle(int uid) {
        Call<PersonSelfResult> call = mApi.getService().createCommit(token, uid);
        if (this.uid == uid) {
            call.enqueue(callback);
        } else {
            call.enqueue(callbackrel);
        }
    }
}
