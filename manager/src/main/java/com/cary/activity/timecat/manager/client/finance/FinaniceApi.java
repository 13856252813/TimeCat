package com.cary.activity.timecat.manager.client.finance;

import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class FinaniceApi {
    public static FinaniceApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static FinaniceApi regApi = new FinaniceApi();
    }

    private FinaniceService service;

    private FinaniceApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(FinaniceService.class);
    }

    public FinaniceService getService() {
        return service;
    }
}
