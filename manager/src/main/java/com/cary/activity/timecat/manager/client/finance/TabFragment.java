package com.cary.activity.timecat.manager.client.finance;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.TimeUtil;
import com.cary.activity.timecat.manager.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cary on 2018/4/10.
 */

@SuppressLint("ValidFragment")
public class TabFragment extends Fragment {
    private static final String TAG = TabFragment.class.getSimpleName();
    @BindView(R.id.tv_finanic_statisic_storename)
    TextView tvFinanicStatisicStorename;
    @BindView(R.id.spinner_finanic_statisic_select)
    Spinner spinnerFinanicStatisicSelect;
    @BindView(R.id.tv_finanic_statisic_date)
    TextView tvFinanicStatisicDate;
    @BindView(R.id.tv_user_newadd_text)
    TextView tvUserNewaddText;
    @BindView(R.id.tv_user_newadd)
    TextView tvUserNewadd;
    @BindView(R.id.tv_orderadd_text)
    TextView tvOrderaddText;
    @BindView(R.id.tv_order_add)
    TextView tvOrderAdd;
    @BindView(R.id.tv_today_money_text)
    TextView tvTodayMoneyText;
    @BindView(R.id.tv_today_money)
    TextView tvTodayMoney;
    @BindView(R.id.tv_money_text)
    TextView tvMoneyText;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_drawwith_text)
    TextView tvDrawwithText;
    @BindView(R.id.tv_drawwith)
    TextView tvDrawwith;
    @BindView(R.id.tv_drawwith_money_text)
    TextView tvDrawwithMoneyText;
    @BindView(R.id.tv_drawwith_money)
    TextView tvDrawwithMoney;
    @BindView(R.id.tv_cash_pledge_money_text)
    TextView tvCashPledgeMoneyText;
    @BindView(R.id.tv_cash_pledge_money)
    TextView tvCashPledgeMoney;
    Unbinder unbinder;

    private int position = 0;
    private FinaniceApi mApi;
    private FinaniceResult mRes;

    private SharedPreferencesHelper sharePh;
    private String token;
    private int uid;
    private int storeId;
    private TimeUtil timeUtil;
//    public static Fragment newInstance(int pos) {
//        position = pos;
//        TabFragment fragment = new TabFragment();
//        return fragment;
//    }

    public TabFragment(int pos) {
        this.position = pos;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_finance_statisic_content, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        sharePh = new SharedPreferencesHelper(getActivity());
        token = (String) sharePh.getSharedPreference("token", "");
        uid = (int) sharePh.getSharedPreference("id", 0);
        storeId = (int) sharePh.getSharedPreference("storeId", 0);
        timeUtil = new TimeUtil();
        if (position == 3) {
            tvUserNewaddText.setText("总用户量");
            tvOrderaddText.setText("总拍摄量");
            tvTodayMoneyText.setText("总实收额");
            tvMoneyText.setText("提现申请");
            tvDrawwithText.setText("提现金额");
            tvDrawwithMoneyText.setText("押金总额");
            tvCashPledgeMoneyText.setVisibility(View.GONE);
            tvCashPledgeMoney.setVisibility(View.GONE);
        } else {
            tvUserNewaddText.setText("用户新增");
            tvOrderaddText.setText("订单新增");
            tvTodayMoneyText.setText("今日实收");
            tvMoneyText.setText("定金收取");
            tvDrawwithText.setText("提现申请");
            tvDrawwithMoneyText.setText("提现金额");
        }
        mApi = FinaniceApi.getApi();
        setMethod();

        return rootView;
    }

    //    查询日期范围[day-日,month-月,year-年,all-全部]
//    查询日期[YYYY-MM-DD]
//查询类型(all-全部,wedding-婚纱摄影,photo-写真摄影,baby-宝宝摄影)
    private void setMethod() {
        if (position == 0) {
            //年
            createSingle("year", timeUtil.getNowYearMoth(), "all");
        } else if (position == 1) {
            //月
            createSingle("month", timeUtil.getNowYearMoth(), "all");
        } else if (position == 2) {
            //日
            createSingle("day", timeUtil.getNowYearMoth(), "all");
        } else if (position == 3) {
            //q全部
            createSingle("all", timeUtil.getNowYearMoth(), "all");
        }
    }

    // 加载数据
    private Callback<FinaniceResult> callbackMeal = new Callback<FinaniceResult>() {
        @Override
        public void onResponse(Call<FinaniceResult> call, Response<FinaniceResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    if (position == 3) {
//                        tvUserNewadd.setText(mRes.getData().get"名");//"总用户量");
//                        tvOrderAdd.setText("¥"+);//"总拍摄量");
//                        tvTodayMoney.setText("¥"+);//"总实收额");
//                        tvMoney.setText("¥"+);//"提现申请");
//                        tvDrawwith.setText("¥"+);//"提现金额");
//                        tvDrawwithMoney.setText("¥"+mRes.getData().getDepositAmout());//押金总额");
                        tvCashPledgeMoneyText.setVisibility(View.GONE);
                        tvCashPledgeMoney.setVisibility(View.GONE);
                    }
//                    else {
                        tvFinanicStatisicStorename.setText(mRes.getData().getStoreId() + "");
                        tvUserNewadd.setText(mRes.getData().getUserCount() + "名");
                        tvOrderAdd.setText(mRes.getData().getOrderCount() + "笔");
                        tvTodayMoney.setText("¥" + mRes.getData().getIncome());
                        tvMoney.setText(mRes.getData().getEarnest() + "");
                        tvDrawwith.setText(mRes.getData().getWithdrawCount() + "笔");
                        tvDrawwithMoney.setText("¥" + mRes.getData().getWithdrawAmount());
                        tvCashPledgeMoney.setText("¥" + mRes.getData().getDepositAmout());
//                    }
                } else {
                    ToastUtil.showShort(getActivity(), mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<FinaniceResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle(String queryTimeType, String queryTime, String queryType) {

        Call<FinaniceResult> call = mApi.getService().createCommit(token, storeId, queryTimeType, queryTime, queryType);
        call.enqueue(callbackMeal);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}