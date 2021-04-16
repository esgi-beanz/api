package fr.esgi.beanz.api.portfolio.dto;

import javax.validation.constraints.PositiveOrZero;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public class UpdatePortfolioDTO {
  @Nullable
  @Getter
  private String stock;

  @Nullable
  @PositiveOrZero(message = "average must be 0 or greater")
  @Getter
  private Integer shares;

  @Nullable
  @PositiveOrZero(message = "average must be 0 or greater")
  @Getter
  private Double average;

  @Nullable
  @Getter
  private String currency;
}
