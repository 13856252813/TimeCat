package com.cary.activity.timecat.manager.oss;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class OSSCredentialsApi {
    public static OSSCredentialsApi getApi(){
        return SingleHolder.regApi;
    }

    private static class SingleHolder{
        public static OSSCredentialsApi regApi = new OSSCredentialsApi();
    }

    private OSSCredentialsService service;

    private OSSCredentialsApi(){
        String  httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(OSSCredentialsService.class);
    }

    public OSSCredentialsService getService(){
        return service;
    }
}
