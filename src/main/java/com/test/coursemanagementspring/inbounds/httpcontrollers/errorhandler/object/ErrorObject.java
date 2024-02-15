package com.test.coursemanagementspring.inbounds.httpcontrollers.errorhandler.object;

import java.io.Serializable;

public class ErrorObject implements Serializable {
    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

    private final int code;
    private final String status;
    private final String message;

    public ErrorObject(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
