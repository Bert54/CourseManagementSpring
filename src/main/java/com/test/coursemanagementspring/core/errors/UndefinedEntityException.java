package com.test.coursemanagementspring.core.errors;

public class UndefinedEntityException extends RuntimeException {
    public UndefinedEntityException(String message) {
        super(message);
    }
}
