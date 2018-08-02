package com.cary.activity.timecat.manager.login;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class LoginApi {
    public static LoginApi getApi(){
        return SingleHolder.regApi;
    }

    private static class SingleHolder{
        public static LoginApi regApi = new LoginApi();
    }

    private LoginService service;

    private LoginApi(){
        String  httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(LoginService.class);
    }

    public LoginService getService(){
        return service;
    }
}
