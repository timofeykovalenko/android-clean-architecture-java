package com.kiparo.domain.entity;

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
}
