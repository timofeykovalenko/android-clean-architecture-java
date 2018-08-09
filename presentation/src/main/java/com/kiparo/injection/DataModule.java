package com.kiparo.injection;

import android.content.Context;

import com.kiparo.data.db.AppDatabase;
import com.kiparo.data.db.dao.ReposDAO;
import com.kiparo.data.net.ApiContract;
import com.kiparo.data.net.NetFactories;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    public ApiContract provideApiContractImpl() {
        //FIXME move url to gradle config
        return NetFactories.createApi("https://api.github.com/");
    }

    @Provides
    @Singleton
    public static AppDatabase provideAppDatabase(Context context) {
        return AppDatabase.getInstance(context);
    }

    @Provides
    public static ReposDAO provideReposDao(AppDatabase appDatabase) {
        return appDatabase.reposDao();
    }
}
