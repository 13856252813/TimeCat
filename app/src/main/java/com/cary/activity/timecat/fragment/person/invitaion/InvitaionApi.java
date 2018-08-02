package com.cary.activity.timecat.fragment.person.invitaion;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class InvitaionApi {
    public static InvitaionApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static InvitaionApi regApi = new InvitaionApi();
    }

    private InvitaionService service;

    private InvitaionApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(InvitaionService.class);
    }

    public InvitaionService getService() {
        return service;
    }
}
