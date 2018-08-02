package com.cary.activity.timecat.manager.client.fulldress;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class FullDressTabApi {
    public static FullDressTabApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static FullDressTabApi regApi = new FullDressTabApi();
    }

    private FullDressTabService service;

    private FullDressTabApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(FullDressTabService.class);
    }

    public FullDressTabService getService() {
        return service;
    }
}
