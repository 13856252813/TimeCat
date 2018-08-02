package com.cary.activity.timecat.fragment.index.selectfriendpay;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * 好友列表
 */
public interface FriendListService {

    // 分页列表
    @GET("api/Friend/Page")
    Call<FriendListResult> createCommitPage(@Header("access-token") String token,
                                            @Query("currentPage") int orderId //页数
                                            );


}
