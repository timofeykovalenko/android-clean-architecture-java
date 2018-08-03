package com.kiparo.domain.repositories;

import com.kiparo.domain.entity.Repos;
import com.kiparo.domain.entity.ReposParam;

import java.util.List;

import io.reactivex.Flowable;

public interface ReposRepository {

    Flowable<List<Repos>> all(ReposParam param);

    // TODO It is just for example what can be done in Repository. All CRUD - storage operations for one Entity
    //    Completable update(Entity user);
    //    Completable delete(Entity id);
    //    Completable add(Entity user);
    //    Observable<List<Entity>> search(SearchParam search);
}
