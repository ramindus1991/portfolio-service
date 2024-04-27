package com.portfolio.portfolioservice.service.impl;

import com.portfolio.portfolioservice.dto.UpdateRequest;
import com.portfolio.portfolioservice.entity.PortfolioEntity;
import com.portfolio.portfolioservice.exception.ValidationException;
import com.portfolio.portfolioservice.repo.PortfolioRepository;
import com.portfolio.portfolioservice.service.PortfolioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PortfolioServiceImplTest {
    @InjectMocks
    PortfolioServiceImpl portfolioService;

    @Mock
    PortfolioRepository portfolioRepository;

    @Test
    void getDetail() {
        when(portfolioRepository.findById(eq("test1")))
                .thenReturn(Optional.of(PortfolioEntity.builder().build()));
        assertDoesNotThrow(() -> portfolioService.getDetail("test1"));
    }

    @Test
    void getDetail_Exception() {
        when(portfolioRepository.findById(eq("test1")))
                .thenThrow(new ValidationException("Invalid"));
        assertThrows(ValidationException.class,() -> portfolioService.getDetail("test1"));
    }

    @Test
    void updatePortfolioValue() {
        when(portfolioRepository.findById(eq("testId")))
                .thenReturn(Optional.of(PortfolioEntity.builder().portfolioValue(BigDecimal.TEN).build()));
        assertDoesNotThrow(()-> portfolioService.updatePortfolioValue("testId", UpdateRequest.builder().instrumentName("Test").tradeType("SELL").units(BigDecimal.TEN).build()));

    }

    @Test
    void updatePortfolioValue_Exception_When_Invalid_Units_Provided() {
        assertThrows(ValidationException.class,()-> portfolioService.updatePortfolioValue("testId", UpdateRequest.builder().instrumentName("Test").tradeType("SELL").build()));

    }
}