package com.cary.activity.timecat.manager.client.showimage;

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

public interface ShowImageService {
    @GET("api/PhotoPackage/Page")
    Call<ShowImageCommitResult> createCommit(@Header("access-token") String token,
                                             @Query("currentPage") String currentPage);
    @PUT("api/PhotoPackage/Page")
    Call<ShowImageCommitResult> createCommit(@FieldMap Map<String, Object> map);

    @GET("api/FolderFile/{id}")
    Call<ImageDataResult> createCommitImage(@Header("access-token") String token,
                                            @Path("id") int id);

}
