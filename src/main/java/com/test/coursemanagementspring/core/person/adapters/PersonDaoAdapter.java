package com.test.coursemanagementspring.core.person.adapters;

import com.test.coursemanagementspring.core.person.entities.Person;

public interface PersonDaoAdapter {
    Person find(int id);
    Person find(String name);
}
