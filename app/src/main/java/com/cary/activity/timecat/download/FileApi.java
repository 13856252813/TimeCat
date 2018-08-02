package com.cary.activity.timecat.download;

import com.cary.activity.timecat.http.base.HttpResult;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @see  文件API
 * Created by Cary on 2018/3/29.
 */

public interface FileApi {
    @GET("/fileUrl")
    Observable<HttpResult<String>> getFileUrl();
}
