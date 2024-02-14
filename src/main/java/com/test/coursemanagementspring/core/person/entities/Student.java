package com.test.coursemanagementspring.core.person.entities;

import java.util.List;

public class Student extends Person{
    Student(String name) {
        super(name);
    }

    Student(int id, String name) {
        super(id, name);
    }

    @Override
    public List<String> getPermissions() {
        return List.of("student_temp");
    }
}
