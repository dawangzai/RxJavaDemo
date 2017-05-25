package com.wangzai.rxjavademo.http;

import android.util.Log;

import com.wangzai.rxjavademo.http.api.PhotoService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangzai on 2017/5/25.
 */

public class HttpClient {
    private static HttpClient mClient;

    private static final int DEFAULT_TIMEOUT = 5;

    private static Retrofit mRetrofit;
    private static PhotoService mPhotoService;

    public static HttpClient getInstance() {
        if (mClient == null) {
            mClient = new HttpClient();
        }
        return mClient;
    }

    public PhotoService getPhotoService() {
        return mPhotoService == null ? configRetrofit(PhotoService.class, true) : mPhotoService;
    }

    private <T> T configRetrofit(Class<T> service, boolean isGetToken) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .client(configClient(true))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return mRetrofit.create(service);
    }

    private OkHttpClient configClient(final boolean isGetToken) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        //为所有请求添加头部 Header 配置的拦截器
        Interceptor headerIntercept = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
//                b05bfc46a0de4842346cb5ce7c766b3a8c9da071ec77f3b5f719406829c2fb31
                builder.addHeader("Authorization", "Client-ID " + "b05bfc46a0de4842346cb5ce7c766b3a8c9da071ec77f3b5f719406829c2fb31");

                Request request = builder.build();

                return chain.proceed(request);
            }
        };

        // Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("test", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        okHttpClient.addInterceptor(loggingInterceptor);

        okHttpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.addNetworkInterceptor(headerIntercept);

        return okHttpClient.build();
    }
}
