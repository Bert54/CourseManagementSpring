package com.test.coursemanagementspring.core.person.adapters;

import com.test.coursemanagementspring.core.person.entities.Person;

public interface PersonDaoAdapter {
    public Person find(int id);

    public Person find(String name);
}
