package com.cary.activity.timecat.manager.oss.put;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class OSSPutApi {
    public static OSSPutApi getApi(){
        return SingleHolder.regApi;
    }

    private static class SingleHolder{
        public static OSSPutApi regApi = new OSSPutApi();
    }

    private OSSPutService service;

    private OSSPutApi(){
        String  httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(OSSPutService.class);
    }

    public OSSPutService getService(){
        return service;
    }
}
