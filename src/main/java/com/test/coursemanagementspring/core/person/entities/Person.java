package com.test.coursemanagementspring.core.person.entities;

import java.io.Serializable;
import java.util.List;

public abstract class Person implements Serializable {
    private int id;

    private String name;

    Person(String name) {
        this.name = name;
    }

    Person(int id, String name) {
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

    abstract public List<String> getPermissions();
}
