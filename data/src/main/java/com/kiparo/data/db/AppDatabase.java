package com.kiparo.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.kiparo.data.db.dao.ReposDAO;
import com.kiparo.data.entity.ReposResponse;

import java.io.File;

@Database(entities = {ReposResponse.class},
        version = 1,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "databasename";

    private static AppDatabase database = null;

    @NonNull
    public static AppDatabase getInstance(Context context) {
        if (database == null) {
            database = create(context);
        }

        return database;
    }

    @NonNull
    public static AppDatabase getInstanceInMemory(Context context) {
        return Room.inMemoryDatabaseBuilder(context,
                AppDatabase.class)
                .build();
    }

    @NonNull
    private static AppDatabase create(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    public static boolean removeDataBase(Context context) {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if (dbFile.exists()) {
            return dbFile.delete();
        } else {
            return false;
        }
    }

    public abstract ReposDAO reposDao();
}
