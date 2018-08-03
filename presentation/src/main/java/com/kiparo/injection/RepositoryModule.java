package com.kiparo.injection;

import android.content.Context;

import com.kiparo.domain.executors.PostExecutionThread;
import com.kiparo.domain.repositories.ReposRepository;
import com.kiparo.data.repositories.ReposRepositoryImpl;
import com.kiparo.executor.UIThread;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    abstract ReposRepository provideVitaldataRepository(ReposRepositoryImpl repository);

}
