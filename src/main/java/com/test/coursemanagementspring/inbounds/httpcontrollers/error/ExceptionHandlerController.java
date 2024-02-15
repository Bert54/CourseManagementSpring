package com.test.coursemanagementspring.inbounds.httpcontrollers.error;

import com.test.coursemanagementspring.core.errors.NotFoundException;
import com.test.coursemanagementspring.core.errors.ValidationException;
import com.test.coursemanagementspring.inbounds.httpcontrollers.error.object.ErrorObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleBadRequestError(Exception e, WebRequest request) {
        return handleGeneric(e, request, 400, ErrorObject.BAD_REQUEST, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundError(Exception e, WebRequest request) {
        return handleGeneric(e, request, 404, ErrorObject.NOT_FOUND, HttpStatus.NOT_FOUND);
    }

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
