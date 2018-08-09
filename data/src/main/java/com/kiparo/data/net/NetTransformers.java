package com.kiparo.data.net;

import com.google.gson.Gson;
import com.kiparo.data.entity.HttpError;
import com.kiparo.domain.entity.AppError;
import com.kiparo.domain.entity.AppErrorType;

import org.reactivestreams.Publisher;

import java.net.SocketTimeoutException;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

class NetTransformers<S> {

    public static <Entity> FlowableTransformer<Entity, Entity> parseHttpError() {

        return new FlowableTransformer<Entity, Entity>() {
            @Override
            public Publisher<Entity> apply(Flowable<Entity> upstream) {

                return upstream
                        .onErrorResumeNext(new Function<Throwable, Flowable<Entity>>() {
                            @Override
                            public Flowable<Entity> apply(Throwable throwable) throws Exception {

                                //finaly we should have AppError entity with all info about error
                                AppError error;

                                if (throwable instanceof HttpException) {
                                    HttpException httpException = (HttpException) throwable;

                                    ResponseBody responseBody = httpException.response().errorBody();
                                    if(responseBody != null) {
                                        Gson gson = new Gson();
                                        HttpError httpError = gson.fromJson(responseBody.string(), HttpError.class);

                                        error = new AppError(throwable.getMessage(),
                                                httpError.getMessage(), AppErrorType.SERVER_NORMAL_ERROR);
                                    } else {
                                        error = new AppError("server error body is empty and " + throwable.getMessage(),
                                                AppErrorType.UNEXPECTED_ERROR);
                                    }
                                } else if (throwable instanceof SocketTimeoutException) {
                                    error = new AppError("Server is not available and " + throwable.getMessage(),
                                            AppErrorType.SERVER_IS_NOT_AVAILABLE);
                                } else {
                                    error = new AppError("Unexpected error and " + throwable.getMessage(),
                                            AppErrorType.UNEXPECTED_ERROR);
                                }

                                return Flowable.error(error);
                            }
                        });
            }
        };
    }
}
