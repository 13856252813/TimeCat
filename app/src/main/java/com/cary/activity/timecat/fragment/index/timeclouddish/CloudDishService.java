package com.cary.activity.timecat.fragment.index.timeclouddish;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface CloudDishService {
    @GET("api/Folder/Page")
    Call<CloudDishPhotoCommitResult> createCommit(@Header("access-token") String token,
                                                  @Query("uid") String uid,
                                                  @Query("type") int type,
                                                  @Query("currentPage") int currentPage);
    @GET("api/FolderFile/Recycle/Page")
    Call<CloudDishPhotoCommitResult> createCommitRecycle(@Header("access-token") String token,
                                                  @Query("currentPage") int currentPage);


    @PUT("api/Folder/List")
    Call<CloudDishPhotoCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
