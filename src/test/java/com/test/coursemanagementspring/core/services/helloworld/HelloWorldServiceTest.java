package com.test.coursemanagementspring.core.services.helloworld;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldServiceTest {
    HelloWorldService helloWorldService;

    @BeforeEach
    public void setupDependencies() {
        this.helloWorldService = new HelloWorldService();
    }

    @Test
    public void TestGetHello() {
        String expected = "Hello World!";
        assertEquals(expected, this.helloWorldService.getHello());
    }

}
