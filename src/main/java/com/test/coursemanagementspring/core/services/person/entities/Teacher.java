package com.test.coursemanagementspring.core.services.person.entities;

import java.util.List;

import static com.test.coursemanagementspring.core.common.permissions.Permissions.CLASS_JOIN;

public class Teacher extends Person {
    public Teacher(String name) {
        super(name);
    }

    public Teacher(int id, String name) {
        super(id, name);
    }

    public PersonType getType() {
        return PersonType.Teacher;
    }

    @Override
    public List<String> getPermissions() {
        return List.of(
                CLASS_JOIN
        );
    }
}
