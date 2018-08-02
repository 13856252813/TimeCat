package com.cary.activity.timecat.fragment.message.list;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface MessageListService {
    //获取环信聊天组列表
    @GET("api/HuanxinGroup/List")
    Call<MessageListCommitResult> createCommit(@Header("access-token")String token,
                                               @Query("uid")int uid);


    @GET("api/HuanxinGroup/List")
    Call<MessageListCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
