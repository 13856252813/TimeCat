package com.cary.activity.timecat.manager.client.myorder;


import com.cary.activity.timecat.manager.util.modelbean.ModelBeanData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface TaskService {

    @GET("api/ManagementOrder/Page")
    Call<TaskListResult> createCommitPage(@Header("access-token") String token,
                                          @Query("storeId") int storeId,
                                          @Query("status") int status,
                                          @Query("currentPage") int currentpage);

    @GET("api/ManagementOrder/Detail/a{orderType}/{id}")
    Call<TaskDetialResult> createCommitId(@Header("access-token") String token,
                                          @Path("orderType")int orderType,
                                          @Path("id") int id);

    @GET("api/ManagementOrder/ReturnDetail/{orderType}/{id}")
    Call<ReturnOrderResult> createCommitIdQuestion(@Header("access-token") String token,
                                                      @Path("orderType")int orderType,
                                                      @Path("id") int id);

    @POST("api/FollowTask/Select")
    Call<ModelBeanData> createCommitGrabsId(@Header("access-token") String token,
                                            @Query("taskId") int taskId,
                                            @Query("id") int id//抢单老师id
    );

}
