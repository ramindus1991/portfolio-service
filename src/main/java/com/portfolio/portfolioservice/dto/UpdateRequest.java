package com.portfolio.portfolioservice.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateRequest {
    private String instrumentName;
    private String tradeType;
    private BigDecimal units;
}
