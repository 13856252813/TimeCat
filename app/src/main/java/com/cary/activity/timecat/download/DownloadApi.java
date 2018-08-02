package com.cary.activity.timecat.download;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Cary on 2018/3/29.
 */

public interface DownloadApi {
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}

