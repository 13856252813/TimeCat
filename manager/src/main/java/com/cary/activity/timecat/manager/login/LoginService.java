package com.cary.activity.timecat.manager.login;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Cary on 2018/4/6.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("api/WebUser/Login")
    Call<LoginCommitResult> createCommit(@Field("mobile") String mobile,
                                         @Field("password") String password,
                                         @Field("deviceType") String deviceType,//设备类型(android-安卓,ios-苹果)
                                         @Field("type") String type,//类型(0个人,1老师,2管理人员)
                                         @Field("deviceToken") String deviceToken//友盟token device
    );

    @FormUrlEncoded
    @POST("api/WebUser/Login")
    Call<LoginCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
