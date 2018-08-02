package com.cary.activity.timecat.fragment.person.sharemoney;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface ShareMoneyService {
    @GET("api/RequestContent")
    Call<ShareMoneyResult> createCommit(@Header("access-token") String token,
                                          @Query("uid") String id);
}
