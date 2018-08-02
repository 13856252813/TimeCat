package com.cary.activity.timecat.fragment.index.selectsetmeal;

import com.cary.activity.timecat.fragment.index.setmealdetial.SetMealCollectResult;
import com.cary.activity.timecat.fragment.index.setmealdetial.SetMealDetialResult;

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

public interface SetMealService {
    @GET("api/PhotoPackage/List")
    Call<SetMealResult> createCommit(@Header("access-token") String token,
                                     @Query("storeId") int storeId);

    @GET("api/PhotoPackage/Page")
    Call<SetMealResult> createCommitPage(@Header("access-token") String token,
                                         @Query("storeId") String storeId,
                                         @Query("packageType") String packageType,//0套餐1订制
                                         @Query("currentPage") int currentpage);


    //根据id获取数据
    @GET("api/PhotoPackage/{id}")
    Call<SetMealDetialResult> createCommitId(@Header("access-token") String token,
                                             @Path("id") String storeId);


    @POST("api/PackageAttention")
    Call<SetMealCollectResult> createCommitCollectId(@Header("access-token") String token,
                                                     @Query("packageId") String packageId,//套餐id
                                                     @Query("uid") String uid);//用户id

    @DELETE("api/PackageAttention/{id}")
    Call<SetMealCollectResult> createCommitUnCollectId(@Header("access-token") String token,
                                                     @Path("id") String packageId //套餐id
                                                    );//用户id

}
