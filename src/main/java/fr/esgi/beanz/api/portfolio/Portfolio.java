package fr.esgi.beanz.api.portfolio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Getter;

@Entity
@Getter
@ConstructorBinding
public class Portfolio {
  @Id
  @GeneratedValue
  private Long id;

  @NotNull(message = "Please provide the stock")
  private String stock;

  @NotNull(message = "Please provide the number of shares")
  private Integer shares;

  @NotNull(message = "Please provide the average")
  private double average;

  @NotBlank(message = "Please provide the currency")
  private String currency;
}
