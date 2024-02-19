package com.test.coursemanagementspring.core.common.errors;

public class UndefinedEntityException extends RuntimeException {
    public UndefinedEntityException(String message) {
        super(message);
    }
}
