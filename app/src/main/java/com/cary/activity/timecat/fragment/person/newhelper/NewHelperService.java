package com.cary.activity.timecat.fragment.person.newhelper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Cary on 2018/4/6.
 */

public interface NewHelperService {
    @GET("api/CommonProblem/List")
    Call<NewHelperResult> createCommit(@Header("access-token") String token);
}
