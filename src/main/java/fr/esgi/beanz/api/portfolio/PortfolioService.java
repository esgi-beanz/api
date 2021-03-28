package fr.esgi.beanz.api.portfolio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortfolioService {
  @Autowired
  private final PortfolioRepository portfolioRepository;

  @Transactional(readOnly = true)
  public List<Portfolio> getPortFolios() {
    return portfolioRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Optional<Portfolio> getPortfolio(Long id) {
    return portfolioRepository.findById(id);
  }

  @Transactional()
  public Portfolio createPortfolio(Portfolio portfolio) {
    return portfolioRepository.save(portfolio);
  }
}
