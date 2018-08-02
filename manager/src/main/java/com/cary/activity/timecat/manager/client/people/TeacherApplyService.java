package com.cary.activity.timecat.manager.client.people;


import com.cary.activity.timecat.manager.client.myorder.TaskDetialResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface TeacherApplyService {

    @GET("api/TeacherInfo/Page")
    Call<TeacherApplyResult> createCommitPage(@Header("access-token") String token,
                                          @Query("storeId") int storeId,
                                          @Query("currentPage") int currentpage);

    @GET("api/TeacherInfo/{id}")
    Call<TaskDetialResult> createCommitId(@Header("access-token") String token,
                                          @Path("id") int id);



}
