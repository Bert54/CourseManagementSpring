package com.test.coursemanagementspring.core.person;

import com.test.coursemanagementspring.core.person.adapters.PersonDaoAdapter;
import com.test.coursemanagementspring.core.person.adapters.PersonServiceAdapter;
import com.test.coursemanagementspring.core.person.entities.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonService implements PersonServiceAdapter {
    private final PersonDaoAdapter personDao;

    public PersonService(PersonDaoAdapter personDao) {
        this.personDao = personDao;
    }

    public Person getPerson(int id) {
        return this.personDao.find(id);
    }

    public Person getPerson(String name) {
        return this.personDao.find(name);
    }
}
