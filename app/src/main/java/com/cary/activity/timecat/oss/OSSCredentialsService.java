package com.cary.activity.timecat.oss;

import com.cary.activity.timecat.util.modelbean.ModelBeanData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface OSSCredentialsService {
    @GET("api/OSS/OSSCredentials")
    Call<OSSCredentialsCommitResult> createCommit();
    @GET("api/OSS/OSSCredentials")
    Call<OSSCredentialsCommitResult> createCommit(@FieldMap Map<String, Object> map);

     @GET("api/OSS/Sign")
    Call<ModelBeanData> createCommitSign(@Header("access-token")String token,
                                         @Query("content")String content);
}
