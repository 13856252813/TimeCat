package com.cary.activity.timecat.download;



import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @see  下载拦截器
 * Created by Cary on 2018/3/29.
 */

public class DownloadInterceptor implements Interceptor {
    private DownloadListener listener;

    public DownloadInterceptor(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder()
                .body(new DownloadResponseBody(response.body(), listener))
                .build();
    }
}
