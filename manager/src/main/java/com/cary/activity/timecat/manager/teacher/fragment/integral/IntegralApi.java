package com.cary.activity.timecat.manager.teacher.fragment.integral;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class IntegralApi {
    public static IntegralApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static IntegralApi regApi = new IntegralApi();
    }

    private IntegralService service;

    private IntegralApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(IntegralService.class);
    }

    public IntegralService getService() {
        return service;
    }
}
