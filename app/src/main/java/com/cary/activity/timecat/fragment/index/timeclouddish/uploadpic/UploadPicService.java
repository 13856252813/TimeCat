package com.cary.activity.timecat.fragment.index.timeclouddish.uploadpic;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface UploadPicService {
    @FormUrlEncoded
    @POST("api/FolderFile")
    Call<UploadPicCommitResult> createCommit(@Header("access-token") String token,
                                                  @Query("folderId") String FloderId,
                                                  @Query("key") String key,
                                                  @Query("note") String note,
                                                  @Query("fileName") String fileName);

    @PUT("api/FolderFile")
    Call<UploadPicCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
