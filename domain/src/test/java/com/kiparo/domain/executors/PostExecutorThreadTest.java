package com.kiparo.domain.executors;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

public class PostExecutorThreadTest implements PostExecutorThread {

    private TestScheduler testScheduler;

    public PostExecutorThreadTest(TestScheduler testScheduler) {
        this.testScheduler = testScheduler;
    }

    @Override
    public Scheduler getScheduler() {
        return testScheduler;
    }
}
