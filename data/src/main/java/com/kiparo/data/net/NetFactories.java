package com.kiparo.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kiparo.data.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetFactories {

    private static final int TIMEOUT_SECOND = 60;

    public static Gson createGson() {
        return new GsonBuilder()
                .create();
    }

    public static OkHttpClient createOkHttpClient(int timeout) {

        OkHttpClient.Builder okHttp = new OkHttpClient
                .Builder()
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .connectTimeout(timeout, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttp.addInterceptor(logging);
        }

        return okHttp.build();
    }

    public static ApiContract createApi(String baseUrl) {
        return createApi(createOkHttpClient(TIMEOUT_SECOND), createGson(), baseUrl);
    }

    public static ApiContract createApiWithTimeout(String baseUrl, int timeoutInSeconds) {
        return createApi(createOkHttpClient(timeoutInSeconds), createGson(), baseUrl);
    }

    public static ApiContract createApi(OkHttpClient okHttpClient, Gson gson, String baseUrl) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
                .create(ApiContract.class);
    }
}
