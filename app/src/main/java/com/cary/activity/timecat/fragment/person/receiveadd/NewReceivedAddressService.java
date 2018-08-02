package com.cary.activity.timecat.fragment.person.receiveadd;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface NewReceivedAddressService {
    @FormUrlEncoded
    @POST("api/UserAddress")
    Call<NewReceivedResult> createCommit(
            @Header("access-token") String token,
//            @Field("mobile") String mobile,
            @Field("uid") int uid,
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("province") String province,
            @Field("city") String city,
            @Field("area") String area,
            @Field("detail") String detail,
            @Field("isDefault") boolean isDefault
    );

    @PUT("api/UserAddress/{id}")
    Call<NewReceivedResult> createCommitPutId(@Header("access-token") String token,
                                              @Path("id") int id,
                                              @Query("uid") int uid,
                                              @Query("name") String name,
                                              @Query("mobile") String mobile,
                                              @Query("province") String province,
                                              @Query("city") String city,
                                              @Query("area") String area,
                                              @Query("detail") String detail,
                                              @Query("isDefault") boolean isDefault);

}
