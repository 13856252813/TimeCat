package com.cary.activity.timecat.manager.teacher.fragment.schedule;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class ScheduleApi {
    public static ScheduleApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static ScheduleApi regApi = new ScheduleApi();
    }

    private ScheduleService service;

    private ScheduleApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(ScheduleService.class);
    }

    public ScheduleService getService() {
        return service;
    }
}
