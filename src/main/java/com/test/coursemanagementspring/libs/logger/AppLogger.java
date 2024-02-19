package com.test.coursemanagementspring.libs.logger;

import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class AppLogger implements LoggerAdapter {
    private final Logger logger;

    public AppLogger() {
        this.logger = LogManager.getLogger();
    }

    @Override
    public void info(String message) {
        this.logger.info(message);
    }

    @Override
    public void warn(String message) {
        this.logger.warn(message);
    }

    @Override
    public void http(String route, String method, int status, long responseTime) {
        String responseTimeFormat;
        if (responseTime > 1000) {
            responseTimeFormat = String.format("%f s", (double) responseTime / 1000);
        } else {
            responseTimeFormat = String.format("%d ms", responseTime);
        }
        String message = String.format("http %s %s - Status %d - %s", method, route, status, responseTimeFormat);
        if (status >= 500 && status < 600) {
            this.warn(message);
        } else {
            this.info(message);
        }

    }
}
