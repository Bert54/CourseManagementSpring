package com.test.coursemanagementspring.core.common.errors;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
