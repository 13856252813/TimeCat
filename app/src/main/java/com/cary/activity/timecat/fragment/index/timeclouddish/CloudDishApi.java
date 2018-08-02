package com.cary.activity.timecat.fragment.index.timeclouddish;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class CloudDishApi {
    public static CloudDishApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static CloudDishApi regApi = new CloudDishApi();
    }

    private CloudDishService service;

    private CloudDishApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(CloudDishService.class);
    }

    public CloudDishService getService() {
        return service;
    }
}
