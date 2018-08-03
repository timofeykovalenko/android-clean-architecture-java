package com.kiparo.data.entity;

import com.google.gson.annotations.SerializedName;

public class ReposResponse implements DataModel {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String fullName;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }
}
