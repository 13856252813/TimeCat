package com.cary.activity.timecat.fragment.look.interact;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface InteractService {

    @GET("api/InteractiveBar/Page")
    Call<InteractListResult> createCommitPageType(@Header("access-token") String token,
                                                  @Query("type") int type,//0 互动，1 评论
                                                  @Query("currentPage") int currentPage);

    @GET("api/InteractiveBar/Page")
    Call<InteractListResult> createCommitPageMy(@Header("access-token") String token,
                                                @Query("uid") int uid,
                                                @Query("currentPage") int currentPage);

    @GET("api/InteractiveBar/Page")
    Call<InteractListResult> createCommitPageAll(@Header("access-token") String token,
                                                 @Query("currentPage") int currentPage);

    //获取详细信息
    @GET("api/InteractiveBar/{id}")
    Call<InteractDetialResult> createCommitID(@Header("access-token") String token,
                                              @Path("id") int id);

    //增加评论
    @FormUrlEncoded
    @POST("api/InteractiveBar/Eva")
    Call<InteractCommentAddResult> createCommitAddComment(@Header("access-token") String token,
                                                          @Field("interactiveId") int interactiveId,
                                                          @Field("uid") int uid,
                                                          @Field("content") String content);
    @FormUrlEncoded
    @POST("api/InteractiveBar")
    Call<InteractDetialResult> createCommitAdd(@Header("access-token") String token,
                                                          @Field("uid") int uid,
                                                          @Field("content")String content,
                                                          @Field("imgurl") String imgurl,
                                                          @Field("videoUrl") String videoUrl);

}
