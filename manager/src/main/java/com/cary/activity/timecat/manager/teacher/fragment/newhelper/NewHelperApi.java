package com.cary.activity.timecat.manager.teacher.fragment.newhelper;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class NewHelperApi {
    public static NewHelperApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static NewHelperApi regApi = new NewHelperApi();
    }

    private NewHelperService service;

    private NewHelperApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(NewHelperService.class);
    }

    public NewHelperService getService() {
        return service;
    }
}
