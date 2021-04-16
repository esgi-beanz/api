package fr.esgi.beanz.api.portfolio;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
