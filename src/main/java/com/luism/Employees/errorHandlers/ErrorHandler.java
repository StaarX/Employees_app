package com.luism.Employees.errorHandlers;


import com.luism.Employees.errorHandlers.exceptions.InvalidFieldException;
import com.luism.Employees.errorHandlers.exceptions.NoEmployeeFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> ValidationHandler(ConstraintViolationException ex, WebRequest request) {
        String errorMessage = new ArrayList<>(ex.getConstraintViolations()).get(0).getMessage();
        return handleExceptionInternal(ex, errorMessage,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
    public ResponseEntity<Object> FailedToInsert(JpaObjectRetrievalFailureException ex, WebRequest request) {
        String errorMessage=ex.getMessage();
//        if (Arrays.stream(ex.getStackTrace())
//                .anyMatch(st-> st.getFileName()
//                                .equals("EmployeeController.java") &&
//                                (st.getMethodName().equals("insertEmployee") ||st.getMethodName().equals("updateEmployee")))) errorMessage="Department specified does not exist";
        if (errorMessage.contains("Unable to find com.luism.Employees.models.Department with id"))errorMessage="Department specified does not exist";
        return handleExceptionInternal(ex, errorMessage,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
