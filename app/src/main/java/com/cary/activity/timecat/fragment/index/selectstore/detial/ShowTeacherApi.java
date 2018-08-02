package com.cary.activity.timecat.fragment.index.selectstore.detial;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class ShowTeacherApi {
    public static ShowTeacherApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static ShowTeacherApi regApi = new ShowTeacherApi();
    }

    private ShowTeacherService service;

    private ShowTeacherApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(ShowTeacherService.class);
    }

    public ShowTeacherService getService() {
        return service;
    }
}
