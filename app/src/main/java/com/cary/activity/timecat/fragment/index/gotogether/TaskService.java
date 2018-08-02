package com.cary.activity.timecat.fragment.index.gotogether;

import com.cary.activity.timecat.fragment.person.myorder.TaskListResult;
import com.cary.activity.timecat.util.modelbean.ModelBeanData;

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

public interface TaskService {
    //发布订单
    @FormUrlEncoded
    @POST("api/FollowTask")
    Call<TaskAddResult> createCommitAdd(@Header("access-token") String token,
                                        @Field("uid") int uid,
                                        @Field("serviceTime") String serviceTime,
                                        @Field("serviceCity") String serviceCity,
                                        @Field("content") String content,
                                        @Field("amount") String amount,
                                        @Field("type") String type,
                                        @Field("payType") String payType);

    @GET("api/FollowTask/Page")
    Call<TaskListResult> createCommitPage(@Header("access-token") String token,
                                          @Query("uid") int uid,
                                          @Query("over") boolean over,
                                          @Query("currentPage") int currentpage);

    @GET("api/FollowTask/{id}")
    Call<TaskDetialResult> createCommitId(@Header("access-token") String token,
                                          @Path("id") int id);

    @POST("api/FollowTask/Select")
    Call<ModelBeanData> createCommitGrabsId(@Header("access-token") String token,
                                            @Query("taskId") int taskId,
                                            @Query("id") int id//抢单老师id
    );

}
