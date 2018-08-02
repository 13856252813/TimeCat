package com.cary.activity.timecat.fragment.person.receiveadd;

import com.cary.activity.timecat.fragment.index.fulldress.detial.DefaultRecAddResult;
import com.cary.activity.timecat.util.modelbean.ModelBeanData;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface ReceivedAddressListService {
    @GET("api/UserAddress/Page")
    Call<ReceivedAddressListResult> createCommit(@Header("access-token") String token,
                                                 @Query("uid") int uid,
                                                 @Query("currentPage") int currentPage);

    @DELETE("api/UserAddress/{id}")
    Call<NewReceivedResult> createCommitId(@Header("access-token") String token,
                                           @Path("id") long id);

    //获取信息  编辑地址
    @GET("api/UserAddress/{id}")
    Call<NewReceivedResult> createCommitGetId(@Header("access-token") String token,
                                              @Path("id") int id);

    //获取默认地址
    @GET("api/UserAddress/Default")
    Call<DefaultRecAddResult> createCommitGetDefault(@Header("access-token") String token,
                                                     @Query("uid") int uid);

    //更新默认地址
    @PUT("api/UserAddress/{id}")
    Call<NewReceivedResult> createCommitPutId(@Header("access-token") String token,
                                              @Path("id") int id,
                                              @Query("isDefault") boolean isDefault);

    //下单兑换
    @POST("api/ScoreOrder")
    Call<ModelBeanData> createCommitPostExchange(@Header("access-token") String token,
                                                 @Query("uid") int uid,
                                                 @Query("pid") int pid,
                                                 @Query("productCount") String productCount,
                                                 @Query("addressId") int addressId,
                                                 @Query("payType") String payType);

}
