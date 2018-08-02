package com.cary.activity.timecat.fragment.index.selectstore.detial;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StoreCollectService {
    //门店 关注
    @POST("api/StoreAttention")
    Call<StoreDetialCollect> createCommitCollect(@Header("access-token") String token,
                                                @Query("storeId") String storeId,
                                                @Query("uid") int uid);


    //门店 删除关注  根据id 删除
    @DELETE("api/StoreAttention/{id}")
    Call<StoreDetialCollect> createCommitUnCollect(@Header("access-token") String token,
                                                  @Path("id") String uid);

}
