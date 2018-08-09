package com.kiparo.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kiparo.data.entity.ReposResponse;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @see com.kiparo.data.entity.ReposResponse
 */
@Dao
public interface ReposDAO {

    @Insert
    public void insert(List<ReposResponse> value);

    @Query("SELECT * FROM " + ReposResponse.TABLE_NAME)
    public Flowable<List<ReposResponse>> get();

    @Query("DELETE FROM " + ReposResponse.TABLE_NAME)
    public void deleteAll();
}
