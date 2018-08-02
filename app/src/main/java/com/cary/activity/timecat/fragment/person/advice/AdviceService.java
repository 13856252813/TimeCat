package com.cary.activity.timecat.fragment.person.advice;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Cary on 2018/4/6.
 */

public interface AdviceService {
    @FormUrlEncoded
    @POST("api/Feedback")
    Call<AdviceResult> createCommit(
            @Header("access-token") String token,
//            @Field("mobile") String mobile,
            @Field("uid") String uid,
            @Field("name") String name,
            @Field("content") String content,
            @Field("mobile") String mobile,
            @Field("questionType") String questionType,
            @Field("hasAnswer") boolean hasAnswer,
            @Field("success") boolean success
    );
}
