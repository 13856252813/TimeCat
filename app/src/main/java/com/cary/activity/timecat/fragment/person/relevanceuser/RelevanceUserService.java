package com.cary.activity.timecat.fragment.person.relevanceuser;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface RelevanceUserService {
    @POST("api/UserRelated")
    Call<RelevanceDetialResult> createCommitAdd(@Header("access-token") String token,
                                                @Query("uid") int uid,
                                                @Query("mobile") String mobile);

    @GET("api/UserRelated/List")
    Call<RelevanceResult> createCommitList(@Header("access-token") String token,
                                           @Query("uid") int id);


    @DELETE("api/UserRelated/{id}")
    Call<RelevanceDetialResult> createCommitDelId(@Header("access-token") String token,
                                                  @Path("id") int id);

    @PUT("api/UserRelated/{id}")
    Call<RelevanceDetialResult> createCommitPutId(@Header("access-token") String token,
                                                  @Path("id") int id,
                                                  @Query("mobile") String mobile);

}
