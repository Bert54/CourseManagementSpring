package com.test.coursemanagementspring.core.person.entities;

import java.util.List;

public class Teacher extends Person {
    Teacher(String name) {
        super(name);
    }

    Teacher(int id, String name) {
        super(id, name);
    }

    @Override
    public List<String> getPermissions() {
        return List.of("teacher_temp");
    }
}
