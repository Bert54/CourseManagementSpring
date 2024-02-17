package com.test.coursemanagementspring.inbounds.httpcontrollers.errorhandler;

import com.test.coursemanagementspring.core.errors.*;
import com.test.coursemanagementspring.inbounds.httpcontrollers.errorhandler.object.ErrorObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
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

    // handle all unhandled errors here.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnhandledError(Exception e, WebRequest request) {
        return handleGeneric(e, request, 500, ErrorObject.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // any new errors to handle should be put above the method above

    private ResponseEntity<Object> handleGeneric(Exception e, WebRequest request, int code, String status, HttpStatus httpStatus) {
        ErrorObject errorObject = new ErrorObject(code, status, e.getMessage());
        return handleExceptionInternal(e, errorObject, new HttpHeaders(), httpStatus, request);
    }
}
