package com.test.coursemanagementspring.core.services.person;

import com.test.coursemanagementspring.core.common.errors.ValidationException;
import com.test.coursemanagementspring.core.services.person.adapters.PersonDaoAdapter;
import com.test.coursemanagementspring.core.services.person.adapters.PersonServiceAdapter;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements PersonServiceAdapter {
    private final PersonDaoAdapter personDao;

    public PersonService(PersonDaoAdapter personDao) {
        this.personDao = personDao;
    }

    public Person getPerson(int id) {
        if (id < 0) {
            throw new ValidationException("ID must be a positive number");
        }

        return this.personDao.find(id);
    }

    public Person getPerson(String name) {
        return this.personDao.find(name);
    }

    public Person addPerson(Person person) {
        return this.personDao.save(person);
    }
}
