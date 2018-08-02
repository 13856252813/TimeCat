package com.cary.activity.timecat.fragment.person.attention.meal;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class MealAttentionApi {
    public static MealAttentionApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static MealAttentionApi regApi = new MealAttentionApi();
    }

    private MealAttentionService service;

    private MealAttentionApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(MealAttentionService.class);
    }

    public MealAttentionService getService() {
        return service;
    }
}
