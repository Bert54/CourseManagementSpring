package com.test.coursemanagementspring.core.services.classs.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.coursemanagementspring.core.services.person.entities.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Class {
    public static final int NAME_MIN_LENGTH = 1;
    public static final int NAME_MAX_LENGTH = 30;
    private int id;
    private String name;
    private List<Person> members;

    public Class(String name) {
        this.name = name;
    }

    public Class(int id, String name) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
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
    public List<Person> getMembers() {
        return Collections.unmodifiableList(this.members);
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    public void addMember(Person person) {
        this.members.add(person);
    }
}
