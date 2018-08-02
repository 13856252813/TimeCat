package com.cary.activity.timecat.fragment.person.attention.news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface NewsAttentionService {

   @GET("api/InformationAttention/Page")
    Call<NewsAttentionResult> createCommitPage(@Header("access-token") String token,
                                            @Query("currentPage") int currentPage,
                                            @Query("uid") int id);

    @GET("api/InformationAttention/{id}")
    Call<NewsAttentionDetialResult> createCommitID(@Header("access-token") String token,
                                          @Path("id") int id);



}
