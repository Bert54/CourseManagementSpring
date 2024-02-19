package com.test.coursemanagementspring.libs.logger.adapters;

public interface LoggerAdapter {
    void info(String message);

    void http(String message, String route, String method);
}
