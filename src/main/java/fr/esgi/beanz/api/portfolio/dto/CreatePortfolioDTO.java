package fr.esgi.beanz.api.portfolio.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;

public class CreatePortfolioDTO {
  
  @NotBlank()
  @NotNull()
  @Valid()
  private String stock;

  @NotNull()
  @Valid()
  private Integer shares;

  @NotNull()
  @Valid()
  private double average;

  @NotBlank()
  @NotNull()
  @Valid()
  private String currency;
}
