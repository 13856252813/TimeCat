package com.cary.activity.timecat.fragment.index.timeclouddish.newfloder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Cary on 2018/4/6.
 */

public interface NewFloderService {
    @FormUrlEncoded
    @POST("api/Folder")
    Call<NewFloderCommitResult> createCommit(@Header("access-token") String token,
                                             @Field("uid") String uid,
                                             @Field("type") int type,
                                             @Field("folder") String floderName);

    @PUT("api/Folder")
    Call<NewFloderCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
