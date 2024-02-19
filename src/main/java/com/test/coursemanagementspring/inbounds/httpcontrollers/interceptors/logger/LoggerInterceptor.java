package com.test.coursemanagementspring.inbounds.httpcontrollers.interceptors.logger;

import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggerInterceptor implements HandlerInterceptor {
    private final LoggerAdapter logger;

    public LoggerInterceptor(LoggerAdapter logger) {
        this.logger = logger;
    }

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler) {
        request.setAttribute("startTime", System.currentTimeMillis());

        return true;
    }

    @Override
    public void afterCompletion(
            @Nullable HttpServletRequest request,
            @Nullable HttpServletResponse response,
            @Nullable Object handler,
            @Nullable Exception ex) {
        if (request != null && response != null) {
            long startTime = (long) request.getAttribute("startTime");
            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;
            this.logger.http(request.getRequestURI(), request.getMethod(), response.getStatus(), timeTaken);
        }
    }
}
