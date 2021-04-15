package fr.esgi.beanz.api.portfolio;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@RestController
@RequestMapping("/portfolios")
@RequiredArgsConstructor
public class PortfolioController {
  @Autowired
  private final PortfolioService portfolioService;

  @GetMapping
  public List<Portfolio> getRepository() {
    return portfolioService.getPortFolios();
  }

  @GetMapping(value = "/{id}")
  public Portfolio getPortfolio(@PathVariable("id") long id) {
    final var portfolio = portfolioService.getPortfolio(id);

    return portfolio.get();
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public String postPortfolio(@Valid @RequestBody Portfolio portfolio) {

    final var res = portfolioService.createPortfolio(portfolio);
    return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + res.getId();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

}
