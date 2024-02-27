package com.test.coursemanagementspring.core.services.person.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.test.coursemanagementspring.core.services.classs.entities.Class;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Person implements Serializable {
    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 100;

    private int id;
    private String name;
    private List<Class> classes;

    protected Person(String name) {
        this.name = name;
    }

    protected Person(int id, String name) {
        this.id = id;
        this.name = name;
        this.classes = new ArrayList<>();
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

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    public List<Class> getClasses() {
        return Collections.unmodifiableList(this.classes);
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public void addClass(Class cls) {
        this.classes.add(cls);
    }

    abstract public PersonType getType();

    @JsonIgnore
    abstract public List<String> getPermissions();
}
