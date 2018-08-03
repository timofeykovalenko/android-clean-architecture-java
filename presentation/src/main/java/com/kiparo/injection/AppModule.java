package com.kiparo.injection;

import android.content.Context;

import com.kiparo.app.App;
import com.kiparo.domain.executors.PostExecutionThread;
import com.kiparo.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = { AndroidSupportInjectionModule.class })
public class AppModule {

    @Provides
    @Singleton
    public Context provideContext(App app) {
        return app.getApplicationContext();
    }

    @Singleton
    @Provides
    public static PostExecutionThread provideUIThread(UIThread uiThread) {
        return uiThread;
    }
}
