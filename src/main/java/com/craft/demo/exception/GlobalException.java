package com.craft.demo.exception;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLSyntaxErrorException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        // Log the exception or perform additional error handling if needed
        e.printStackTrace();

        // Return a specific response for NullPointerException
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("NullPointerException: " + e.getMessage());
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNumberFormatException(NumberFormatException e) {
        // Log the exception or perform additional error handling if needed
        e.printStackTrace();

        // Return a specific response for NumberFormatException
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("NumberFormatException: " + e.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class,MethodArgumentTypeMismatchException.class, SQLSyntaxErrorException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        // Log the exception or perform additional error handling if needed
        e.printStackTrace();

        // Return a specific response for IllegalArgumentException
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("IllegalArgumentException: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception or perform additional error handling if needed
        e.printStackTrace();

        // Return a generic server error response
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("");
    }
}
