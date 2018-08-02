package com.cary.activity.timecat.fragment.index.selectstore.detial;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class StoreCommentApi {
    public static StoreCommentApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static StoreCommentApi regApi = new StoreCommentApi();
    }

    private StoreCommentService service;

    private StoreCommentApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(StoreCommentService.class);
    }

    public StoreCommentService getService() {
        return service;
    }
}
