package com.cary.activity.timecat.manager.client.withdraw;

import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class WithDrawApi {
    public static WithDrawApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static WithDrawApi regApi = new WithDrawApi();
    }

    private WithDrawService service;

    private WithDrawApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(WithDrawService.class);
    }

    public WithDrawService getService() {
        return service;
    }
}
