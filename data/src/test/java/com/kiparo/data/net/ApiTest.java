package com.kiparo.data.net;

import com.kiparo.data.entity.ReposResponse;
import com.kiparo.domain.entity.AppError;
import com.kiparo.domain.entity.AppErrorType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Predicate;
import io.reactivex.subscribers.TestSubscriber;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.assertEquals;

public class ApiTest {

    private static final String BASE_URL = "/";
    private static final int OKHTTP_TIMEOUT_SECOND = 1;

    private ApiManager apiManager;

    private MockWebServer mockServer;

    private HttpUrl url;

    @Before
    public void setUp() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start();
        url = mockServer.url(BASE_URL);
        apiManager = new ApiManager(NetFactories.createApiWithTimeout(url.toString(),
                OKHTTP_TIMEOUT_SECOND));
    }

    @After
    public void release() throws IOException {
        mockServer.shutdown();
    }

    @Test
    public void testError404Reponse() throws Exception {

        MockResponse mockedResponse = new MockResponse();
        mockedResponse.setResponseCode(404);
        mockedResponse.setBody(JsonFile.getJson(JsonFile.ERROR_404_NOT_FOUND_BODY));

        mockServer.enqueue(mockedResponse);

        TestSubscriber<List<ReposResponse>> subscriber = new TestSubscriber<>();

        apiManager
                .getReposByUsername("test name")
                .subscribe(subscriber);

        subscriber.assertError(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {

                if (throwable instanceof AppError) {
                    AppError appError = (AppError) throwable;
                    if (appError.getType() == AppErrorType.SERVER_NORMAL_ERROR
                            && appError.getMessageForUser() != null
                            && !appError.getMessageForUser().isEmpty()) {
                        return true;
                    }
                }

                return false;
            }
        });

        subscriber.dispose();
    }

    @Test
    public void testErrorTimeoutsReponse() throws Exception {

        TestSubscriber<List<ReposResponse>> subscriber = new TestSubscriber<>();

        apiManager
                .getReposByUsername("test name")
                .subscribe(subscriber);
        subscriber.awaitTerminalEvent(OKHTTP_TIMEOUT_SECOND + 1, TimeUnit.SECONDS);

        subscriber.assertError(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {

                if (throwable instanceof AppError) {
                    AppError appError = (AppError) throwable;

                    System.out.println(appError.toString());

                    if (appError.getType() == AppErrorType.SERVER_IS_NOT_AVAILABLE) {
                        return true;
                    }
                }

                return false;
            }
        });

        subscriber.dispose();
    }

    @Test
    public void testNormalReponse() throws Exception {

        MockResponse mockedResponse = new MockResponse();
        mockedResponse.setResponseCode(200);
        mockedResponse.setBody(JsonFile.getJson(JsonFile.NORMAL_200_REPOS_BY_USERNAME_BODY));

        mockServer.enqueue(mockedResponse);

        TestSubscriber<List<ReposResponse>> subscriber = new TestSubscriber<>();

        apiManager
                .getReposByUsername("test name")
                .subscribe(subscriber);

        subscriber.assertNoErrors();

        subscriber.assertValue(new Predicate<List<ReposResponse>>() {
            @Override
            public boolean test(List<ReposResponse> reposResponses) throws Exception {

                if(reposResponses.size() == 1) {
                    ReposResponse reposResponse = reposResponses.get(0);
                    if(reposResponse.getId().equals("143269609") && reposResponse.getName().equals("android-clean-architecture-java")
                            && reposResponse.getFullName().equals("timofeykovalenko/android-clean-architecture-java")) {
                        return true;
                    }
                }

                return false;
            }
        });

        subscriber.dispose();
    }
}
