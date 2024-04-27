package com.portfolio.portfolioservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummeryResponse {
    private String customerId ;//	(guid)
    private String customerName;//	(string)
    private String portfolioNumber;//	(string)
    private BigDecimal portfolioValue;//	(BigDecimal)
    private double currentPerformance;//	(double)
    private String investmentStrategy;//
}
