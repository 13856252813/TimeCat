package com.cary.activity.timecat.manager.teacher.fragment.balance;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface BalanceService {
    @GET("api/FeeRecord/Page")
    Call<BalanceResult> createCommitPage(@Header("access-token") String token,
                                         @Query("uid") int uid,
                                         @Query("currentPage") int currentPage
    );
}
