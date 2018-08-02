package com.cary.activity.timecat.manager.client.pushmanager;

import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class PushApi {
    public static PushApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static PushApi regApi = new PushApi();
    }

    private PushService service;

    private PushApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(PushService.class);
    }

    public PushService getService() {
        return service;
    }
}
