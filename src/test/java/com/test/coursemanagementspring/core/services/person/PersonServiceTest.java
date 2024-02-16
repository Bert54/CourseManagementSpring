package com.test.coursemanagementspring.core.services.person;

import com.test.coursemanagementspring.core.services.person.adapters.PersonDaoAdapter;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
    @DisplayName("Test PersonService getPerson(int) - OK")
    public void TestGetPersonIntOK() {
        Person person = Mockito.mock(Person.class);
        when(this.dependencies.personDao.find(anyInt())).thenReturn(person);

        assertSame(person, this.personService.getPerson(anyInt()));
        verify(this.dependencies.personDao, times(1)).find(anyInt());
    }

    @Test
    @DisplayName("Test PersonService getPerson(int) - Dao throws runtime exception")
    public void TestGetPersonIntDaoRuntimeException() {
        RuntimeException expected = new RuntimeException("I am an exception");
        when(this.dependencies.personDao.find(anyInt())).thenThrow(expected);

        assertThrows(RuntimeException.class, () -> this.personService.getPerson(anyInt()));
        verify(this.dependencies.personDao, times(1)).find(anyInt());
    }

    @Test
    @DisplayName("Test PersonService getPerson(String) - OK")
    public void TestGetPersonStringOK() {
        Person person = Mockito.mock(Person.class);
        when(this.dependencies.personDao.find(anyString())).thenReturn(person);

        assertSame(person, this.personService.getPerson(anyString()));
        verify(this.dependencies.personDao, times(1)).find(anyString());
    }

    @Test
    @DisplayName("Test PersonService getPerson(String) - Dao throws runtime exception")
    public void TestGetPersonStringDaoRuntimeException() {
        RuntimeException expected = new RuntimeException("I am an exception");
        when(this.dependencies.personDao.find(anyString())).thenThrow(expected);

        assertThrows(RuntimeException.class, () -> this.personService.getPerson(anyString()));
        verify(this.dependencies.personDao, times(1)).find(anyString());
    }

    @Test
    @DisplayName("Test PersonService addPerson() - OK")
    public void TestAddPersonOK() {
        Person person = Mockito.mock(Person.class);
        person.setId(6);
        person.setName("abcd");
        when(this.dependencies.personDao.save(person)).thenReturn(person);

        assertSame(person, this.personService.addPerson(person));
        verify(this.dependencies.personDao, times(1)).save(person);
    }

    @Test
    @DisplayName("Test PersonService addPerson() - Dao throws runtime exception")
    public void TestAddPersonDaoRuntimeException() {
        RuntimeException expected = new RuntimeException("I am an exception");
        when(this.dependencies.personDao.save(any(Person.class))).thenThrow(expected);

        assertThrows(RuntimeException.class, () -> this.personService.addPerson(Mockito.mock(Person.class)));
        verify(this.dependencies.personDao, times(1)).save(any(Person.class));
    }
}
