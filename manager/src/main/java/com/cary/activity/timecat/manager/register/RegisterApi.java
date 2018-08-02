package com.cary.activity.timecat.manager.register;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;
import com.cary.activity.timecat.manager.util.LogUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 * 注册
 */

public class RegisterApi {
    public static RegisterApi getApi(){
        return SingleHolder.regApi;
    }

    private static class SingleHolder{
        public static RegisterApi regApi = new RegisterApi();
    }

    private RegisterService service;

    private RegisterApi(){
        String  httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        LogUtils.v("httpurl:"+httpurl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(RegisterService.class);
    }

    public RegisterService getService(){
        return service;
    }
}
