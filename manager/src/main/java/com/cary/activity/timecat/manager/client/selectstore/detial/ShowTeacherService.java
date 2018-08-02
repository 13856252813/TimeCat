package com.cary.activity.timecat.manager.client.selectstore.detial;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ShowTeacherService {


    //老师列表
    @GET("api/TeacherInfo/List")
    Call<ShowTeacherComResult> createCommitList(@Header("access-token") String token,
                                                @Query("storeId") String storeId);



}
