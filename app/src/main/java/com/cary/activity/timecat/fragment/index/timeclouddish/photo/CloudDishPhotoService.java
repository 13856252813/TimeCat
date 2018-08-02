package com.cary.activity.timecat.fragment.index.timeclouddish.photo;

import com.cary.activity.timecat.util.modelbean.ModelBeanData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 * Todo 文件列表
 */

public interface CloudDishPhotoService {
    @GET("api/FolderFile/Page")
    Call<CloudDishPhotoResult> createCommit(@Header("access-token") String token,
                                            @Query("folderId") String floderId,
                                            @Query("currentPage") int currentPage);


    //删除文件
    @DELETE("api/FolderFile")
    Call<ModelBeanData> createCommitDel(@Header("access-token") String token,
                                        @Query("ids") String id);

    @PUT("api/FolderFile/Move")
    Call<ModelBeanData> createCommitCut(@Header("access-token") String token,
                                        @Query("ids") String id);

    @PUT("api/FolderFile/Copy")
    Call<ModelBeanData> createCommitCopy(@Header("access-token") String token,
                                         @Query("ids") String id);

    @PUT("api/FolderFile/List")
    Call<CloudDishPhotoResult> createCommit(@FieldMap Map<String, Object> map);


}
