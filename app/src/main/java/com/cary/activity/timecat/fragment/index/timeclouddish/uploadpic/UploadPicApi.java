package com.cary.activity.timecat.fragment.index.timeclouddish.uploadpic;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class UploadPicApi {
    public static UploadPicApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static UploadPicApi regApi = new UploadPicApi();
    }

    private UploadPicService service;

    private UploadPicApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(UploadPicService.class);
    }

    public UploadPicService getService() {
        return service;
    }
}
