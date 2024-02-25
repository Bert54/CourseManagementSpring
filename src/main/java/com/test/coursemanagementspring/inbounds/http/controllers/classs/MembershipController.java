package com.test.coursemanagementspring.inbounds.http.controllers.classs;

import com.test.coursemanagementspring.core.services.classs.adapters.MembershipServiceAdapter;
import com.test.coursemanagementspring.core.services.classs.entities.Membership;
import com.test.coursemanagementspring.inbounds.dto.classs.AddMembershipDto;
import com.test.coursemanagementspring.inbounds.http.common.aspects.checkpermission.CheckPermission;
import com.test.coursemanagementspring.inbounds.http.common.errorhandler.object.ErrorObject;
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

import static com.test.coursemanagementspring.core.common.permissions.Permissions.CLASS_JOIN;
import static com.test.coursemanagementspring.inbounds.http.common.aspects.checkpermission.CheckPermissionAspect.PERMISSION_HEADER;
import static com.test.coursemanagementspring.inbounds.http.common.openapi.Tags.CLASS_TAG;

@RestController
@RequestMapping("/api/v1/membership")
@Tag(name = CLASS_TAG, description = "Handle classes")
public class MembershipController {
    private final LoggerAdapter logger;
    private final MembershipServiceAdapter membershipService;

    public MembershipController(LoggerAdapter logger, MembershipServiceAdapter membershipService) {
        this.logger = logger;
        this.membershipService = membershipService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CheckPermission(permission = CLASS_JOIN)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            description = "Join a class. The class is provided in the request body.",
            summary = "Join a class",
            operationId = "joinClass"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Class successfully joined"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The class has an invalid name or the membership already exists",
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
    public Membership joinClass(
            @RequestHeader(PERMISSION_HEADER) String personIdStr,
            @RequestBody AddMembershipDto membership) {
        // No needs to validate the person ID since the "CheckPermission" aspect already did this for us
        membership.format();
        try {
            membership.validate();
        } catch (Exception e) {
            this.logger.info(e.getMessage());
            throw e;
        }

        return this.membershipService.addMembership(membership.toCoreMembership(Integer.parseInt(personIdStr)));
    }
}
