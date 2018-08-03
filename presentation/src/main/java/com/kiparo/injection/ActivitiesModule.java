package com.kiparo.injection;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.kiparo.presentation.screeens.home.HomeActivity;
import com.kiparo.presentation.screeens.home.HomeModule;
import com.kiparo.presentation.utils.CustomViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = { HomeModule.class })
    abstract HomeActivity contributeHomeActivity();
}
