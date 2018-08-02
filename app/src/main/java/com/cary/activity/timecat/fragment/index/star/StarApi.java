package com.cary.activity.timecat.fragment.index.star;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class StarApi {
    public static StarApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static StarApi regApi = new StarApi();
    }

    private StarService service;

    private StarApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(StarService.class);
    }

    public StarService getService() {
        return service;
    }
}
