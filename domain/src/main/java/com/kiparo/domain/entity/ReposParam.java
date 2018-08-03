package com.kiparo.domain.entity;

public class ReposParam implements DomainModel {

    private String username;

    public ReposParam(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
