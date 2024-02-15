package com.test.coursemanagementspring.outbounds.databases.sql.person;

import com.test.coursemanagementspring.core.errors.NotFoundException;
import com.test.coursemanagementspring.core.person.adapters.PersonDaoAdapter;
import com.test.coursemanagementspring.core.person.entities.Person;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.PersonEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.repositories.PersonRepository;
import org.springframework.stereotype.Component;

@Component
public class PersonDao implements PersonDaoAdapter {
    private final PersonRepository personRepository;

    public PersonDao(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person find(int id) throws NotFoundException {
        PersonEntity person = this.personRepository.find(id);
        if (person == null) {
            throw new NotFoundException(String.format("Person with id '%d' was not found", id));
        }
        return person.toCorePerson();
    }

    @Override
    public Person find(String name) throws NotFoundException {
        PersonEntity person = this.personRepository.find(name);
        if (person == null) {
            throw new NotFoundException(String.format("Person with name '%s' was not found", name));
        }
        return person.toCorePerson();
    }
}
