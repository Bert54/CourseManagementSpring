package com.test.coursemanagementspring.core.services.person.adapters;

import com.test.coursemanagementspring.core.services.person.entities.Person;

public interface PersonServiceAdapter {
    Person getPerson(int id);

    Person getPerson(String name);

    Person addPerson(Person person);
}
