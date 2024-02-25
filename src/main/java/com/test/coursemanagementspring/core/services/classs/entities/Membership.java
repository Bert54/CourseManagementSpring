package com.test.coursemanagementspring.core.services.classs.entities;

import com.test.coursemanagementspring.core.services.person.entities.Person;

public class Membership {
    private int personId;
    private String className;

    public Membership(int personId, String className) {
        this.personId = personId;
        this.className = className;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
