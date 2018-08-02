package com.cary.activity.timecat.fragment.index.fulldress.expressmethod;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Cary on 2018/4/6.
 */

public interface ExpressService {


    @GET("api/Express/List")
    Call<ExpressResult> createCommitList(@Header("access-token") String token);


}
