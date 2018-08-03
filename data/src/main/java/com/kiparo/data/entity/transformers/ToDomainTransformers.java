package com.kiparo.data.entity.transformers;

import com.kiparo.domain.entity.Repos;
import com.kiparo.data.entity.ReposResponse;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;

public class ToDomainTransformers {

    public static FlowableTransformer<List<ReposResponse>, List<Repos>> map() {

        return new FlowableTransformer<List<ReposResponse>, List<Repos>>() {
            @Override
            public Publisher<List<Repos>> apply(Flowable<List<ReposResponse>> upstream) {
                return upstream
                        .map(new Function<List<ReposResponse>, List<Repos>>() {
                            @Override
                            public List<Repos> apply(List<ReposResponse> dataList) throws Exception {

                                final List<Repos> domainlist = new ArrayList<>();
                                for (ReposResponse repos : dataList) {
                                    domainlist.add(new Repos(
                                            repos.getId(),
                                            repos.getName(),
                                            repos.getFullName()));
                                }

                                return domainlist;
                            }
                        });
            }
        };
    }
}
