package org.example.porti.portfolio;

import org.example.porti.portfolio.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {
}
