package com.test.coursemanagementspring.core.common.errors;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
