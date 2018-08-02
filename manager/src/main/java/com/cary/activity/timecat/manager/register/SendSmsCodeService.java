package com.cary.activity.timecat.manager.register;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Cary on 2018/4/6.
 */

public interface SendSmsCodeService {
    @FormUrlEncoded
    @POST("api/WebUser/Sms")
    Call<NormalCommitResult> createCommit(
            @Field("mobile") String mobile);
    @FormUrlEncoded
    @POST("api/WebUser/Sms")
    Call<NormalCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
