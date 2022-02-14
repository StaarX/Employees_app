package com.luism.Employees.errorHandlers.exceptions;

public class NoEmployeeFoundException extends RuntimeException{

    public NoEmployeeFoundException() {
        super();
    }
    public NoEmployeeFoundException(String message) {
        super(message);
    }
}
