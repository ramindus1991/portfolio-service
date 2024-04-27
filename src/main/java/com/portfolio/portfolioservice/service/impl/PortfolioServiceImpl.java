package com.portfolio.portfolioservice.service.impl;

import com.portfolio.portfolioservice.dto.SummeryResponse;
import com.portfolio.portfolioservice.dto.UpdateRequest;
import com.portfolio.portfolioservice.entity.PortfolioEntity;
import com.portfolio.portfolioservice.exception.ValidationException;
import com.portfolio.portfolioservice.repo.PortfolioRepository;
import com.portfolio.portfolioservice.service.PortfolioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Override
    public SummeryResponse getDetail(String id) {
        PortfolioEntity portfolio = getPortfolio(id);

        SummeryResponse summeryResponse=new SummeryResponse();
        BeanUtils.copyProperties(portfolio,summeryResponse);

        return summeryResponse;
    }

    @Override
    @Transactional
    //Assumption no concurrent transactions
    //For same portfolio there won't be concurrent update
    public String updatePortfolioValue(String id, UpdateRequest updateRequest) {
        PortfolioEntity portfolio = getPortfolio(id);
        BigDecimal newValue=calCulatePortfolioValue(portfolio.getPortfolioValue(),updateRequest);
        portfolio.setPortfolioValue(newValue);
        portfolioRepository.save(portfolio);
        return "Success";
    }

    private BigDecimal calCulatePortfolioValue(BigDecimal currentValue,UpdateRequest updateRequest){
        //TODO Get stock unit value from external APIby passing instrument name
        //TODO For now its hard coded to 100
        BigDecimal stockUnitValue=BigDecimal.valueOf(100);
        BigDecimal newValue= stockUnitValue.multiply(updateRequest.getUnits());
        BigDecimal netValue=updateRequest.getTradeType().equals("SELL")?currentValue.subtract(newValue):currentValue.add(newValue);
        return netValue;
    }

    private PortfolioEntity getPortfolio(String id) {
        return portfolioRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Portfolio Not found"));
    }
}
