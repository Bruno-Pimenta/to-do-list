package com.bruno_pimenta_dev.to_do_list.exception.handler;

import com.bruno_pimenta_dev.to_do_list.exception.ResourceNotFoundException;
import com.bruno_pimenta_dev.to_do_list.exception.error.CustomError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
