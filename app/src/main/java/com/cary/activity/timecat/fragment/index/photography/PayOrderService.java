package com.cary.activity.timecat.fragment.index.photography;

import com.cary.activity.timecat.util.modelbean.ModelBeanData;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 订单
 */
public interface PayOrderService {


    // 下单
    @POST("api/Order")
    Call<PayOrderResult> createCommitOrder(@Header("access-token") String token,
                                           @Query("uid") String uid,//用户id
                                           @Query("packageId") String packageId  //套餐id
    );


    // 支付保证金  定金
    @POST("api/Order/Earnest")
    Call<ModelBeanData> createCommitPayOrderEarnest(@Header("access-token") String token,
                                                    @Query("orderId") int orderId,//订单Id
                                                    @Query("payType") String payType  //支付方式
    );

    //服装购买
    @POST("api/ClothesOrder")
    Call<PayClothResult> createCommitPayCloth(@Header("access-token") String token,
                                              @Query("uid") int uid,
                                              @Query("orderCount") String orderCount,//数量
                                              @Query("clothId") int clothId,//服装Id
                                              @Query("payType") String payType, //支付方式
                                              @Query("addressId") int addressId
    );


    //服装租借
    @POST("api/RentOrder")
    Call<RentOrderResult> createCommitRentCloth(@Header("access-token") String token,
                                              @Query("uid") int uid,
                                              @Query("orderCount") String orderCount,//数量
                                              @Query("clothId") int clothId,//服装Id
                                              @Query("payType") String payType, //支付方式
                                              @Query("addressId") int addressId
    );


}
