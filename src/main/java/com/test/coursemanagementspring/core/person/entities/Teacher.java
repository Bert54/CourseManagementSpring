package com.test.coursemanagementspring.core.person.entities;

import java.util.List;

public class Teacher extends Person {
    public Teacher(String name) {
        super(name);
    }

    public Teacher(int id, String name) {
        super(id, name);
    }

    @Override
    public List<String> getPermissions() {
        return List.of("teacher_temp");
    }
}
