package com.cary.activity.timecat.manager.register;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class SendSmsCodeApi {
    public static SendSmsCodeApi getApi(){
        return SingleHolder.regApi;
    }

    private static class SingleHolder{
        public static SendSmsCodeApi regApi = new SendSmsCodeApi();
    }

    private SendSmsCodeService service;

    private SendSmsCodeApi(){
        String  httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(SendSmsCodeService.class);
    }

    public SendSmsCodeService getService(){
        return service;
    }
}
