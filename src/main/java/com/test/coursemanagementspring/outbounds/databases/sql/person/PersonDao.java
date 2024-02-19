package com.test.coursemanagementspring.outbounds.databases.sql.person;

import com.test.coursemanagementspring.core.errors.AlreadyExistsException;
import com.test.coursemanagementspring.core.errors.NotFoundException;
import com.test.coursemanagementspring.core.services.person.adapters.PersonDaoAdapter;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.AdministratorEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.PersonEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.StudentEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.TeacherEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.repositories.PersonRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class PersonDao implements PersonDaoAdapter {
    private final PersonRepository personRepository;
    private final LoggerAdapter logger;

    public PersonDao(PersonRepository personRepository, LoggerAdapter logger) {
        this.personRepository = personRepository;
        this.logger = logger;
    }

    public Person find(int id) throws NotFoundException {
        PersonEntity person = this.personRepository.find(id);
        if (person == null) {
            String message = String.format("Person with id '%d' was not found", id);
            this.logger.info(message);
            throw new NotFoundException(message);
        }

        return person.toCorePerson();
    }

    public Person find(String name) throws NotFoundException {
        PersonEntity person = this.personRepository.find(name);
        if (person == null) {
            throw new NotFoundException(String.format("Person with name '%s' was not found", name));
        }

        return person.toCorePerson();
    }

    public Person save(Person person) {
        PersonEntity savedPerson;
        try {
            savedPerson = this.personRepository.save(this.toPersonEntity(person));
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException(String.format("Person with name '%s' already exists", person.getName()));
        }

        return savedPerson.toCorePerson();
    }

    private PersonEntity toPersonEntity(Person person) {
        String name = person.getName();

        return switch (person.getType()) {
            case Student -> new StudentEntity(name);
            case Administrator -> new AdministratorEntity(name);
            case Teacher -> new TeacherEntity(name);
        };
    }
}
