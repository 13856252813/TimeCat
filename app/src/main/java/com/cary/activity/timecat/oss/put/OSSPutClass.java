package com.cary.activity.timecat.oss.put;

import android.content.Context;
import android.util.Log;

import com.cary.activity.timecat.reglogin.NormalCommitResult;
import com.cary.activity.timecat.util.ToastUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cary on 2018/4/6.
 */

public class OSSPutClass {
    private static final String TAG = OSSPutClass.class.getSimpleName();
    private Context mContext;
    private OSSPutApi ossPutApi;
    private OSSPutCommitParam ossPutcommitParam;

    private NormalCommitResult ossPutComRes;

    public OSSPutClass(Context mContext) {
        this.mContext = mContext;
        ossPutApi = OSSPutApi.getApi();
        ossPutcommitParam = new OSSPutCommitParam();
    }

    public void OssPutMethod(String filepath){
        ossPutcommitParam.setFilePath(filepath);
        createSingle();
    }

    public NormalCommitResult getOSSPutResult(){
        return ossPutComRes;
    }

    private Callback<NormalCommitResult> callback = new Callback<NormalCommitResult>() {
        @Override
        public void onResponse(Call<NormalCommitResult> call, Response<NormalCommitResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                NormalCommitResult ossPutComRes = response.body();
                if("00".equals(ossPutComRes.getCode())){
                    OSSPutClass.this.ossPutComRes = ossPutComRes;
                }else{
                    ToastUtil.showShort(mContext,ossPutComRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<NormalCommitResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        File file = new File(ossPutcommitParam.getFilePath());
        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);
        Call<NormalCommitResult> call = ossPutApi.getService().createCommit(body);

        call.enqueue(callback);
    }

}
