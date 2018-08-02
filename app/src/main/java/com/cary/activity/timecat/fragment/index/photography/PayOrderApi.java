package com.cary.activity.timecat.fragment.index.photography;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class PayOrderApi {
    public static PayOrderApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static PayOrderApi regApi = new PayOrderApi();
    }

    private PayOrderService service;

    private PayOrderApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(PayOrderService.class);
    }

    public PayOrderService getService() {
        return service;
    }
}
