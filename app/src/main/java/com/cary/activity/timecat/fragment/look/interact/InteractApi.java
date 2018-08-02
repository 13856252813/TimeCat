package com.cary.activity.timecat.fragment.look.interact;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class InteractApi {
    public static InteractApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static InteractApi regApi = new InteractApi();
    }

    private InteractService service;

    private InteractApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(InteractService.class);
    }

    public InteractService getService() {
        return service;
    }
}
