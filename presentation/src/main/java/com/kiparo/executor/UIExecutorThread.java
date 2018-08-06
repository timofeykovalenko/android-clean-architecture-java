package com.kiparo.executor;

import com.kiparo.domain.executors.PostExecutorThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Singleton
public class UIExecutorThread implements PostExecutorThread {

    @Inject
    public UIExecutorThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
