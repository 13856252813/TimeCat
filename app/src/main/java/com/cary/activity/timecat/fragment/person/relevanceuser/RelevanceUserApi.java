package com.cary.activity.timecat.fragment.person.relevanceuser;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class RelevanceUserApi {
    public static RelevanceUserApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static RelevanceUserApi regApi = new RelevanceUserApi();
    }

    private RelevanceUserService service;

    private RelevanceUserApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(RelevanceUserService.class);
    }

    public RelevanceUserService getService() {
        return service;
    }
}
