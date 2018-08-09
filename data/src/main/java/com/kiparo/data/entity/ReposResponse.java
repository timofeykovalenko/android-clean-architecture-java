package com.kiparo.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * @see com.kiparo.data.db.dao.ReposDAO
 */
//TODO For real project, I recommend using a different class(entity) for the database and
//TODO map from this entity to database entity to store data. Yes, you will have similar classes and
//TODO a few extra lines of code but the code will be independent and well tested.
@Entity(tableName = ReposResponse.TABLE_NAME)
public class ReposResponse implements DataModel {

    public static final String TABLE_NAME = "ReposDbEntity";

    @SerializedName("id")
    @PrimaryKey
    @NonNull
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String fullName;

    public static String getTableName() {
        return TABLE_NAME;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
