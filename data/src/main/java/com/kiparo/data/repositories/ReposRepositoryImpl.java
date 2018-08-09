package com.kiparo.data.repositories;

import com.kiparo.data.db.AppDatabase;
import com.kiparo.data.db.dao.ReposDAO;
import com.kiparo.data.entity.ReposResponse;
import com.kiparo.data.net.ApiManager;
import com.kiparo.domain.entity.AppError;
import com.kiparo.domain.entity.Repos;
import com.kiparo.domain.entity.ReposParam;
import com.kiparo.data.entity.transformers.ToDomainTransformers;
import com.kiparo.domain.repositories.ReposRepository;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ReposRepositoryImpl implements ReposRepository {

    private ApiManager api;
    private ReposDAO reposDAO;

    @Inject
    public ReposRepositoryImpl(ApiManager api, ReposDAO reposDAO) {
        this.api = api;
        this.reposDAO = reposDAO;
    }

    @Override
    public Flowable<List<Repos>> all(ReposParam param) {

        //Just an example, you can implement your own logic with cheking errors,
        //checking for the latest update time etc.
        //Also I recommend do not do a lot of nesting calls, use compose() with Transformers or another method.

        return api
                .getReposByUsername(param.getUsername())
                .doOnNext(new Consumer<List<ReposResponse>>() {
                    @Override
                    public void accept(List<ReposResponse> reposResponses) throws Exception {
                        reposDAO.insert(reposResponses);
                    }
                })
                .onErrorResumeNext(new Function<Throwable, Publisher<? extends List<ReposResponse>>>() {
                    @Override
                    public Publisher<? extends List<ReposResponse>> apply(final Throwable throwable) throws Exception {
                        return reposDAO
                                .get()
                                .flatMap(new Function<List<ReposResponse>, Publisher<List<ReposResponse>>>() {
                                    @Override
                                    public Publisher<List<ReposResponse>> apply(List<ReposResponse> reposResponses) throws Exception {
                                        if(reposResponses.isEmpty()) {
                                            return Flowable.error(throwable);
                                        } else {
                                            return Flowable.just(reposResponses);
                                        }
                                    }
                                });
                    }
                })
                .compose(ToDomainTransformers.map());
    }
}
