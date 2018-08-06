package com.kiparo.domain.usecases;

import com.kiparo.domain.executors.WorkExecutorThread;
import com.kiparo.domain.executors.PostExecutorThread;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseUseCase {

    protected Scheduler executionThread;
    protected Scheduler postExecutionThread;

    public BaseUseCase(Scheduler executionThread, Scheduler postExecutionThread) {
        this.executionThread = executionThread;
        this.postExecutionThread = postExecutionThread;
    }

    public BaseUseCase(WorkExecutorThread executionThread, PostExecutorThread postExecutionThread) {
        this.executionThread = Schedulers.from(executionThread);
        this.postExecutionThread = postExecutionThread.getScheduler();
    }

    public BaseUseCase(PostExecutorThread postExecutionThread) {
        this.executionThread = Schedulers.io();
        this.postExecutionThread = postExecutionThread.getScheduler();
    }

    public void setExecutionThread(Scheduler executionThread) {
        this.executionThread = executionThread;
    }

    public void setPostExecutionThread(Scheduler postExecutionThread) {
        this.postExecutionThread = postExecutionThread;
    }
}
