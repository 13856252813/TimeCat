package com.cary.activity.timecat.fragment.index.timeclouddish.photo;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class CloudDishPhotoApi {
    public static CloudDishPhotoApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static CloudDishPhotoApi regApi = new CloudDishPhotoApi();
    }

    private CloudDishPhotoService service;

    private CloudDishPhotoApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(CloudDishPhotoService.class);
    }

    public CloudDishPhotoService getService() {
        return service;
    }
}
