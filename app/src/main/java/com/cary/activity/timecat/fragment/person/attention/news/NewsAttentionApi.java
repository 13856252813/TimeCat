package com.cary.activity.timecat.fragment.person.attention.news;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class NewsAttentionApi {
    public static NewsAttentionApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static NewsAttentionApi regApi = new NewsAttentionApi();
    }

    private NewsAttentionService service;

    private NewsAttentionApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(NewsAttentionService.class);
    }

    public NewsAttentionService getService() {
        return service;
    }
}
