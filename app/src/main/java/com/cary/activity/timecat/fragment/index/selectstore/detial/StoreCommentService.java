package com.cary.activity.timecat.fragment.index.selectstore.detial;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface StoreCommentService {
    //门店评论
    @GET("api/PhotoPackageEva/List")
    Call<StoreDetialCommentResult> createCommitList(@Header("access-token") String token,
                                                 @Query("storeId") int storeId);


    //套餐评论
    @GET("api/PhotoPackageEva/List")
    Call<StoreDetialCommentResult> createCommitMealList(@Header("access-token") String token,
                                                   @Query("packageId") String packageId);


}
