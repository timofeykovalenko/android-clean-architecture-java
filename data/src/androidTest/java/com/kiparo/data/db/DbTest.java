package com.kiparo.data.db;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.kiparo.data.db.dao.ReposDAO;
import com.kiparo.data.entity.ReposResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.subscribers.TestSubscriber;

@RunWith(AndroidJUnit4.class)
public class DbTest {

    private ReposDAO reposDao;
    private AppDatabase database;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        database = AppDatabase.getInstanceInMemory(context);
        reposDao = database.reposDao();
    }

    @After
    public void closeDb() throws IOException {
        database.close();
    }

    @Test
    public void writeRepoAndReadInList() throws Exception {

        ReposResponse repos = new ReposResponse();
        repos.setId("1");
        repos.setName("Test name");
        repos.setFullName("Test full name");

        List<ReposResponse> list = new ArrayList<>();
        list.add(repos);
        reposDao.insert(list);

        TestSubscriber<List<ReposResponse>> subscriber = new TestSubscriber<>();

        reposDao
                .get()
                .subscribe(subscriber);

        subscriber.assertNoErrors();
    }
}