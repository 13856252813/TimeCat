package com.cary.activity.timecat.http.base;

import com.cary.activity.timecat.model.AttractionBean;
import com.cary.activity.timecat.model.Order;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {


    @GET("api/PackageAttraction/Attractions/{id}")
    Call<AttractionBean> getAttractionsById(@Header("access-token") String token, @Path("id") String id);

    @POST("api/Order")
    Call<Order> loadOrder(@Header("access-token") String token, @Query("packageId") String packageId,//套餐id
                          @Query("uid") String uid);
}
