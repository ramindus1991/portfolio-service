package com.portfolio.portfolioservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateRequest {
    private BigDecimal tradeValue;
}
