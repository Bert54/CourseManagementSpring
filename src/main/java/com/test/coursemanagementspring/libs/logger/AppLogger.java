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
    public void http(String route, String method, int status, long responseTime) {
        String responseTimeFormat;
        if (responseTime > 1000) {
            responseTimeFormat = String.format("%d s", responseTime / 1000);
        } else {
            responseTimeFormat = String.format("%d ms", responseTime);
        }
        this.logger.info(String.format("http %s %s - Status %d - %s", method, route, status, responseTimeFormat));
    }
}
