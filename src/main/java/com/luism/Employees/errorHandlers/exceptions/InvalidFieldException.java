package com.luism.Employees.errorHandlers.exceptions;

public class InvalidFieldException extends RuntimeException{

    public InvalidFieldException() {
        super();
    }
    public InvalidFieldException(String message) {
        super(message);
    }
}
