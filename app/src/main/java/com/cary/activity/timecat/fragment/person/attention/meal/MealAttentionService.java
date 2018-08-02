package com.cary.activity.timecat.fragment.person.attention.meal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface MealAttentionService {

    @GET("api/PackageAttention/Page")
    Call<MealAttentionResult> createCommitPage(@Header("access-token") String token,
                                               @Query("currentPage") int currentPage,
                                               @Query("uid") int id);


}
