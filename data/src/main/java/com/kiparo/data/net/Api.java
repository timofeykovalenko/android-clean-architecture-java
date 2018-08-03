package com.kiparo.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kiparo.data.entity.ReposResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class Api {

    private ApiContract restApi;
    private Gson gson;
    private NetTransformers netTransformers;

    @Inject
    public Api() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttp = new OkHttpClient
                .Builder()
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        gson = new GsonBuilder()
                .create();

        this.restApi = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com/")
                .client(okHttp)
                .build()
                .create(ApiContract.class);

        netTransformers = new NetTransformers(gson);
    }

    public Flowable<List<ReposResponse>> getReposByUsername(String username) {
        return restApi
                .getReposByUsername(username)
                .compose(netTransformers.<List<ReposResponse>>parseHttpError());
    }
}
