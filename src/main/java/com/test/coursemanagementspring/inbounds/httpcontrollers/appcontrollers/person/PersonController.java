package com.test.coursemanagementspring.inbounds.httpcontrollers.appcontrollers.person;

import com.test.coursemanagementspring.core.errors.ValidationException;
import com.test.coursemanagementspring.core.services.person.adapters.PersonServiceAdapter;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.inbounds.dto.person.AddPersonDto;
import com.test.coursemanagementspring.inbounds.httpcontrollers.errorhandler.object.ErrorObject;
import com.test.coursemanagementspring.inbounds.httpcontrollers.filters.checkpermission.CheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.test.coursemanagementspring.core.permissions.Permissions.PERSON_CREATE;
import static com.test.coursemanagementspring.inbounds.httpcontrollers.openapi.Tags.PERSON_TAG;

@RestController
@RequestMapping("/api/v1/person")
@Tag(name = PERSON_TAG, description = "Handle people")
public class PersonController {
    private final PersonServiceAdapter personService;

    PersonController(PersonServiceAdapter personService) {
        this.personService = personService;
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
            throw new ValidationException(String.format("'%s' is not a valid number", idStr));
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
            )
    })
    public Person addPerson(@RequestBody AddPersonDto person) {
        person.format();
        person.validate();
        return this.personService.addPerson(person.toCorePerson());
    }
}
