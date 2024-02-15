package com.test.coursemanagementspring.core.person.adapters;

import com.test.coursemanagementspring.core.errors.NotFoundException;
import com.test.coursemanagementspring.core.person.entities.Person;

public interface PersonServiceAdapter {
    Person getPerson(int id) throws NotFoundException;

    Person getPerson(String name) throws NotFoundException;
}
