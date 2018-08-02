package com.cary.activity.timecat.fragment.index.star;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Cary on 2018/4/6.
 */

public interface StarService {
    @GET("api/Star/Star")
    Call<StarListResult> createCommitList(@Header("access-token") String token );

}
