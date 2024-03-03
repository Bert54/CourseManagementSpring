package com.test.coursemanagementspring.inbounds.common.dto.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.coursemanagementspring.core.common.errors.ValidationException;
import com.test.coursemanagementspring.core.services.course.entities.Course;
import com.test.coursemanagementspring.inbounds.common.dto.classs.AddClassDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import static com.test.coursemanagementspring.core.services.classs.entities.Class.NAME_MAX_LENGTH;
import static com.test.coursemanagementspring.core.services.classs.entities.Class.NAME_MIN_LENGTH;
import static com.test.coursemanagementspring.core.services.course.entities.Course.*;

@Schema(description = "Structure used to create a new course")
public class AddCourseDto {
    @NotNull
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    @Schema(description = "Name of the class the course belongs to")
    @JsonProperty("class_name")
    private String className;

    @NotNull
    @Size(min = TITLE_MIN_LENGTH, max = TITLE_MAX_LENGTH)
    @Schema(description = "Title of the course")
    private String title;

    @NotNull
    @Size(min = CONTENT_MIN_LENGTH)
    @Schema(description = "Content of the course")
    private String content;

    public AddCourseDto(@JsonProperty("class_name") String className, String title, String content) {
        this.className = className;
        this.title = title;
        this.content = content;
    }

    public String getClassName() {
        return className;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void format() {
        this.className = this.className.trim();
        this.title = this.title.trim();
        this.content = this.content.trim();
    }

    public void validate() {
        Set<String> violations = new HashSet<>();

        // validate className
        // we can ignore NAME_MIN_NAME since it is equal to 1
        if (this.className.isEmpty()) {
            violations.add("Name must not be empty");
        } else if (this.className.length() > NAME_MAX_LENGTH) {
            violations.add(String.format("Name must be at most %d characters long", NAME_MAX_LENGTH));
        }

        // validate title
        if (this.title.isEmpty()) {
            violations.add("Title must not be empty");
        } else if (this.title.length() < TITLE_MIN_LENGTH) {
            violations.add(String.format("Title must be at least %d characters long", TITLE_MIN_LENGTH));
        } else if (this.title.length() > TITLE_MAX_LENGTH) {
            violations.add(String.format("Title must be at most %d characters long", TITLE_MAX_LENGTH));
        }

        // validate content
        // we can ignore CONTENT_MIN_LENGTH since it is equal to 1
        if (this.content.isEmpty()) {
            violations.add("Content must not be empty");
        }

        if (!violations.isEmpty()) {
            String message = String.join(", ", violations);
            throw new ValidationException(String.format("Validation error: %s", message));
        }
    }

    public Course toCoreCourse(int teacherId) {
        return new Course(teacherId, this.className, this.title, this.content);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof AddCourseDto dto)) {
            return false;
        }

        return this.className.equals(dto.className) && this.title.equals(dto.title) && this.content.equals(dto.content);
    }
}
