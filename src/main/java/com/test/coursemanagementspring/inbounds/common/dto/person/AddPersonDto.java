package com.test.coursemanagementspring.inbounds.common.dto.person;

import com.test.coursemanagementspring.core.common.errors.UnknownEntityException;
import com.test.coursemanagementspring.core.common.errors.ValidationException;
import com.test.coursemanagementspring.core.services.person.entities.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import static com.test.coursemanagementspring.core.services.person.entities.Person.*;

@Schema(description = "Structure used to create a new person")
public class AddPersonDto {
    public static final String ADMINISTRATOR_ROLE = "administrator";
    public static final String TEACHER_ROLE = "teacher";
    public static final String STUDENT_ROLE = "student";

    @NotNull
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    @Schema(description = "Name of the person")
    private String name;

    @NotNull
    @Schema(description = "Role of the person", allowableValues = {ADMINISTRATOR_ROLE, TEACHER_ROLE, STUDENT_ROLE})
    private String role;

    public AddPersonDto(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    @SuppressWarnings("unused") // Used by the OpenAPI documentation
    public String getRole() {
        return this.role;
    }

    public void format() {
        this.name = this.name.trim();
        this.role = this.role.toLowerCase().trim();
    }

    public void validate() {
        Set<String> violations = new HashSet<>();

        // validate name
        if (this.name.isEmpty()) {
            violations.add("Name is empty");
        } else if (this.name.length() < NAME_MIN_LENGTH) {
            violations.add(String.format("Name must be at least %d characters long", NAME_MIN_LENGTH));
        } else if (this.name.length() > NAME_MAX_LENGTH) {
            violations.add(String.format("Name must be at most %d characters long", NAME_MAX_LENGTH));
        }

        // validate role
        if (!Set.of(ADMINISTRATOR_ROLE, STUDENT_ROLE, TEACHER_ROLE).contains(this.role)) {
            violations.add(String.format("Role '%s' is unknown", this.role));
        }

        if (!violations.isEmpty()) {
            String message = String.join(", ", violations);
            throw new ValidationException(String.format("Validation error: %s", message));
        }
    }

    public Person toCorePerson() {
        return switch (this.role) {
            case ADMINISTRATOR_ROLE -> new Administrator(this.name);
            case TEACHER_ROLE -> new Teacher(this.name);
            case STUDENT_ROLE -> new Student(this.name);
            default -> throw new UnknownEntityException(String.format("Unknown person role '%s'", this.role));
        };
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof AddPersonDto dto)) {
            return false;
        }

        return this.name.equals(dto.name) && this.role.equals(dto.role);
    }
}
