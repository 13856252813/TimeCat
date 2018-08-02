package com.cary.activity.timecat.fragment.person.invitaion;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface InvitaionService {
    @GET("api/UserCenter/Request")
    Call<InvitaionResult> createCommitPage(@Header("access-token") String token,
                                           @Query("uid") int uid
    );

    @GET("api/UserCenter/SetParent")
    Call<InvitaionResult> createCommitCode(@Header("access-token") String token,
                                           @Query("uid") int uid,
                                           @Query("requestCode") String requestCode
    );
}
