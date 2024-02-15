package com.test.coursemanagementspring.core.errors;

public class UnknownEntityException extends RuntimeException {
    public UnknownEntityException(String message) {
        super(message);
    }
}
