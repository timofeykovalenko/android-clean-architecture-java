package com.kiparo.domain.usecases;

import com.kiparo.domain.entity.Repos;
import com.kiparo.domain.entity.ReposParam;
import com.kiparo.domain.executors.PostExecutorThreadTest;
import com.kiparo.domain.repositories.ReposRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetReposUseCaseTest {

    @Mock
    private ReposRepository reposRepository;

    private GetReposUseCase reposUseCase;

    private TestScheduler testScheduler;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        testScheduler = new TestScheduler();
        reposUseCase = new GetReposUseCase(reposRepository,
                new PostExecutorThreadTest(testScheduler));
        reposUseCase.setExecutionThread(testScheduler);
    }

    @Test
    public void testPositiveOnValidData() {

        ReposParam param = new ReposParam("testName");

        List<Repos> listData = new ArrayList<>();
        listData.add(new Repos("1", "repos test name", "repos test full name"));
        listData.add(new Repos("2", "repos test name 2", "repos test full name 2"));

        when(reposRepository.all(param)).thenReturn(Flowable.just(listData));

        TestSubscriber<List<Repos>> subscriber = new TestSubscriber<>();

        reposUseCase
                .get(param)
                .subscribe(subscriber);

        testScheduler.triggerActions();

        subscriber.assertValueCount(1);
        subscriber.assertSubscribed();
        subscriber.assertValue(listData);

        subscriber.dispose();
    }
}
