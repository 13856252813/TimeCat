package com.cary.activity.timecat.manager.client.people;

import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class TeacherApplyApi {
    public static TeacherApplyApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static TeacherApplyApi regApi = new TeacherApplyApi();
    }

    private TeacherApplyService service;

    private TeacherApplyApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(TeacherApplyService.class);
    }

    public TeacherApplyService getService() {
        return service;
    }
}
