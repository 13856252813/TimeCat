package com.cary.activity.timecat.manager.client.finance;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface FinaniceService {

    @GET("api/Finance")
    Call<FinaniceResult> createCommit(@Header("access-token") String token,
                                          @Query("storeId") int storeId,
                                          @Query("queryTimeType") String queryTimeType,//查询范围
                                          @Query("queryTime") String queryTime,//查询日期
                                          @Query("queryType") String queryType//查询类型
    );





}
