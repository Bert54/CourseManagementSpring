package com.test.coursemanagementspring.core.services.person.entities;

import java.util.List;

import static com.test.coursemanagementspring.core.common.permissions.Permissions.*;

public class Administrator extends Person {
    public Administrator(String name) {
        super(name);
    }

    public Administrator(int id, String name) {
        super(id, name);
    }

    public PersonType getType() {
        return PersonType.Administrator;
    }

    @Override
    public List<String> getPermissions() {
        return List.of(
                PERSON_CREATE,
                CLASS_CREATE,
                CLASS_DELETE
        );
    }
}
