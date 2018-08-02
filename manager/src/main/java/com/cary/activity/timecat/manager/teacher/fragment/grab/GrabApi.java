package com.cary.activity.timecat.manager.teacher.fragment.grab;

import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class GrabApi {
    public static GrabApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static GrabApi regApi = new GrabApi();
    }

    private GrabService service;

    private GrabApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(GrabService.class);
    }

    public GrabService getService() {
        return service;
    }
}
