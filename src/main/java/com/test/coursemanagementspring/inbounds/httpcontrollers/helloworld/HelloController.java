package com.test.coursemanagementspring.inbounds.httpcontrollers.helloworld;

import com.test.coursemanagementspring.core.services.helloworld.adapters.HelloWorldServiceBase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/hello")
public class HelloController {

    private final HelloWorldServiceBase helloWorldService;

    public HelloController(HelloWorldServiceBase helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping
    public String getHello() {
        return helloWorldService.getHello();
    }
}
