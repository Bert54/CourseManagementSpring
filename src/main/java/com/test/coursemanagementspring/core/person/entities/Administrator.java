package com.test.coursemanagementspring.core.person.entities;

import java.util.List;

public class Administrator extends Person {
    public Administrator(String name) {
        super(name);
    }

    public Administrator(int id, String name) {
        super(id, name);
    }

    @Override
    public List<String> getPermissions() {
        return List.of("administrator_temp");
    }
}
