package com.cary.activity.timecat.manager.teacher.fragment.self;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class PersonSelfApi {
    public static PersonSelfApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static PersonSelfApi regApi = new PersonSelfApi();
    }

    private PersonSelfService service;

    private PersonSelfApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(PersonSelfService.class);
    }

    public PersonSelfService getService() {
        return service;
    }
}
