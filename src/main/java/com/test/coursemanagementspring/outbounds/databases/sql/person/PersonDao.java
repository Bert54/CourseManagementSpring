package com.test.coursemanagementspring.outbounds.databases.sql.person;

import com.test.coursemanagementspring.core.common.errors.AlreadyExistsException;
import com.test.coursemanagementspring.core.common.errors.NotFoundException;
import com.test.coursemanagementspring.core.services.person.adapters.PersonDaoAdapter;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.PersonEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.transformer.PersonTransformerAdaper;
import com.test.coursemanagementspring.outbounds.databases.sql.person.repositories.PersonRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao implements PersonDaoAdapter {
    private final PersonRepository personRepository;
    private final LoggerAdapter logger;
    private final PersonTransformerAdaper personTransformer;

    public PersonDao(PersonRepository personRepository, LoggerAdapter logger, PersonTransformerAdaper personTransformer) {
        this.personRepository = personRepository;
        this.logger = logger;
        this.personTransformer = personTransformer;
    }

    @Override
    public Person find(int id) throws NotFoundException {
        PersonEntity person = this.personRepository.find(id);
        if (person == null) {
            String message = String.format("Person with id '%d' was not found", id);
            this.logger.info(message);
            throw new NotFoundException(message);
        }

        return person.toCorePerson();
    }

    @Override
    public Person find(String name) throws NotFoundException {
        PersonEntity person = this.personRepository.find(name);
        if (person == null) {
            String message = String.format("Person with name '%s' was not found", name);
            this.logger.info(message);
            throw new NotFoundException(message);
        }

        return person.toCorePerson();
    }

    @Override
    public Person save(Person person) {
        PersonEntity savedPerson;
        try {
            savedPerson = this.personRepository.save(this.toPersonEntity(person));
        } catch (DataIntegrityViolationException e) {
            String message = String.format("Person with name '%s' already exists", person.getName());
            this.logger.info(message);
            throw new AlreadyExistsException(message);
        }

        return savedPerson.toCorePerson();
    }

    public PersonEntity toPersonEntity(Person person) {
        return this.personTransformer.toPersonEntity(person);
    }
}
