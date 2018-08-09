package com.kiparo.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kiparo.data.BuildConfig;
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
public class ApiManager {

    private ApiContract apiContractImpl;

    @Inject
    public ApiManager(ApiContract apiContractImpl) {
        this.apiContractImpl = apiContractImpl;
    }

    public Flowable<List<ReposResponse>> getReposByUsername(String username) {
        return apiContractImpl
                .getReposByUsername(username)
                .compose(NetTransformers.<List<ReposResponse>>parseHttpError());
    }
}
