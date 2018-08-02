package com.cary.activity.timecat.manager.client.fulldress;


import com.cary.activity.timecat.manager.client.fulldress.detial.FullDressColothNorm;
import com.cary.activity.timecat.manager.client.fulldress.detial.FullDressDetialResult;
import com.cary.activity.timecat.manager.client.fulldress.fragment.FullDressColtheResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface FullDressTabService {
    @GET("api/ClothesCatagory/List")
    Call<FullDressTabResult> createCommitList(@Header("access-token") String token,
                                              @Query("sex") String sex,//(0-男,1-女)
                                              @Query("type") String type //(0-售卖,1-共享)
    );

    @GET("api/Clothes/Page")
    Call<FullDressColtheResult> createCommitColothPage(@Header("access-token") String token,
                                                       @Query("catagory") String catagory,//分了的id
                                                       @Query("sex") String sex,//(0-男,1-女)
                                                       @Query("type") String type, //(0-售卖,1-共享)
                                                       @Query("currentPage") int currentPage
    );

    //获取服装详情
    @GET("api/Clothes/{id}")
    Call<FullDressDetialResult> createCommitColothId(@Header("access-token") String token,
                                                     @Path("id") String id
    );


    //服装规格
    @GET("api/ClothesNorm/List")
    Call<FullDressColothNorm> createCommitColothNorm(@Header("access-token") String token,
                                                     @Query("clothId") String clothId//服装id
    );

}
