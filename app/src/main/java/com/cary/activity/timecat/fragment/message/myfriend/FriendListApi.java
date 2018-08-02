package com.cary.activity.timecat.fragment.message.myfriend;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class FriendListApi {
    public static FriendListApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static FriendListApi regApi = new FriendListApi();
    }

    private FriendListService service;

    private FriendListApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(FriendListService.class);
    }

    public FriendListService getService() {
        return service;
    }
}
