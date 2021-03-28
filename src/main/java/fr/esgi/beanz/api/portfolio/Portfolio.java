package fr.esgi.beanz.api.portfolio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;

@Entity
@Getter
public class Portfolio {
  @Id
  @GeneratedValue
  private Long id;

  @NotEmpty
  private String stock;

  @NotEmpty
  private Integer shares;

  @NotEmpty
  private double average;

  @NotEmpty
  private String currency;
}
