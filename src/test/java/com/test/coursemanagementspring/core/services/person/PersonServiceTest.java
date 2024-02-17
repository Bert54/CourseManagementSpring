package com.test.coursemanagementspring.core.services.person;

import com.test.coursemanagementspring.core.errors.ValidationException;
import com.test.coursemanagementspring.core.services.person.adapters.PersonDaoAdapter;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PersonServiceTest {
    private PersonService personService;
    private Dependencies dependencies;

    @BeforeEach
    public void setupDependencies() {
        Dependencies dependencies = new Dependencies();
        dependencies.personDao = Mockito.mock(PersonDaoAdapter.class);

        this.personService = new PersonService(dependencies.personDao);

        this.dependencies = dependencies;
    }

    @Test
    @DisplayName("Test getPerson(int) - OK")
    public void TestGetPersonIntOK() {
        Person person = Mockito.mock(Person.class);
        when(this.dependencies.personDao.find(anyInt())).thenReturn(person);

        assertSame(person, this.personService.getPerson(anyInt()));
        verify(this.dependencies.personDao, times(1)).find(anyInt());
    }

    @Test
    @DisplayName("Test getPerson(int) - Negative ID error")
    public void TestGetPersonIntNegativeIDError() {
        assertThrows(ValidationException.class, () -> this.personService.getPerson(-1));
        verify(this.dependencies.personDao, times(0)).find(anyInt());
    }

    @Test
    @DisplayName("Test getPerson(int) - Dao throws runtime exception")
    public void TestGetPersonIntDaoRuntimeException() {
        RuntimeException expected = new RuntimeException("I am an exception");
        when(this.dependencies.personDao.find(anyInt())).thenThrow(expected);

        assertThrows(RuntimeException.class, () -> this.personService.getPerson(anyInt()));
        verify(this.dependencies.personDao, times(1)).find(anyInt());
    }

    @Test
    @DisplayName("Test getPerson(String) - OK")
    public void TestGetPersonStringOK() {
        Person person = Mockito.mock(Person.class);
        when(this.dependencies.personDao.find(anyString())).thenReturn(person);

        assertSame(person, this.personService.getPerson(anyString()));
        verify(this.dependencies.personDao, times(1)).find(anyString());
    }

    @Test
    @DisplayName("Test getPerson(String) - Dao throws runtime exception")
    public void TestGetPersonStringDaoRuntimeException() {
        RuntimeException expected = new RuntimeException("I am an exception");
        when(this.dependencies.personDao.find(anyString())).thenThrow(expected);

        assertThrows(RuntimeException.class, () -> this.personService.getPerson(anyString()));
        verify(this.dependencies.personDao, times(1)).find(anyString());
    }

    @Test
    @DisplayName("Test addPerson() - OK")
    public void TestAddPersonOK() {
        Person person = Mockito.mock(Person.class);
        person.setId(6);
        person.setName("abcd");
        when(this.dependencies.personDao.save(person)).thenReturn(person);

        assertSame(person, this.personService.addPerson(person));
        verify(this.dependencies.personDao, times(1)).save(person);
    }

    @Test
    @DisplayName("Test addPerson() - Dao throws runtime exception")
    public void TestAddPersonDaoRuntimeException() {
        RuntimeException expected = new RuntimeException("I am an exception");
        when(this.dependencies.personDao.save(any(Person.class))).thenThrow(expected);

        assertThrows(RuntimeException.class, () -> this.personService.addPerson(Mockito.mock(Person.class)));
        verify(this.dependencies.personDao, times(1)).save(any(Person.class));
    }
}
