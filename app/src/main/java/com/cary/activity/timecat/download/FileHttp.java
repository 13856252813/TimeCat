package com.cary.activity.timecat.download;

import com.cary.activity.timecat.http.base.HttpResultFunc;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @see  文件相关的HTTP请求
 * Created by Cary on 2018/3/29.
 */

public class FileHttp {
    private FileApi fileApi;

    public static FileHttp getInstance() {
        return InstanceHolder.instance;
    }

    private FileHttp() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://retrofit.devwiki.net")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        fileApi = retrofit.create(FileApi.class);
    }

    private static class InstanceHolder {
        private static FileHttp instance = new FileHttp();
    }

    public void getFileUrl(Subscriber<String> subscriber) {
        fileApi.getFileUrl()
                .map(new HttpResultFunc<String>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
