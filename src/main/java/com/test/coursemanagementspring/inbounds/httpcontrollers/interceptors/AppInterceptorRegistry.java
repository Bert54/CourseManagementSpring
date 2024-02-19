package com.test.coursemanagementspring.inbounds.httpcontrollers.interceptors;

import com.test.coursemanagementspring.inbounds.httpcontrollers.interceptors.logger.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppInterceptorRegistry implements WebMvcConfigurer {
    private final LoggerInterceptor loggerInterceptor;

    AppInterceptorRegistry(LoggerInterceptor loggerInterceptor) {
        this.loggerInterceptor = loggerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loggerInterceptor);
    }
}
