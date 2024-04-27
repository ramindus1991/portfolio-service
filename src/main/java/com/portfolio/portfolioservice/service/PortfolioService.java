package com.portfolio.portfolioservice.service;

import com.portfolio.portfolioservice.dto.SummeryResponse;
import com.portfolio.portfolioservice.dto.UpdateRequest;

public interface PortfolioService {

    SummeryResponse getDetail(String id);

    String updatePortfolioValue(String id,UpdateRequest updateRequest);
}
