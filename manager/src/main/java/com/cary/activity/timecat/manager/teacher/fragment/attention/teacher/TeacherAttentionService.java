package com.cary.activity.timecat.manager.teacher.fragment.attention.teacher;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface TeacherAttentionService {

    @GET("api/TeacherAttention/Page")
    Call<TeacherAttentionResult> createCommitPage(@Header("access-token") String token,
                                                  @Query("currentPage") int currentPage,
                                                  @Query("uid") int id);


}
