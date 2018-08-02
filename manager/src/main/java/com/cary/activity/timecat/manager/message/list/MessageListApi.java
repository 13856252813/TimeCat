package com.cary.activity.timecat.manager.message.list;


import com.cary.activity.timecat.manager.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageListApi {
    public static MessageListApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static MessageListApi regApi = new MessageListApi();
    }

    private MessageListService service;

    private MessageListApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(MessageListService.class);
    }

    public MessageListService getService() {
        return service;
    }
}
