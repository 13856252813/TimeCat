package com.cary.activity.timecat.fragment.person.integral;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface IntegralService {
    @GET("api/FeeRecord/Page")
    Call<IntegralResult> createCommitPage(@Header("access-token") String token,
                                          @Query("uid") int uid,
                                          @Query("currentPage") int currentPage
    );
}
