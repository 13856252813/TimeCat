package com.cary.activity.timecat.manager.client.people;


import com.cary.activity.timecat.manager.util.modelbean.ModelBeanData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface EmployeeManagerService {

    @GET("api/Employee/List")
    Call<EmployeeManagerResult> createCommitPage(@Header("access-token") String token,
                                              @Query("shopId") int shopId);

    @FormUrlEncoded
    @POST("api/Employee")
    Call<ModelBeanData> createCommitId(@Header("access-token") String token,
                                       @Field("shopId") int shopId,
                                       @Field("mobile") String mobile,
                                       @Field("position") String position,
                                       @Field("nickname") String nickname);


}
