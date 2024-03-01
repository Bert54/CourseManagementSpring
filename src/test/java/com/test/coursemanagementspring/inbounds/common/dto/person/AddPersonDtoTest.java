package com.test.coursemanagementspring.inbounds.common.dto.person;

import com.test.coursemanagementspring.core.common.errors.UnknownEntityException;
import com.test.coursemanagementspring.core.common.errors.ValidationException;
import com.test.coursemanagementspring.core.services.person.entities.Administrator;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.core.services.person.entities.Student;
import com.test.coursemanagementspring.core.services.person.entities.Teacher;
import com.test.coursemanagementspring.inbounds.common.dto.person.AddPersonDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddPersonDtoTest {
    @Test
    @DisplayName("Test format()")
    public void TestFormat() {
        AddPersonDto dto = new AddPersonDto(" jAmeS      ", " SECret aGEnT   ");
        AddPersonDto expected = new AddPersonDto("jAmeS", "secret agent");

        dto.format();

        assertEquals(expected, dto);
    }

    @Test
    @DisplayName("Test validate() - OK Administrator")
    public void TestValidateOKAdministrator() {
        AddPersonDto dto = new AddPersonDto("james", "administrator");
        assertDoesNotThrow(dto::validate);
    }

    @Test
    @DisplayName("Test validate() - OK Student")
    public void TestValidateOKStudent() {
        AddPersonDto dto = new AddPersonDto("james", "student");
        assertDoesNotThrow(dto::validate);
    }

    @Test
    @DisplayName("Test validate() - OK Teacher")
    public void TestValidateOKTeacher() {
        AddPersonDto dto = new AddPersonDto("james", "teacher");
        assertDoesNotThrow(dto::validate);
    }

    @Test
    @DisplayName("Test validate() - No name")
    public void TestValidateNoName() {
        AddPersonDto dto = new AddPersonDto("", "administrator");
        assertThrows(ValidationException.class, dto::validate);
    }

    @Test
    @DisplayName("Test validate() - Name too short")
    public void TestValidateNameTooShort() {
        AddPersonDto dto = new AddPersonDto("a", "administrator");
        assertThrows(ValidationException.class, dto::validate);
    }

    @Test
    @DisplayName("Test validate() - Name too short")
    public void TestValidateNameTooLong() {
        AddPersonDto dto = new AddPersonDto(
                // 101 characters
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "administrator");
        assertThrows(ValidationException.class, dto::validate);
    }

    @Test
    @DisplayName("Test validate() - Role unknown")
    public void TestValidateRoleUnknown() {
        AddPersonDto dto = new AddPersonDto("james", "secret agent");
        assertThrows(ValidationException.class, dto::validate);
    }

    @Test
    @DisplayName("Test toCorePerson() - OK Administrator")
    public void TestToCorePersonOKAdministrator() {
        String expectedName = "james";
        AddPersonDto dto = new AddPersonDto(expectedName, "administrator");

        assertDoesNotThrow(() -> {
            Person person = dto.toCorePerson();
            assertInstanceOf(Administrator.class, person);
            assertEquals(expectedName, person.getName());
        });
    }

    @Test
    @DisplayName("Test toCorePerson() - OK Student")
    public void TestToCorePersonOKStudent() {
        String expectedName = "james";
        AddPersonDto dto = new AddPersonDto(expectedName, "student");

        assertDoesNotThrow(() -> {
            Person person = dto.toCorePerson();
            assertInstanceOf(Student.class, person);
            assertEquals(expectedName, person.getName());
        });
    }

    @Test
    @DisplayName("Test toCorePerson() - OK Teacher")
    public void TestToCorePersonOKTeacher() {
        String expectedName = "james";
        AddPersonDto dto = new AddPersonDto(expectedName, "teacher");

        assertDoesNotThrow(() -> {
            Person person = dto.toCorePerson();
            assertInstanceOf(Teacher.class, person);
            assertEquals(expectedName, person.getName());
        });
    }

    @Test
    @DisplayName("Test toCorePerson() - Unknown role")
    public void TestToCorePersonUnknownRole() {
        AddPersonDto dto = new AddPersonDto("james", "secret agent");
        assertThrows(UnknownEntityException.class, dto::toCorePerson);
    }
}
