package com.test.coursemanagementspring.core.person.adapters;

import com.test.coursemanagementspring.core.errors.NotFoundException;
import com.test.coursemanagementspring.core.person.entities.Person;

public interface PersonDaoAdapter {
    Person find(int id) throws NotFoundException;
    Person find(String name) throws NotFoundException;
}
