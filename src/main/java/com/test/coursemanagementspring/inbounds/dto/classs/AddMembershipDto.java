package com.test.coursemanagementspring.inbounds.dto.classs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.coursemanagementspring.core.common.errors.ValidationException;
import com.test.coursemanagementspring.core.services.classs.entities.Membership;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import static com.test.coursemanagementspring.core.services.classs.entities.Class.NAME_MAX_LENGTH;
import static com.test.coursemanagementspring.core.services.classs.entities.Class.NAME_MIN_LENGTH;

@Schema(description = "Structure used to create a new class <-> person membership")
public class AddMembershipDto {
    @NotNull
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    @Schema(name= "class_name", description = "Name of the class to join")
    private String className;

    public AddMembershipDto(@JsonProperty("class_name") String className) {
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }

    public void format() {
        this.className = this.className.trim();
    }

    public void validate() {
        Set<String> violations = new HashSet<>();

        // validate name
        // we can ignore NAME_MIN_NAME since it is equal to 1
        if (this.className.isEmpty()) {
            violations.add("Class name is empty");
        } else if (this.className.length() > NAME_MAX_LENGTH) {
            violations.add(String.format("Class name must be at most %d characters long", NAME_MAX_LENGTH));
        }

        if (!violations.isEmpty()) {
            String message = String.join(", ", violations);
            throw new ValidationException(String.format("Validation error: %s", message));
        }
    }

    public Membership toCoreMembership(int personId) {
        return new Membership(personId, this.className);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof AddMembershipDto dto)) {
            return false;
        }

        return this.className.equals(dto.className);
    }
}
