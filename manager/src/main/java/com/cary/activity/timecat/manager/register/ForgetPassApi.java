package com.cary.activity.timecat.manager.register;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class ForgetPassApi {
    public static ForgetPassApi getApi(){
        return SingleHolder.regApi;
    }

    private static class SingleHolder{
        public static ForgetPassApi regApi = new ForgetPassApi();
    }

    private ForgetPassService service;

    private ForgetPassApi(){
        String  httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(ForgetPassService.class);
    }

    public ForgetPassService getService(){
        return service;
    }
}
