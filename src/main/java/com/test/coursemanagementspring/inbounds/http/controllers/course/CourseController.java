package com.test.coursemanagementspring.inbounds.http.controllers.course;

import com.test.coursemanagementspring.core.services.course.adapters.CourseServiceAdapter;
import com.test.coursemanagementspring.core.services.course.entities.Course;
import com.test.coursemanagementspring.inbounds.common.dto.course.AddCourseDto;
import com.test.coursemanagementspring.inbounds.http.common.aspects.checkpermission.CheckPermission;
import com.test.coursemanagementspring.inbounds.http.common.errorhandler.object.ErrorObject;
import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.test.coursemanagementspring.core.common.permissions.Permissions.COURSE_CREATE;
import static com.test.coursemanagementspring.inbounds.http.common.aspects.checkpermission.CheckPermissionAspect.PERMISSION_HEADER;
import static com.test.coursemanagementspring.inbounds.http.common.openapi.Tags.COURSE_TAG;

@RestController
@RequestMapping("/api/v1/course")
@Tag(name = COURSE_TAG, description = "Handle courses")
public class CourseController {
    private final LoggerAdapter logger;
    private final CourseServiceAdapter courseService;

    public CourseController(LoggerAdapter logger, CourseServiceAdapter courseService) {
        this.logger = logger;
        this.courseService = courseService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CheckPermission(permission = COURSE_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            description = "Add a new course for a certain class. The course is provided in the request body.",
            summary = "Add a course",
            operationId = "addCourse"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Course successfully added"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The course to add has an invalid referred class name, title or content",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorObject.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The referred class was not found",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorObject.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No authorization header were provided or authorization header is empty",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorObject.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "The person does not have the required permission",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorObject.class
                            )
                    )
            )
    })
    public Course addCourse(
            @RequestHeader(PERMISSION_HEADER) String personIdStr,
            @RequestBody AddCourseDto course) {
        // No needs to validate the person ID since the "CheckPermission" aspect already did this for us
        course.format();
        try {
            course.validate();
        } catch (Exception e) {
            this.logger.info(e.getMessage());
            throw e;
        }

        return this.courseService.addCourse(course.toCoreCourse(Integer.parseInt(personIdStr)));
    }
}
