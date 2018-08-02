package com.cary.activity.timecat.reglogin;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class LoginEaseApi {
    public static LoginEaseApi getApi(){
        return SingleHolder.regApi;
    }

    private static class SingleHolder{
        public static LoginEaseApi regApi = new LoginEaseApi();
    }

    private LoginEaseService service;

    private LoginEaseApi(){
        String  httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(LoginEaseService.class);
    }

    public LoginEaseService getService(){
        return service;
    }
}
