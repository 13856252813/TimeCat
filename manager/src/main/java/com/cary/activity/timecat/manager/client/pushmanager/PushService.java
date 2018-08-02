package com.cary.activity.timecat.manager.client.pushmanager;


import com.cary.activity.timecat.manager.util.modelbean.ModelBeanData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface PushService {

    @GET("api/PushRecord/Page")
    Call<PushResult> createCommitPage(@Header("access-token") String token,
                                      @Query("type") int type,
                                      @Query("storeId") int storeId,
                                      @Query("currentPage") int currentpage);

    @GET("api/PushRecord/{id}")
    Call<PushDetialResult> createCommitId(@Header("access-token") String token,
                                          @Path("id") int id);

    @FormUrlEncoded
    @POST("api/PushRecord/All")
    Call<ModelBeanData> createCommitPushAllMsg(@Header("access-token") String token,
                                               @Field("pushUid") int pushUid,
                                               @Field("storeId") int storeId,
                                               @Field("title") String title,
                                               @Field("content") String content);

    @FormUrlEncoded
    @POST("api/PushRecord")
    Call<ModelBeanData> createCommitPushMsg(@Header("access-token") String token,
                                            @Field("pushUid") int pushUid,
                                            @Field("storeId") int storeId,
                                            @Field("title") String title,
                                            @Field("content") String content,
                                            @Field("mobile") String mobile);


}
