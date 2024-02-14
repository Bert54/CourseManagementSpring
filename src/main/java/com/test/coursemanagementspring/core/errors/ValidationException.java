package com.test.coursemanagementspring.core.errors;

public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
