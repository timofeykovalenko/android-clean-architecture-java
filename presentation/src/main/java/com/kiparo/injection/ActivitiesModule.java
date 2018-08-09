package com.kiparo.injection;

import com.kiparo.presentation.screeens.home.HomeActivity;
import com.kiparo.presentation.screeens.home.HomeModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = { HomeModule.class })
    abstract HomeActivity contributeHomeActivity();
}
