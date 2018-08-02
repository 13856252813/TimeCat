package com.cary.activity.timecat.fragment.index.selectstore.detial;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ShowTeacherService {

    //时光老师
    @GET("api/TeacherInfo/List")
    Call<StoreTeacherResult> createCommitList(@Header("access-token") String token,
                                              @Query("storeId") int storeId);


}
