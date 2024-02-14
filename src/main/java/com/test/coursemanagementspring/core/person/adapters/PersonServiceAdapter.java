package com.test.coursemanagementspring.core.person.adapters;

import com.test.coursemanagementspring.core.person.entities.Person;

public interface PersonServiceAdapter {
    public Person getPerson(int id);

    public Person getPerson(String name);
}
