package com.cary.activity.timecat.manager.teacher.fragment.hotscenic;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface HotScenicService {
    @GET("api/Attractions/List")
    Call<HotScenicCommitResult> createCommit(@Header("access_token") String token);

    @GET("api/Attractions/Page")
    Call<HotScenicCommitResult> createCommitPage(@Header("access_token") String token,
                                                 @Query("currentPage") int currentpage);

    @GET("api/Attractions/List")
    Call<HotScenicCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
