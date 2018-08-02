package com.cary.activity.timecat.manager.client.myorder;

import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class TaskApi {
    public static TaskApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static TaskApi regApi = new TaskApi();
    }

    private TaskService service;

    private TaskApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(TaskService.class);
    }

    public TaskService getService() {
        return service;
    }
}
