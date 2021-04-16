package fr.esgi.beanz.api.portfolio;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.esgi.beanz.api.exceptions.HttpErrorException;
import fr.esgi.beanz.api.portfolio.dto.UpdatePortfolioDTO;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

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


    if (!portfolio.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This portfolio does not exist.");
    }

    return portfolio.get();
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public String postPortfolio(@Valid @RequestBody Portfolio portfolio) {

    final var res = portfolioService.createPortfolio(portfolio);
    return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + res.getId();
  }

  @PutMapping(value = "/{id}")
  public String updatePortfolio(@PathVariable("id") long id,  @Valid @RequestBody UpdatePortfolioDTO updatedParams) {
    final var existingPortfolio = portfolioService.getPortfolio(id);

    if (!existingPortfolio.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This portfolio does not exist.");
    }

    final var portfolio = existingPortfolio.get();

    if (updatedParams.getAverage() != null) portfolio.setAverage(updatedParams.getAverage());
    if (updatedParams.getShares() != null) portfolio.setShares(updatedParams.getShares());
    if (updatedParams.getStock() != null) portfolio.setStock(updatedParams.getStock());
    if (updatedParams.getCurrency() != null) portfolio.setCurrency(updatedParams.getCurrency());



    portfolioService.updatePortfolio(new Portfolio(
      id,
      portfolio.getStock(),
      portfolio.getShares(),
      portfolio.getAverage(),
      portfolio.getCurrency()
    ));

    return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + id;
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePortfolio(@PathVariable("id") long id) {
    final var portfolio = this.portfolioService.getPortfolio(id);

    if (!portfolio.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This portfolio does not exist.");
    }

    this.portfolioService.deletePortfolio(portfolio.get());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public HttpErrorException handleValidationExceptions(MethodArgumentNotValidException ex) {
    return fr.esgi.beanz.api.error.ExceptionHandler.handleValidationExceptions(ex);
  }

}
