package com.kiparo.injection;

import com.kiparo.domain.repositories.ReposRepository;
import com.kiparo.data.repositories.ReposRepositoryImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    abstract ReposRepository provideVitaldataRepository(ReposRepositoryImpl repository);

}
