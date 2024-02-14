package com.test.coursemanagementspring.core.person.adapters;

import com.test.coursemanagementspring.core.person.entities.Person;

public interface PersonServiceAdapter {
    Person getPerson(int id);

    Person getPerson(String name);
}
