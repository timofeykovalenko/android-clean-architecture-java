package com.kiparo.presentation.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseViewModel<R extends BaseRouter> extends ViewModel {

    //FIXME move to better place
    public static final long PROGRESS_DELAY_MS = 300L;

    private CompositeDisposable compositeDisposable = null;
    private Disposable progressDelayDisposable = null;

    @Nullable
    protected R router;

    public ObservableBoolean progressBar = new ObservableBoolean(false);

    public void addRouter(R router) {
        this.router = router;
    }

    public void removeRouter() {
        router = null;
    }

    public void showProgress() {
        progressBar.set(true);
    }

    public void showProgressWithDelay() {
        progressDelayDisposable =
                Observable.timer(PROGRESS_DELAY_MS, TimeUnit.MILLISECONDS,
                        Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .take(1)
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                progressBar.set(true);
                            }
                        });
    }

    public void dismissProgress() {

        if(progressDelayDisposable != null) {
            progressDelayDisposable.dispose();
            progressDelayDisposable = null;
        }

        progressBar.set(false);
    }

    //try to reduce the use of this method in ViewModel as much as possible
    public void onResume() {
    }

    //try to reduce the use of this method in ViewModel as much as possible
    public void onPause() {
    }

    //try to reduce the use of this method in ViewModel as much as possible
    public void onStart() {
    }

    //try to reduce the use of this method in ViewModel as much as possible
    public void onStop() {
    }

    public CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        return compositeDisposable;
    }

    protected void addToCompositeDisposable(Disposable disposable) {
        getCompositeDisposable().add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
