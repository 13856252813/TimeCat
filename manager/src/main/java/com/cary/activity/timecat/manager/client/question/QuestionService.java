package com.cary.activity.timecat.manager.client.question;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface QuestionService {

    @GET("api/Feedback/Page")
    Call<QuestionResult> createCommitPage(@Header("access-token") String token,
                                          @Query("currentPage") int currentpage);

    @GET("api/Feedback/Page")
    Call<QuestionResult> createCommitPage(@Header("access-token") String token,
                                          @Query("questionType") String questionType,
                                          @Query("currentPage") int currentpage);
    @GET("api/Feedback/Page")
    Call<QuestionResult> createCommitPage(@Header("access-token") String token,
                                          @Query("success") boolean success,
                                          @Query("currentPage") int currentpage);

    @GET("api/Feedback/{id}")
    Call<QuestionDetialResult> createCommitId(@Header("access-token") String token,
                                              @Path("id") int id);

    @PUT("api/Feedback/{id}")
    Call<QuestionDetialResult> createCommitPutId(@Header("access-token") String token,
                                                 @Path("id") int id,
                                                 @Query("success") boolean success);


}
