package com.kiparo.domain.usecases;

import com.kiparo.domain.entity.Repos;
import com.kiparo.domain.entity.ReposParam;
import com.kiparo.domain.executors.PostExecutorThread;
import com.kiparo.domain.repositories.ReposRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;

public class GetReposUseCase extends BaseUseCase {

    private ReposRepository reposRepository;

    @Inject
    public GetReposUseCase(ReposRepository userRepository, PostExecutorThread postExecutionThread) {
        super(postExecutionThread);
        this.reposRepository = userRepository;
    }

    public Flowable<List<Repos>> get(ReposParam param) {
        return reposRepository
                .all(param)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
