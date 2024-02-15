package com.test.coursemanagementspring.core.services.person.entities;

import java.util.List;

public class Student extends Person{
    public Student(String name) {
        super(name);
    }

    public Student(int id, String name) {
        super(id, name);
    }

    public PersonType getType() {
        return PersonType.Student;
    }

    @Override
    public List<String> getPermissions() {
        return List.of("student_temp");
    }
}
