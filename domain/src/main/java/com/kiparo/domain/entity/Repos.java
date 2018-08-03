package com.kiparo.domain.entity;

public class Repos implements DomainModel {

    private String id;
    private String name;
    private String fullName;

    public Repos(String id, String name, String fullName) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
    }

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
