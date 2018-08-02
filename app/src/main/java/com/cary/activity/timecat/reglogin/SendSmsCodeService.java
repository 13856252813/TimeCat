package com.cary.activity.timecat.reglogin;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface SendSmsCodeService {
    @GET("api/WebUser/Sms")
    Call<NormalCommitResult> createCommit(@Query("mobile") String mobile);

    @GET("api/WebUser/Sms")
    Call<NormalCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
