package com.test.coursemanagementspring.core.services.person.entities.transformer;

import com.test.coursemanagementspring.core.services.person.entities.Administrator;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.core.services.person.entities.Student;
import com.test.coursemanagementspring.core.services.person.entities.Teacher;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.AdministratorEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.PersonEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.StudentEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.TeacherEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.transformer.PersonTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTransformerTest {
    private PersonTransformer personTransformer;

    @BeforeEach
    public void setup() {
        this.personTransformer = new PersonTransformer();
    }

    @Test
    @DisplayName("Test toPersonEntity -> Student")
    public void TestToPersonEntityStudent() {
        var name = "boris";
        Person basePerson = new Student(name);

        PersonEntity gotten = this.personTransformer.toPersonEntity(basePerson);
        assertAll("Test name + class equality",
                () -> assertEquals(name, gotten.getName()),
                () -> assertThat(gotten, instanceOf(StudentEntity.class))
        );
    }

    @Test
    @DisplayName("Test toPersonEntity -> Teacher")
    public void TestToPersonEntityTeacher() {
        var name = "boris";
        Person basePerson = new Teacher(name);

        PersonEntity gotten = this.personTransformer.toPersonEntity(basePerson);
        assertAll("Test name + class equality",
                () -> assertEquals(name, gotten.getName()),
                () -> assertThat(gotten, instanceOf(TeacherEntity.class))
        );
    }

    @Test
    @DisplayName("Test toPersonEntity -> Administrator")
    public void TestToPersonEntityAdministrator() {
        var name = "boris";
        Person basePerson = new Administrator(name);

        PersonEntity gotten = this.personTransformer.toPersonEntity(basePerson);
        assertAll("Test name + class equality",
                () -> assertEquals(name, gotten.getName()),
                () -> assertThat(gotten, instanceOf(AdministratorEntity.class))
        );
    }
}
