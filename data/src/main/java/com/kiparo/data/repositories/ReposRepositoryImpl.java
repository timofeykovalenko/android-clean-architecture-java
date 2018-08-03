package com.kiparo.data.repositories;

import com.kiparo.domain.entity.Repos;
import com.kiparo.domain.entity.ReposParam;
import com.kiparo.data.entity.ReposResponse;
import com.kiparo.data.entity.transformers.ToDomainTransformers;
import com.kiparo.data.net.Api;
import com.kiparo.domain.entity.AppError;
import com.kiparo.domain.entity.AppErrorType;
import com.kiparo.domain.repositories.ReposRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class ReposRepositoryImpl implements ReposRepository {

    private Api api;

    @Inject
    public ReposRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Flowable<List<Repos>> all(ReposParam param) {
        return api
                .getReposByUsername(param.getUsername())
                .compose(ToDomainTransformers.map());
    }
}
