package com.kiparo.presentation.utils;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

public class RxTransformers {

    //FIXME move to better place
    // for clicks
    public static final long DEBOUNCE_DELAY_MS = 500L;

    public static <T> ObservableTransformer<T, T> applyThrottlingFirstObservable() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.throttleFirst(DEBOUNCE_DELAY_MS, TimeUnit.MILLISECONDS);
            }
        };
    }

    public static <T> FlowableTransformer<T, T> applyThrottlingFirstFlowable() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.throttleFirst(DEBOUNCE_DELAY_MS, TimeUnit.MILLISECONDS);            }
        };
    }

    public static <T> ObservableTransformer<T, T> applyThrottlingLastObservable() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.throttleLast(DEBOUNCE_DELAY_MS, TimeUnit.MILLISECONDS);
            }
        };
    }

    public static <T> FlowableTransformer<T, T> applyThrottlingLastFlowable() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.throttleLast(DEBOUNCE_DELAY_MS, TimeUnit.MILLISECONDS);            }
        };
    }
}
