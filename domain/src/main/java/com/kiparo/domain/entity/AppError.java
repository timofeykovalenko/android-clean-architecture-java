package com.kiparo.domain.entity;

import java.util.Objects;

public class AppError extends Throwable implements DomainModel {

    private AppErrorType type;
    private String messageForUser;

    public AppError(String message, AppErrorType type) {
        this.type = type;
        this.messageForUser = messageForUser;
    }

    public AppError(String message, String messageForUser, AppErrorType type) {
        super(message);
        this.type = type;
        this.messageForUser = messageForUser;
    }

    public AppErrorType getType() {
        return type;
    }

    public String getMessageForUser() {
        return messageForUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppError appError = (AppError) o;
        return type == appError.type &&
                Objects.equals(messageForUser, appError.messageForUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, messageForUser);
    }

    @Override
    public String toString() {
        return "AppError{" +
                "type=" + type +
                ", messageForUser='" + messageForUser + '\'' +
                '}';
    }
}
