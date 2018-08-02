package com.cary.activity.timecat.manager.client.withdraw;


import com.cary.activity.timecat.manager.client.myorder.TaskDetialResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface WithDrawService {

    @GET("api/Withdraw/Page")
    Call<WithDrawResult> createCommitPage(@Header("access-token") String token,
                                          @Query("uid") int uid,
                                          @Query("currentPage") int currentpage);

    @GET("api/Withdraw/{id}")
    Call<TaskDetialResult> createCommitId(@Header("access-token") String token,
                                          @Path("id") int id);


}
