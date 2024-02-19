package com.test.coursemanagementspring.libs.logger.adapters;

public interface LoggerAdapter {
    void info(String message);

    void warn(String message);

    void http(String route, String method, int status, long responseTime);
}
