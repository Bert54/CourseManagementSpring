package com.test.coursemanagementspring.inbounds.httpcontrollers.appcontrollers.person;

import com.test.coursemanagementspring.core.common.errors.ValidationException;
import com.test.coursemanagementspring.core.services.person.adapters.PersonServiceAdapter;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.inbounds.dto.person.AddPersonDto;
import com.test.coursemanagementspring.inbounds.httpcontrollers.common.errorhandler.object.ErrorObject;
import com.test.coursemanagementspring.inbounds.httpcontrollers.common.aspects.checkpermission.CheckPermission;
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

import static com.test.coursemanagementspring.core.common.permissions.Permissions.PERSON_CREATE;
import static com.test.coursemanagementspring.inbounds.httpcontrollers.common.openapi.Tags.PERSON_TAG;

@RestController
@RequestMapping("/api/v1/person")
@Tag(name = PERSON_TAG, description = "Handle people")
public class PersonController {
    private final PersonServiceAdapter personService;
    private final LoggerAdapter logger;

    PersonController(PersonServiceAdapter personService, LoggerAdapter logger) {
        this.personService = personService;
        this.logger = logger;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Return a person using their ID. The ID is retrieved from the path.",
            summary = "Return a person by ID",
            operationId = "getPerson"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Person successfully retrieved"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The provided ID is not a valid number",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorObject.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The person was not found",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorObject.class
                            )
                    )
            )
    })
    public Person getPersonById(@PathVariable("id") String idStr) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            String message = String.format("'%s' is not a valid number", idStr);
            this.logger.info(message);
            throw new ValidationException(message);
        }

        return this.personService.getPerson(id);
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Return a person using their name. The name is retrieved from the path.",
            summary = "Return a person by name",
            operationId = "getPerson"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Person successfully retrieved"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The person was not found",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorObject.class
                            )
                    )
            )
    })
    public Person getPersonByName(@PathVariable("name") String name) {
        return this.personService.getPerson(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CheckPermission(permission = PERSON_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            description = "Add a new person. The person is provided in the request body.",
            summary = "Add a person",
            operationId = "addPerson"
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
                    description = "Person successfully added"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The person to add has an invalid name, an unknown role or already exists",
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
    public Person addPerson(@RequestBody AddPersonDto person) {
        person.format();
        try {
            person.validate();
        } catch (Exception e) {
            this.logger.info(e.getMessage());
            throw e;
        }

        return this.personService.addPerson(person.toCorePerson());
    }
}
