package com.cary.activity.timecat.manager.client.people;

import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class EmployeeManagerApi {
    public static EmployeeManagerApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static EmployeeManagerApi regApi = new EmployeeManagerApi();
    }

    private EmployeeManagerService service;

    private EmployeeManagerApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(EmployeeManagerService.class);
    }

    public EmployeeManagerService getService() {
        return service;
    }
}
