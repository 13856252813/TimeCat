package com.cary.activity.timecat.fragment.person.balance;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class BalanceApi {
    public static BalanceApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static BalanceApi regApi = new BalanceApi();
    }

    private BalanceService service;

    private BalanceApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(BalanceService.class);
    }

    public BalanceService getService() {
        return service;
    }
}
