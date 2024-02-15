package com.test.coursemanagementspring.core.services.person.adapters;

import com.test.coursemanagementspring.core.services.person.entities.Person;

public interface PersonDaoAdapter {
    Person find(int id);
    Person find(String name);
    Person save(Person person);
}
