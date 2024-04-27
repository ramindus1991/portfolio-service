package com.portfolio.portfolioservice.repo;

import com.portfolio.portfolioservice.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity,String> {
}
