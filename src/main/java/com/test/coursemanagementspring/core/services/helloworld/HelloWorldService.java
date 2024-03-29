package com.test.coursemanagementspring.core.services.helloworld;

import com.test.coursemanagementspring.core.services.helloworld.adapters.HelloWorldServiceBase;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldService implements HelloWorldServiceBase {
    @Override
    public String getHello() {
        return "Hello World!";
    }
}
