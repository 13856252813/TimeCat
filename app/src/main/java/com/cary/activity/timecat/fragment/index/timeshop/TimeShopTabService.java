package com.cary.activity.timecat.fragment.index.timeshop;

import com.cary.activity.timecat.fragment.index.fulldress.detial.FullDressColothNorm;
import com.cary.activity.timecat.fragment.index.fulldress.selection.FullDressColothCollect;
import com.cary.activity.timecat.fragment.index.timeshop.fragment.TimeShopProductResult;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface TimeShopTabService {
    @GET("api/MarkerProductCatagory/List")
    Call<TimeShopTabResult> createCommitList(@Header("access-token") String token);


    //获取服装详情
    @GET("api/MarkerProduct/Page")
    Call<TimeShopProductResult> createCommitPage(@Header("access-token") String token,
                                                 @Query("catagory") int catagory,
                                                 @Query("currentPage") int currentPage);
    @GET("api/MarkerProduct/{id}")
    Call<TimeShopDetialResult> createCommitId(@Header("access-token") String token,
                                                      @Path("id") int id);

    //服装规格
    @GET("api/MarkerProductNormItem/List")
    Call<FullDressColothNorm> createCommitColothNorm(@Header("access-token") String token,
                                                     @Query("clothId") int clothId//服装id
    );

    @POST("api/ClothesAttention")
    Call<FullDressColothCollect> createCommitCollectId(@Header("access-token") String token,
                                                       @Query("clothesId") int packageId,//套餐id
                                                       @Query("uid") String uid);//用户id

    @DELETE("api/ClothesAttention/{id}")
    Call<FullDressColothCollect> createCommitUnCollectId(@Header("access-token") String token,
                                                         @Path("id") int packageId //套餐id
    );//用户id

}
