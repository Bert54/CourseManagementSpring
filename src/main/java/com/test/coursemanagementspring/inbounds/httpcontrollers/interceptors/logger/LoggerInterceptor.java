package com.test.coursemanagementspring.inbounds.httpcontrollers.interceptors.logger;

import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

    private final LoggerAdapter logger;

    public LoggerInterceptor(LoggerAdapter logger) {
        this.logger = logger;
    }

    @Override
    public void postHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            ModelAndView modelAndView) {
        String message = String.format("Status %d", response.getStatus());
        this.logger.http(message, request.getRequestURI(), request.getMethod());
    }
}
