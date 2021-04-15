package fr.esgi.beanz.api.portfolio.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreatePortfolioDTO {
  
  @NotBlank()
  @NotNull()
  private String stock;

  @NotNull()
  private Integer shares;

  @NotNull()
  private double average;

  @NotBlank()
  @NotNull()
  private String currency;
}
