package fr.esgi.beanz.api.error;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import fr.esgi.beanz.api.exceptions.HttpErrorException;

public class ExceptionHandler {
  public static HttpErrorException handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> errors = new ArrayList<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.add(String.format("%s : %s", fieldName, errorMessage));
    });
    return new HttpErrorException(HttpStatus.BAD_REQUEST, "Bad request.", errors.toArray());
  } 
}
