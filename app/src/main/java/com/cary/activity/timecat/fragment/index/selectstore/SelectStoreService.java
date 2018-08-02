package com.cary.activity.timecat.fragment.index.selectstore;

import com.cary.activity.timecat.fragment.index.selectstore.detial.StoreDetialResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface SelectStoreService {
    @GET("api/Store/List")
    Call<SelectStoreCommitResult> createCommit(@Header("access-token")String token);

    @GET("api/Store/Page")
    Call<SelectStoreCommitResult> createCommitPage(@Header("access-token")String token,
//                                                   @Query("uid")String uid,
                                                   @Query("currentPage")int currentpage);
    //门店详情
    @GET("api/Store/{id}")
    Call<StoreDetialResult> createCommitID(@Header("access-token")String token,
                                           @Path("id")String uid);


    @GET("api/Store/List")
    Call<SelectStoreCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
