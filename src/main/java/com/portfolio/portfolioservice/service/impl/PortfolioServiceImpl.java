package com.portfolio.portfolioservice.service.impl;

import com.portfolio.portfolioservice.dto.SummeryResponse;
import com.portfolio.portfolioservice.entity.PortfolioEntity;
import com.portfolio.portfolioservice.exception.ValidationException;
import com.portfolio.portfolioservice.repo.PortfolioRepository;
import com.portfolio.portfolioservice.service.PortfolioService;
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
        PortfolioEntity portfolio=portfolioRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Portfolio Not found"));

        SummeryResponse summeryResponse=new SummeryResponse();
        BeanUtils.copyProperties(portfolio,summeryResponse);

        return summeryResponse;
    }
}
