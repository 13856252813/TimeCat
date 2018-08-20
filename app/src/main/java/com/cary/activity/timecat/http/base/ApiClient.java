package com.cary.activity.timecat.http.base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static ApiClient getApi() {
        return ApiClient.SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static ApiClient regApi = new ApiClient();
    }

    private ApiService service;

    private ApiClient() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return service;
    }
}
