package com.test.coursemanagementspring.outbounds.databases.sql.person;

import com.test.coursemanagementspring.core.common.errors.AlreadyExistsException;
import com.test.coursemanagementspring.core.common.errors.NotFoundException;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.PersonEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.transformer.PersonTransformerAdaper;
import com.test.coursemanagementspring.outbounds.databases.sql.person.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PersonDaoTest {
    private Dependencies dependencies;
    private PersonDao personDao;

    @BeforeEach
    public void setupDependencies() {
        Dependencies dependencies = new Dependencies();
        dependencies.logger = Mockito.mock(LoggerAdapter.class);
        dependencies.personRepository = Mockito.mock(PersonRepository.class);
        dependencies.personTransformer = Mockito.mock(PersonTransformerAdaper.class);

        this.personDao = new PersonDao(dependencies.personRepository, dependencies.logger, dependencies.personTransformer);

        this.dependencies = dependencies;
    }

    @Test
    @DisplayName("Test find(int) - OK")
    public void TestFindIntOK() {
        PersonEntity personEntity = Mockito.mock(PersonEntity.class);
        when(this.dependencies.personRepository.find(anyInt())).thenReturn(personEntity);

        Person person = Mockito.mock(Person.class);
        when(personEntity.toCorePerson(anyBoolean())).thenReturn(person);

        assertSame(person, this.personDao.find(anyInt()));
        verify(this.dependencies.personRepository, times(1)).find(anyInt());
        verify(personEntity, times(1)).toCorePerson(anyBoolean());
    }

    @Test
    @DisplayName("Test find(int) - Not found error")
    public void TestFindIntNotFound() {
        when(this.dependencies.personRepository.find(anyInt())).thenReturn(null);
        assertThrows(NotFoundException.class, () -> this.personDao.find(anyInt()));
    }

    @Test
    @DisplayName("Test find(String) - OK")
    public void TestFindStringOK() {
        PersonEntity personEntity = Mockito.mock(PersonEntity.class);
        when(this.dependencies.personRepository.find(anyString())).thenReturn(personEntity);

        Person person = Mockito.mock(Person.class);
        when(personEntity.toCorePerson(anyBoolean())).thenReturn(person);

        assertSame(person, this.personDao.find(anyString()));
        verify(this.dependencies.personRepository, times(1)).find(anyString());
        verify(personEntity, times(1)).toCorePerson(anyBoolean());
    }

    @Test
    @DisplayName("Test find(String) - Not found error")
    public void TestFindStringNotFound() {
        when(this.dependencies.personRepository.find(anyString())).thenReturn(null);
        assertThrows(NotFoundException.class, () -> this.personDao.find(anyString()));
    }

    @Test
    @DisplayName("Test save - OK")
    public void TestSaveOK() {
        var personDaoSpy = Mockito.spy(this.personDao);
        PersonEntity personEntity = Mockito.mock(PersonEntity.class);
        Person person = Mockito.mock(Person.class);
        when(this.dependencies.personRepository.save(personEntity)).thenReturn(personEntity);
        when(personEntity.toCorePerson(anyBoolean())).thenReturn(person);
        when(this.dependencies.personTransformer.toPersonEntity(person)).thenReturn(personEntity);

        assertSame(person, personDaoSpy.save(person));
        verify(this.dependencies.personRepository, times(1)).save(personEntity);
        verify(personEntity, times(1)).toCorePerson(anyBoolean());
        verify(personDaoSpy, times(1)).toPersonEntity(person);
        verify(this.dependencies.personTransformer, times(1)).toPersonEntity(person);
    }

    @Test
    @DisplayName("Test save - Already exist error")
    public void TestSaveAlreadyExistError() {
        var personDaoSpy = Mockito.spy(this.personDao);
        PersonEntity personEntity = Mockito.mock(PersonEntity.class);
        Person person = Mockito.mock(Person.class);
        when(this.dependencies.personRepository.save(personEntity)).thenThrow(new DataIntegrityViolationException("I am error"));
        when(this.dependencies.personTransformer.toPersonEntity(person)).thenReturn(personEntity);

        assertThrows(AlreadyExistsException.class, () -> personDaoSpy.save(person));
        verify(this.dependencies.personRepository, times(1)).save(personEntity);
        verify(personEntity, times(0)).toCorePerson(anyBoolean());
        verify(personDaoSpy, times(1)).toPersonEntity(person);
        verify(this.dependencies.personTransformer, times(1)).toPersonEntity(person);
    }
}
