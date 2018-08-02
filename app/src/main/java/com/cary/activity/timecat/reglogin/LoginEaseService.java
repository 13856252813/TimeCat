package com.cary.activity.timecat.reglogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Cary on 2018/4/6.
 */

public interface LoginEaseService {
    @FormUrlEncoded
    @POST("api/WebUser/Easemob")
    Call<LoginCommitResult> createCommit(
            @Header("access-token") String token,
            @Field("uid") long uid);
}
