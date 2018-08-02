package com.cary.activity.timecat.fragment.index.news;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface NewsService {
    @GET("api/Information/List")
    Call<NewsCommitResult> createCommit(@Header("access-token") String token);

   @GET("api/Information/Page")
    Call<NewsCommitResult> createCommitPage(@Header("access-token") String token,
                                            @Query("currentPage") int currentPage,
                                            @Query("type") int type);

    @GET("api/Information/{id}")
    Call<NewsDetialResult> createCommitID(@Header("access-token") String token,
                                            @Path("id") int id);



    @GET("api/BaseContent")
    Call<CEOEmailResult> createCommitCEOEmail(@Header("access-token") String token);

    @GET("api/Informn/List")
    Call<NewsCommitResult> createCommit(@FieldMap Map<String, Object> map);
}
