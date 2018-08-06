package com.kiparo.domain.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repos repos = (Repos) o;
        return Objects.equals(id, repos.id) &&
                Objects.equals(name, repos.name) &&
                Objects.equals(fullName, repos.fullName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, fullName);
    }
}
