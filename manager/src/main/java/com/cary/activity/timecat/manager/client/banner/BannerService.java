package com.cary.activity.timecat.manager.client.banner;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;

/**
 * Created by Cary on 2018/4/6.
 */

public interface BannerService {
    @GET("api/Banner/List")
    Call<BannerCommitResult> createCommit();
    @GET("api/Banner/List")
    Call<BannerCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
