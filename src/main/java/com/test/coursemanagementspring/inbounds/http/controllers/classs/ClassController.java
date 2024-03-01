package com.test.coursemanagementspring.inbounds.http.controllers.classs;

import com.test.coursemanagementspring.core.services.classs.adapters.ClassServiceAdapter;
import com.test.coursemanagementspring.core.services.classs.entities.Class;
import com.test.coursemanagementspring.inbounds.common.dto.classs.AddClassDto;
import com.test.coursemanagementspring.inbounds.http.common.errorhandler.object.ErrorObject;
import com.test.coursemanagementspring.inbounds.http.common.aspects.checkpermission.CheckPermission;
import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.test.coursemanagementspring.core.common.permissions.Permissions.CLASS_CREATE;
import static com.test.coursemanagementspring.core.common.permissions.Permissions.CLASS_DELETE;
import static com.test.coursemanagementspring.inbounds.http.common.openapi.Tags.CLASS_TAG;

@RestController
@RequestMapping("/api/v1/class")
@Tag(name = CLASS_TAG, description = "Handle classes")
public class ClassController {
    private final LoggerAdapter logger;
    private final ClassServiceAdapter classService;

    public ClassController(LoggerAdapter logger, ClassServiceAdapter classService) {
        this.logger = logger;
        this.classService = classService;
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Return a class using its name. The name is retrieved from the path.",
            summary = "Return a class by name",
            operationId = "getClass"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Class successfully retrieved"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The class was not found",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorObject.class
                            )
                    )
            )
    })
    public Class getClassByName(@PathVariable("name") String name) {
        return this.classService.getClass(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CheckPermission(permission = CLASS_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            description = "Add a new class. The class is provided in the request body.",
            summary = "Add a class",
            operationId = "addClass"
    )
    @Parameter(
            description = "ID of the person performing this operation",
            required = true,
            name = "X-PersonID",
            in = ParameterIn.HEADER
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Class successfully added"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The person to add has an invalid name or already exists",
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
    public Class addClass(@RequestBody AddClassDto cls) {
        cls.format();
        try {
            cls.validate();
        } catch (Exception e) {
            this.logger.info(e.getMessage());
            throw e;
        }

        return this.classService.addClass(cls.toCoreClass());
    }

    @DeleteMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CheckPermission(permission = CLASS_DELETE)
    @Operation(
            description = "Delete a class using its name and return the deleted class. The name is retrieved from the path.",
            summary = "Delete a class by name",
            operationId = "deleteClass"
    )
    @Parameter(
            description = "ID of the person performing this operation",
            required = true,
            name = "X-PersonID",
            in = ParameterIn.HEADER
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Class successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The class was not found",
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
    public Class deleteClassByName(@PathVariable("name") String name) {
        return this.classService.deleteClass(name);
    }
}
