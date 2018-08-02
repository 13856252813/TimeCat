package com.cary.activity.timecat.fragment.person.systemmessage;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class SysMsgApi {
    public static SysMsgApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static SysMsgApi regApi = new SysMsgApi();
    }

    private SysMsgService service;

    private SysMsgApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(SysMsgService.class);
    }

    public SysMsgService getService() {
        return service;
    }
}
