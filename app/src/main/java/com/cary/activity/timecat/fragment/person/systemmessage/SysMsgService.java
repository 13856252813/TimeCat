package com.cary.activity.timecat.fragment.person.systemmessage;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface SysMsgService {
    @GET("api/Information/List")
    Call<SysMsgResult> createCommit(@Header("access_token") String token);

    @GET("api/UserMsg/Page")
    Call<SysMsgResult> createCommitPage(@Header("access-token") String token,
                                        @Query("uid") String id,
                                        @Query("currentPage") int currentPage);

    @GET("api/UserMsg/{id}")
    Call<SysMsgDetialresult> createCommitID(@Header("access-token") String token,
                                            @Path("id") int id);

    @PUT("api/UserMsg")
    Call<SysMsgDetialresult> createCommitReadID(@Header("access-token") String token,
                                                @Query("id") int id,
                                                @Query("uid") int uid);
    //未读消息数量
    @GET("api/UserMsg/UnRead/{uid}")
    Call<SysMsgNumResult> createCommitUnReadNum(@Header("access-token") String token,
                                                @Query("uid") int uid);


    @GET("api/UserMsg/List")
    Call<SysMsgResult> createCommit(@FieldMap Map<String, Object> map);
}
