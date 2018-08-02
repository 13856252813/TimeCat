package com.cary.activity.timecat.manager.teacher.fragment.schedule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface ScheduleService {
    @GET("api/TeacherSchedule/Page")
    Call<ScheduleResult> createCommitPage(@Header("access-token") String token,
                                        @Query("currentPage")int currentPage);

    @GET("api/TeacherSchedule/Page")
    Call<ScheduleResult> createCommitPageSuccess(@Header("access-token") String token,
                                          @Query("success")boolean success,
                                          @Query("currentPage")int currentPage);

}

