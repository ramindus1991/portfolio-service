package com.portfolio.portfolioservice.service;

import com.portfolio.portfolioservice.dto.SummeryResponse;

public interface PortfolioService {

    SummeryResponse getDetail(String id);
}
