package com.cary.activity.timecat.manager.oss;

import android.content.Context;
import android.util.Log;

import com.cary.activity.timecat.manager.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cary on 2018/4/6.
 */

public class OSSCredentialsClass {
    private static final String TAG = OSSCredentialsClass.class.getSimpleName();
    private Context mContext;
    private OSSCredentialsApi osscredentialsApi;
    private OSSCredentialsCommitResult osscredentialsComRes;

    public OSSCredentialsClass(Context mContext) {
        this.mContext = mContext;
        osscredentialsApi = OSSCredentialsApi.getApi();
    }

    public void OSSCredentialsMethod() {
        createSingle();
//        createMap();
    }

    public OSSCredentialsCommitResult getOSSCredentialsResult() {
        return osscredentialsComRes;
    }

    private Callback<OSSCredentialsCommitResult> callback = new Callback<OSSCredentialsCommitResult>() {
        @Override
        public void onResponse(Call<OSSCredentialsCommitResult> call, Response<OSSCredentialsCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                OSSCredentialsCommitResult osscredentialsComRes = response.body();
                if ("00".equals(osscredentialsComRes.getCode())) {
                    OSSCredentialsClass.this.osscredentialsComRes = osscredentialsComRes;
                } else {
                    ToastUtil.showShort(mContext, osscredentialsComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<OSSCredentialsCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<OSSCredentialsCommitResult> call = osscredentialsApi.getService().createCommit();
        call.enqueue(callback);
    }

    private void createMap() {
        Map map = new HashMap();
        Call<OSSCredentialsCommitResult> call = osscredentialsApi.getService().createCommit(map);
        call.enqueue(callback);
    }

}
