package com.test.coursemanagementspring.inbounds.common.dto.classs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.coursemanagementspring.core.common.errors.ValidationException;
import com.test.coursemanagementspring.core.services.classs.entities.Class;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import static com.test.coursemanagementspring.core.services.classs.entities.Class.NAME_MAX_LENGTH;
import static com.test.coursemanagementspring.core.services.classs.entities.Class.NAME_MIN_LENGTH;

@Schema(description = "Structure used to create a new class")
public class AddClassDto {
    @NotNull
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    @Schema(description = "Name of the class")
    private String name;

    public AddClassDto(@JsonProperty("name")  String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void format() {
        this.name = this.name.trim();
    }

    public void validate() {
        Set<String> violations = new HashSet<>();

        // validate name
        // we can ignore NAME_MIN_NAME since it is equal to 1
        if (this.name.isEmpty()) {
            violations.add("Name is empty");
        } else if (this.name.length() > NAME_MAX_LENGTH) {
            violations.add(String.format("Name must be at most %d characters long", NAME_MAX_LENGTH));
        }

        if (!violations.isEmpty()) {
            String message = String.join(", ", violations);
            throw new ValidationException(String.format("Validation error: %s", message));
        }
    }

    public Class toCoreClass() {
        return new Class(this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof AddClassDto dto)) {
            return false;
        }

        return this.name.equals(dto.name);
    }
}
