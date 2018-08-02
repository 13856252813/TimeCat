package com.cary.activity.timecat.fragment.index.fulldress.expressmethod;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class ExpressApi {
    public static ExpressApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static ExpressApi regApi = new ExpressApi();
    }

    private ExpressService service;

    private ExpressApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(ExpressService.class);
    }

    public ExpressService getService() {
        return service;
    }
}
