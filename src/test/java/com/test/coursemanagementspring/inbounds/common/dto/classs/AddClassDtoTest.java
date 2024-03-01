package com.test.coursemanagementspring.inbounds.common.dto.classs;

import com.test.coursemanagementspring.core.common.errors.ValidationException;
import com.test.coursemanagementspring.inbounds.common.dto.classs.AddClassDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddClassDtoTest {
    @Test
    @DisplayName("Test format()")
    public void TestFormat() {
        var dto = new AddClassDto(" mI6      ");
        var expected = new AddClassDto("mI6");

        dto.format();

        assertEquals(expected, dto);
    }

    @Test
    @DisplayName("Test validate() - OK")
    public void TestValidateOK() {
        var dto = new AddClassDto("MI6");
        assertDoesNotThrow(dto::validate);
    }

    @Test
    @DisplayName("Test validate() - Empty name error")
    public void TestValidateEmptyNameError() {
        var dto = new AddClassDto("");
        assertThrows(ValidationException.class, dto::validate);
    }

    @Test
    @DisplayName("Test validate() - Name too long error")
    public void TestValidateNameTooLongError() {
        var dto = new AddClassDto("MI66666666666666666666666666666"); // 31 characters
        assertThrows(ValidationException.class, dto::validate);
    }
    @Test
    @DisplayName("Test toCoreClass() - OK")
    public void TestToCorePersonOKStudent() {
        String expectedName = "MI6";
        var dto = new AddClassDto(expectedName);

        var cls = dto.toCoreClass();
        assertEquals(expectedName, cls.getName());
    }

}
