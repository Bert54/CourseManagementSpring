package com.test.coursemanagementspring.inbounds.http.common.errorhandler;

import com.test.coursemanagementspring.core.common.errors.*;
import com.test.coursemanagementspring.inbounds.http.common.errorhandler.object.ErrorObject;
import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    private final LoggerAdapter logger;

    public ExceptionHandlerController(LoggerAdapter logger) {
        super();
        this.logger = logger;
    }

    @ExceptionHandler({ValidationException.class, AlreadyExistsException.class})
    public ResponseEntity<Object> handleBadRequestError(Exception e, WebRequest request) {
        return handleGeneric(e, request, 400, ErrorObject.BAD_REQUEST, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundError(Exception e, WebRequest request) {
        return handleGeneric(e, request, 404, ErrorObject.NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenError(Exception e, WebRequest request) {
        return handleGeneric(e, request, 403, ErrorObject.FORBIDDEN, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedError(Exception e, WebRequest request) {
        return handleGeneric(e, request, 401, ErrorObject.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }

    // the two methods below allow us to customize the default "route/resource not found" errors managed by Spring

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(@Nullable NoHandlerFoundException ex, @Nullable HttpHeaders headers, @Nullable HttpStatusCode status, @Nullable WebRequest request) {
        return handleNotFoundError(ex, request);
    }

    @Override
    public ResponseEntity<Object> handleNoResourceFoundException(@Nullable NoResourceFoundException ex, @Nullable HttpHeaders headers, @Nullable HttpStatusCode status, @Nullable WebRequest request) {
        return handleNotFoundError(ex, request);
    }

    // the method below allow us to customize the default "unreadable request" errors managed by Spring
    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(@Nullable HttpMessageNotReadableException ex, @Nullable HttpHeaders headers, @Nullable HttpStatusCode status, @Nullable WebRequest request) {
        return handleBadRequestError(ex, request);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotWritable(@Nullable HttpMessageNotWritableException ex, @Nullable HttpHeaders headers, @Nullable HttpStatusCode status, @Nullable WebRequest request) {
        return handleUnhandledError(ex, request);
    }

    // handle all unhandled errors here.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnhandledError(Exception e, WebRequest request) {
        if (e != null){
            this.logger.warn(String.format("An unexpected error occurred. Error: '%s'", e));
        }
        return handleGeneric(e, request, 500, ErrorObject.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // any new errors to handle should be put above the method above

    private ResponseEntity<Object> handleGeneric(Exception e, WebRequest request, int code, String status, HttpStatus httpStatus) {
        if (e == null) {
            e = new Exception("Unknown error");
        }
        ErrorObject errorObject = new ErrorObject(code, status, e.getMessage());
        return handleExceptionInternal(e, errorObject, new HttpHeaders(), httpStatus, request);
    }
}
