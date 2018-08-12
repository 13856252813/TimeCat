package com.cary.activity.timecat.http.base;

import android.util.Log;

import com.cary.activity.timecat.util.LogUtils;

import java.net.URLDecoder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cary on 2018/4/6.
 */

public class HttpUrlClient {
    private static final String TAG = HttpUrlClient.class.getSimpleName();

    public static String BASEURL = "http://39.106.187.244:8080/time-cat-api/";

    public static String ALIYUNPHOTOBASEURL = "http://timecats-yunpan.oss-cn-hangzhou.aliyuncs.com";
    public static String YUNPHOTOBASEURL ="http://oss-cn-hangzhou.aliyuncs.com/";

    public static String PAYORDERSUCCESS = "http://m.baidu.com";

    // set your desired log level
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }


    public static String toURLDecoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            LogUtils.v("toURLDecoded error:"+paramString);
            return "";
        }

        try
        {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLDecoder.decode(str, "UTF-8");
            return str;
        }
        catch (Exception localException)
        {
            LogUtils.v("toURLDecoded error:"+paramString+localException);
        }

        return "";
    }
    public static OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG,"OkHttp====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }
}
