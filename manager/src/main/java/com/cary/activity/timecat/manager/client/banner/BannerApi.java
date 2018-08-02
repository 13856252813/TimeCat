package com.cary.activity.timecat.manager.client.banner;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class BannerApi {
    public static BannerApi getApi(){
        return SingleHolder.regApi;
    }

    private static class SingleHolder{
        public static BannerApi regApi = new BannerApi();
    }

    private BannerService service;

    private BannerApi(){
        String  httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(BannerService.class);
    }

    public BannerService getService(){
        return service;
    }
}
