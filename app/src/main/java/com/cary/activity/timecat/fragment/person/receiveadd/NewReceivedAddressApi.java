package com.cary.activity.timecat.fragment.person.receiveadd;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class NewReceivedAddressApi {
    public static NewReceivedAddressApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static NewReceivedAddressApi regApi = new NewReceivedAddressApi();
    }

    private NewReceivedAddressService service;

    private NewReceivedAddressApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(NewReceivedAddressService.class);
    }

    public NewReceivedAddressService getService() {
        return service;
    }
}
