package com.cary.activity.timecat.fragment.person.self;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Cary on 2018/4/6.
 */

public interface PersonSelfService {
    @GET("api/WebUser/{id}")
    Call<PersonSelfResult> createCommit(@Header("access-token") String token,
                                        @Path("id") int id);
}
