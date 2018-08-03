package com.kiparo.presentation.screeens.home;

import android.databinding.ObservableField;

import com.jakewharton.rxrelay2.PublishRelay;
import com.kiparo.domain.entity.Constant;
import com.kiparo.domain.entity.Repos;
import com.kiparo.domain.entity.ReposParam;
import com.kiparo.domain.usecases.GetReposUseCase;
import com.kiparo.presentation.base.BaseViewModel;
import com.kiparo.presentation.utils.RxTransformers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class HomeViewModel extends BaseViewModel<HomeRouter> {

    private GetReposUseCase useCase;

    //Visible in xml
    public ObservableField<String> username = new ObservableField<>(Constant.EMPTY);
    public PublishRelay<Boolean> loadReposClick = PublishRelay.create();

    {
        Disposable d =
                loadReposClick
                        .compose(RxTransformers.<Boolean>applyThrottlingFirstObservable())
                        .filter(new Predicate<Boolean>() {
                            @Override
                            public boolean test(Boolean aBoolean) throws Exception {
                                return !username.get().isEmpty();
                            }
                        })
                        .map(new Function<Boolean, ReposParam>() {
                            @Override
                            public ReposParam apply(Boolean aBoolean) throws Exception {
                                return new ReposParam(username.get());
                            }
                        })
                        .doOnNext(new Consumer<ReposParam>() {
                            @Override
                            public void accept(ReposParam param) throws Exception {
                                router.hideKeyboard();
                            }
                        })
                        .subscribe(new Consumer<ReposParam>() {
                            @Override
                            public void accept(ReposParam param) throws Exception {
                                loadRepos(param);
                            }
                        });
        addToCompositeDisposable(d);
    }

    @Inject
    public HomeViewModel(GetReposUseCase useCase) {
        this.useCase = useCase;
    }

    private void loadRepos(ReposParam param) {

        showProgress();

        Disposable d = useCase
                .get(param)
                .subscribe(new Consumer<List<Repos>>() {
                    @Override
                    public void accept(List<Repos> repos) throws Exception {
                        dismissProgress();
                        router.showToast("Loaded " + repos.size() + " repos");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dismissProgress();
                        router.showError(throwable);
                    }
                });

        addToCompositeDisposable(d);
    }
}


