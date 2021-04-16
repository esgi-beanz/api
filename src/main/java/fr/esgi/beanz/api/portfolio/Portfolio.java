package fr.esgi.beanz.api.portfolio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@ConstructorBinding
@NoArgsConstructor
public class Portfolio {
  @Id
  @GeneratedValue
  @Getter
  @Setter
  private Long id;

  @NotBlank(message = "Please provide the stock")
  @Getter
  @Setter
  private String stock;

  @NotNull(message = "Please provide the number of shares")
  @PositiveOrZero(message = "average must be 0 or greater")
  @Getter
  @Setter
  private Integer shares;

  @NotNull(message = "Please provide the average")
  @PositiveOrZero(message = "average must be 0 or greater")
  @Getter
  @Setter
  private Double average;

  @NotBlank(message = "Please provide the currency")
  @Getter
  @Setter
  private String currency;
}
