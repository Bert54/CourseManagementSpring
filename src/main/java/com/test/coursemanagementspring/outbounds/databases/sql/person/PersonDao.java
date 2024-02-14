package com.test.coursemanagementspring.outbounds.databases.sql.person;

import com.test.coursemanagementspring.core.person.adapters.PersonDaoAdapter;
import com.test.coursemanagementspring.core.person.entities.Person;
import com.test.coursemanagementspring.outbounds.databases.sql.person.repositories.PersonRepository;
import org.springframework.stereotype.Component;

@Component
public class PersonDao implements PersonDaoAdapter {
    private final PersonRepository personRepository;

    public PersonDao(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person find(int id) {
        return this.personRepository.find(id).toCorePerson();
    }

    @Override
    public Person find(String name) {
        return this.personRepository.find(name).toCorePerson();
    }
}
