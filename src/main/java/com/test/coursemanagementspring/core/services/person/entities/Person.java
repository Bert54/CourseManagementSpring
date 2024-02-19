package com.test.coursemanagementspring.core.services.person.entities;

import java.io.Serializable;
import java.util.List;

public abstract class Person implements Serializable {
    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 100;

    private int id;
    private String name;

    protected Person(String name) {
        this.name = name;
    }

    protected Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract public PersonType getType();

    abstract public List<String> getPermissions();
}
