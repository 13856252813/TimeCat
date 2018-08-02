package com.cary.activity.timecat.manager.teacher.fragment.grab;

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

public interface GrabService {

    @GET("api/FollowTask/Page")
    Call<GrabResult> createCommitPage(@Header("access_token") String token,
                                      @Query("uid") int uid,
                                      @Query("type") String type,
                                      @Query("currentPage") int currentpage);

    @GET("api/FollowTask/{id}")
    Call<TaskDetialResult> createCommitId(@Header("access_token") String token,
                                          @Path("id") int id);

    @POST("api/FollowTask/Select")
    Call<ModelBeanData> createCommitGrabsId(@Header("access_token") String token,
                                            @Query("taskId") int taskId,
                                            @Query("id") int id//抢单老师id
    );

}
