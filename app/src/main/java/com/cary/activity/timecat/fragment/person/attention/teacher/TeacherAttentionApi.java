package com.cary.activity.timecat.fragment.person.attention.teacher;

import com.cary.activity.timecat.http.base.HttpUrlClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class TeacherAttentionApi {
    public static TeacherAttentionApi getApi() {
        return SingleHolder.regApi;
    }

    private static class SingleHolder {
        public static TeacherAttentionApi regApi = new TeacherAttentionApi();
    }

    private TeacherAttentionService service;

    private TeacherAttentionApi() {
        String httpurl = HttpUrlClient.toURLDecoded(HttpUrlClient.BASEURL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpUrlClient.getOkHttpClient())//使用自己创建的OkHttp
                .build();
        service = retrofit.create(TeacherAttentionService.class);
    }

    public TeacherAttentionService getService() {
        return service;
    }
}
