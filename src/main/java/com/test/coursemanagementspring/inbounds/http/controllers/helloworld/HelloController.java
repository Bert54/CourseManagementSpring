package com.test.coursemanagementspring.inbounds.http.controllers.helloworld;

import com.test.coursemanagementspring.core.services.helloworld.adapters.HelloWorldServiceBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.test.coursemanagementspring.inbounds.http.common.openapi.Tags.HELLO_TAG;

@RestController
@RequestMapping(value = "/api/v1/hello")
@Tag(name = HELLO_TAG, description = "A simple 'Hello World' controller")
public class HelloController {

    private final HelloWorldServiceBase helloWorldService;

    public HelloController(HelloWorldServiceBase helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
            description = "Return a simple 'Hello World!' in plain text. No parameters, body or headers required",
            summary = "Return 'Hello World!' in plain text",
            operationId = "getHello"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "'Hello World!"
            )
    })
    public String getHello() {
        return helloWorldService.getHello();
    }
}
