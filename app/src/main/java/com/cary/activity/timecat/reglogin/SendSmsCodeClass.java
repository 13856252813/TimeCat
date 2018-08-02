package com.cary.activity.timecat.reglogin;

import android.content.Context;
import android.util.Log;

import com.cary.activity.timecat.util.ToastUtil;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cary on 2018/4/6.
 */

public class SendSmsCodeClass {
    private static final String TAG = SendSmsCodeClass.class.getSimpleName();
    private Context mContext;
    private boolean smsCodeResult=false;//访问的结果
    private SendSmsCodeApi smsApi;
    private SendSmsCodeCommitParam smscommitParam;

    public SendSmsCodeClass(Context mContext) {
        this.mContext = mContext;
        smsApi = SendSmsCodeApi.getApi();
        smscommitParam = new SendSmsCodeCommitParam();
    }

    public void SendSmsCodeMethod(String mobile){
        smscommitParam.setMobile(mobile);
        createSingle();
    }

    public boolean getSmsCodeResult(){
        return smsCodeResult;
    }

    private Callback<NormalCommitResult> callback = new Callback<NormalCommitResult>() {
        @Override
        public void onResponse(Call<NormalCommitResult> call, Response<NormalCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                NormalCommitResult forgetpassComRes = response.body();
                if("00".equals(forgetpassComRes.getCode())){
                    smsCodeResult = true;
                }else{
                    smsCodeResult = false;
                    ToastUtil.showShort(mContext,forgetpassComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NormalCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
            smsCodeResult = false;
            ToastUtil.showShort(mContext,"发送短信失败");
        }
    };
    private void createSingle() {
        Call<NormalCommitResult> call = smsApi.getService().createCommit(
                smscommitParam.getMobile());
        call.enqueue(callback);
    }

    private void createMap() {
        Map map = smscommitParam.createCommitParams();
        Call<NormalCommitResult> call = smsApi.getService().createCommit(map);
        call.enqueue(callback);
    }


}
