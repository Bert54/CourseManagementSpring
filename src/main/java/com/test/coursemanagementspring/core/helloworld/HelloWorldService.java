package com.test.coursemanagementspring.core.helloworld;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldService implements HelloWorldServiceBase {
    @Override
    public String getHello() {
        return "Hello World!";
    }
}
