package com.kiparo.data.entity;

import com.google.gson.annotations.SerializedName;

public class HttpError extends Exception implements DataModel {

    @SerializedName("message")
    private String message;

    @SerializedName("documentation_url")
    private String documentationUrl;

    @Override
    public String getMessage() {
        return message;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }
}
