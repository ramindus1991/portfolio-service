package com.portfolio.portfolioservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@Table(name="portfolio")
public class PortfolioEntity {
    @Id
    private String customerId ;//	(guid)
    private String customerName;//	(string)
    private String portfolioNumber;//	(string)
    private BigDecimal portfolioValue;//	(BigDecimal)
    private double currentPerformance;//	(double)
    private String investmentStrategy;//	(enum)
}
