package com.luism.Employees.errorHandlers;


import com.luism.Employees.errorHandlers.exceptions.InvalidFieldException;
import com.luism.Employees.errorHandlers.exceptions.NoEmployeeFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoEmployeeFoundException.class)
    public ResponseEntity<Object> NoEmployeeFoundHandler(RuntimeException ex, WebRequest request){
    return handleExceptionInternal(ex, ex.getMessage(),
            new HttpHeaders(), HttpStatus.NOT_FOUND, request);
        }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<Object> InvalidFieldHandler(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
