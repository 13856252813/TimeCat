package com.cary.activity.timecat.fragment.index.fulldress.confirmorder;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class TeacherApi {
    public static TeacherApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static TeacherApi regApi = new TeacherApi();
    }

    private TeacherService service;

    private TeacherApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(TeacherService.class);
    }

    public TeacherService getService() {
        return service;
    }
}
