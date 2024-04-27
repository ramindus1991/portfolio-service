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
        portfolio.setPortfolioValue(portfolio.getPortfolioValue().add(updateRequest.getTradeValue()));
        portfolioRepository.save(portfolio);
        return "Success";
    }

    private PortfolioEntity getPortfolio(String id) {
        return portfolioRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Portfolio Not found"));
    }
}
