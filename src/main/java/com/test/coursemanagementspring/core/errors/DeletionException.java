package com.test.coursemanagementspring.core.errors;

public class DeletionException extends RuntimeException {
    public DeletionException(String message) {
        super(message);
    }
}
