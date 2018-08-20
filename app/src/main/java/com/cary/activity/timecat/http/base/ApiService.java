package com.cary.activity.timecat.http.base;

import com.cary.activity.timecat.model.AttractionBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiService {


    @GET("api/PackageAttraction/Attractions/{id}")
    Call<AttractionBean> getAttractionsById(@Header("access-token") String token, @Path("id")String id);
}
