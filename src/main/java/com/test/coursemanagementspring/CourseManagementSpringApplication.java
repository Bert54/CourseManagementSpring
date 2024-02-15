package com.test.coursemanagementspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CourseManagementSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseManagementSpringApplication.class, args);
    }

}
