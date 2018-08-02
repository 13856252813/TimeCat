package com.cary.activity.timecat.manager.oss;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;

/**
 * Created by Cary on 2018/4/6.
 */

public interface OSSCredentialsService {
    @GET("api/OSS/OSSCredentials")
    Call<OSSCredentialsCommitResult> createCommit();
    @GET("api/OSS/OSSCredentials")
    Call<OSSCredentialsCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
