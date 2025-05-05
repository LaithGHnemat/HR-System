package com.laith.hrsystem.laith.exceptions.handler;

import com.laith.hrsystem.laith.exceptions.EmployeeNotFoundException;
import com.laith.hrsystem.laith.exceptions.NotCorrectManagerException;
import com.laith.hrsystem.laith.exceptions.NotFoundDepartmentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotCorrectManagerException.class)
    public ResponseEntity<?> handleRecordNotFoundException(NotCorrectManagerException ex){

        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), List.of(ex.getMessage()));
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleDuplicateRecordException(EmployeeNotFoundException ex){
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), List.of(ex.getMessage()));
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(NotFoundDepartmentException.class)
    public ResponseEntity<?> handleRecordNotFoundException(NotFoundDepartmentException ex){

        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), List.of(ex.getMessage()));
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

}
