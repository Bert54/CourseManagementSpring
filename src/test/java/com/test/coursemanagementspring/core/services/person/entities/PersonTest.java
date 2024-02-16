package com.test.coursemanagementspring.core.services.person.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    @Test
    @DisplayName("Test constructor(int, String) with getters")
    public void TestConstructorOne() {
        String expectedName = "abcd";
        int expectedId = 1;
        Person person = Mockito.mock(Person.class, Mockito.withSettings()
                .useConstructor(expectedId, expectedName)
                .defaultAnswer(Mockito.CALLS_REAL_METHODS)
        );

        assertEquals(person.getId(), expectedId);
        assertEquals(person.getName(), expectedName);
    }

    @Test
    @DisplayName("Test constructor(String) with getters")
    public void TestConstructorTwo() {
        String expectedName = "abcd";
        Person person = Mockito.mock(Person.class, Mockito.withSettings()
                .useConstructor(expectedName)
                .defaultAnswer(Mockito.CALLS_REAL_METHODS)
        );

        assertEquals(person.getName(), expectedName);
    }

    @Test
    @DisplayName("Test setName()")
    public void TestSetName() {
        Person person = Mockito.mock(Person.class, Mockito.withSettings()
                        .defaultAnswer(Mockito.CALLS_REAL_METHODS)
        );
        String expectedName = "one";
        person.setName(expectedName);

        assertEquals(person.getName(), expectedName);
    }

    @Test
    @DisplayName("Test setId()")
    public void TestSetId() {
        Person person = Mockito.mock(Person.class, Mockito.withSettings()
                .defaultAnswer(Mockito.CALLS_REAL_METHODS)
        );
        int expectedId = 752;
        person.setId(expectedId);

        assertEquals(person.getId(), expectedId);

        // test a second time to ensure it wasn't a random number
        expectedId = 9963;
        person.setId(expectedId);

        assertEquals(person.getId(), expectedId);
    }
}
