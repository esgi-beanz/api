package fr.esgi.beanz.api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpErrorException.class)
    public ResponseEntity<Map<String, Object>> generateNotFoundException(HttpErrorException e) {
        return new ResponseEntity<>(e.responseBody(), e.getStatus());
    }
}
