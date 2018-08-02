package com.cary.activity.timecat.fragment.index.hotscenic;

import com.cary.activity.timecat.fragment.index.fulldress.selection.FullDressColothCollect;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface HotScenicService {
    @GET("api/Attractions/List")
    Call<HotScenicCommitResult> createCommit(@Header("access-token") String token);

    @GET("api/Attractions/Page")
    Call<HotScenicCommitResult> createCommitPage(@Header("access-token") String token,
                                                 @Query("currentPage") int currentpage);

    @GET("api/Attractions/{id}")
    Call<HotScenicDetialResult> createCommitId(@Header("access-token") String token,
                                               @Path("id") int id,
                                               @Query("loginUid")int loginUid);

    @GET("api/Attractions/List")
    Call<HotScenicCommitResult> createCommit(@FieldMap Map<String, Object> map);


    @POST("api/ClothesAttention")
    Call<FullDressColothCollect> createCommitCollectId(@Header("access-token") String token,
                                                       @Query("clothesId") int packageId,//套餐id
                                                       @Query("uid") String uid);//用户id

    @DELETE("api/ClothesAttention/{id}")
    Call<FullDressColothCollect> createCommitUnCollectId(@Header("access-token") String token,
                                                         @Path("id") int packageId //套餐id
    );//用户id
}
