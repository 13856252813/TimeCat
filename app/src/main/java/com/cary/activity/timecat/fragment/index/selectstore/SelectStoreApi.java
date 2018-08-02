package com.cary.activity.timecat.fragment.index.selectstore;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class SelectStoreApi {
    public static SelectStoreApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static SelectStoreApi regApi = new SelectStoreApi();
    }

    private SelectStoreService service;

    private SelectStoreApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(SelectStoreService.class);
    }

    public SelectStoreService getService() {
        return service;
    }
}
