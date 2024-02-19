package com.test.coursemanagementspring.core.common.errors;

public class DeletionException extends RuntimeException {
    public DeletionException(String message) {
        super(message);
    }
}
