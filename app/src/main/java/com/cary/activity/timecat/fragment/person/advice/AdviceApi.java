package com.cary.activity.timecat.fragment.person.advice;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class AdviceApi {
    public static AdviceApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static AdviceApi regApi = new AdviceApi();
    }

    private AdviceService service;

    private AdviceApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(AdviceService.class);
    }

    public AdviceService getService() {
        return service;
    }
}
