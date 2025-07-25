package com.bruno_pimenta_dev.to_do_list.exception.handler;

import com.bruno_pimenta_dev.to_do_list.exception.DatabaseException;
import com.bruno_pimenta_dev.to_do_list.exception.ResourceNotFoundException;
import com.bruno_pimenta_dev.to_do_list.exception.error.CustomError;
import com.bruno_pimenta_dev.to_do_list.exception.error.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError error = new CustomError(Instant.now(), "Not Found", status.value(), e.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<?> databaseException(DatabaseException e){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        CustomError error = new CustomError(Instant.now(), "Internal Server Error", status.value(), e.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError(Instant.now(), "Erro de validação", status.value(), "Dados inválidos");
        for(FieldError f : e.getBindingResult().getFieldErrors()){
            error.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);
    }
}
