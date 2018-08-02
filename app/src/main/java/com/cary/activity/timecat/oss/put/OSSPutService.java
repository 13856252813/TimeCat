package com.cary.activity.timecat.oss.put;

import com.cary.activity.timecat.reglogin.NormalCommitResult;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Cary on 2018/4/6.
 */

public interface OSSPutService {

    @Multipart
    @POST("api/OSS/OSSPut")
    Call<NormalCommitResult> createCommit(
            @Part MultipartBody.Part file);

//    @FormUrlEncoded
//    @POST("api/OSS/OSSPut")
//    Call<NormalCommitResult> createCommit(
//            @Part("description") RequestBody description,
//            @Part MultipartBody.Part file);
}
