package fr.esgi.beanz.api.error;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
  private String message;

  private Map<String, String> errors;
}
