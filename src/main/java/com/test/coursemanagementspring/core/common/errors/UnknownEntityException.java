package com.test.coursemanagementspring.core.common.errors;

public class UnknownEntityException extends RuntimeException {
    public UnknownEntityException(String message) {
        super(message);
    }
}
