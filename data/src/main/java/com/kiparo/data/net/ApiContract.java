package com.kiparo.data.net;

import com.kiparo.data.entity.ReposResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiContract {

    @GET("users/{user}/repos")
    Flowable<List<ReposResponse>> getReposByUsername(@Path("user")  String username);

}
