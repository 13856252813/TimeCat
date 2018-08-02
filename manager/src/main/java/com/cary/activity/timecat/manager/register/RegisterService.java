package com.cary.activity.timecat.manager.register;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface RegisterService {
    /**
     * @param mobile
     * @param password
     * @param requestCode
     * @param type
     * @param smsCode
     * @return
     */
    @FormUrlEncoded
    @POST("api/WebUser")
    Call<RegisterCommitResult> createCommit(
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("requestCode") String requestCode,
            @Field("type") String type,
            @Field("smsCode") String smsCode);

    @PUT("api/WebUser/{id}")
    Call<RegisterCommitResult> createCommitPutId(@Header("access-token") String token,
                                                 @Path("id") int id,
                                                 @Query("nickname") String nickname,
                                                 @Query("imgurl") String imgurl);
    @PUT("api/WebUser/{id}")
    Call<RegisterCommitResult> createCommitPutNick(@Header("access-token") String token,
                                                   @Path("id") int id,
                                                   @Query("nickname") String nickname);
    @PUT("api/WebUser/{id}")
    Call<RegisterCommitResult> createCommitPutBucketName(@Header("access-token") String token,
                                                         @Path("id") int id,
                                                         @Query("imgurl") String imgurl);

    @FormUrlEncoded
    @POST("api/WebUser")
    Call<RegisterCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
