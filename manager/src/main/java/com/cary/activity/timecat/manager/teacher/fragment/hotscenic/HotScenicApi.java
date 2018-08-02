package com.cary.activity.timecat.manager.teacher.fragment.hotscenic;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class HotScenicApi {
    public static HotScenicApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static HotScenicApi regApi = new HotScenicApi();
    }

    private HotScenicService service;

    private HotScenicApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(HotScenicService.class);
    }

    public HotScenicService getService() {
        return service;
    }
}
