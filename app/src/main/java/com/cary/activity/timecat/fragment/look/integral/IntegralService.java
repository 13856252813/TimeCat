package com.cary.activity.timecat.fragment.look.integral;

import com.cary.activity.timecat.fragment.look.integral.exchange.IntegralDetialResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface IntegralService {

    @GET("api/ScoreProduct/Page")
    Call<IntegralListResult> createCommitPage(@Header("access_token") String token,
                                              @Query("uid") int uid,
                                              @Query("currentPage") int currentPage);

    @GET("api/ScoreProduct/Page")
    Call<IntegralListResult> createCommitPageAll(@Header("access_token") String token,
                                                 @Query("currentPage") int currentPage);

    @GET("api/ScoreProduct/{id}")
    Call<IntegralDetialResult> createCommitID(@Header("access_token") String token,
                                              @Path("id") int id);

//    @GET("api/Informn/List")
//    Call<NewsCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
