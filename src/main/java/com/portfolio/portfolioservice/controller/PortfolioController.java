package com.portfolio.portfolioservice.controller;

import com.portfolio.portfolioservice.dto.SummeryResponse;
import com.portfolio.portfolioservice.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PortfolioController {
    private static final Logger log = LoggerFactory.getLogger(PortfolioController.class);
    private final PortfolioService portfolioService;

    @GetMapping("/{id}")
    public ResponseEntity<SummeryResponse> getDetail(@PathVariable String id){
        log.info("Request received for get getDetail portfolio-id ={}",id);
        return new ResponseEntity<>(portfolioService.getDetail(id), HttpStatus.OK);
    }



}