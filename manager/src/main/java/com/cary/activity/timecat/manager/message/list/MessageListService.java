package com.cary.activity.timecat.manager.message.list;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;

/**
 * Created by Cary on 2018/4/6.
 */

public interface MessageListService {
    @GET("api/Attractions/List")
    Call<MessageListCommitResult> createCommit();
    @GET("api/Attractions/List")
    Call<MessageListCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
