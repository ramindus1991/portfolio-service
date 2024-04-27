package com.portfolio.portfolioservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.portfolioservice.dto.UpdateRequest;
import com.portfolio.portfolioservice.exception.ValidationException;
import com.portfolio.portfolioservice.service.PortfolioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;


@WebMvcTest(value = PortfolioController.class)
class PortfolioControllerTest {

    @Autowired
    MockMvc mvc;

    String endpoint="/";

    @MockBean
    PortfolioService portfolioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getDetail() throws Exception {
        mvc.perform(get("/testId"))
                .andExpect(status().isOk());
    }


    @Test
    void getDetail_Internal_Exception() throws Exception {
        Mockito.when(portfolioService.getDetail(anyString())).thenThrow(new RuntimeException("Failed"));
        mvc.perform(get("/testId"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void getDetail_BusinessException() throws Exception {
        Mockito.when(portfolioService.getDetail(anyString())).thenThrow(new ValidationException("Failed"));
        mvc.perform(get("/testId"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void update() throws Exception {
        UpdateRequest updateRequest= UpdateRequest.builder().instrumentName("Test").tradeType("SELL").build();

        String objJackson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateRequest);
        mvc.perform(put("/testId")
                        .content(objJackson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void update_InternalException() throws Exception {
        Mockito.when(portfolioService.updatePortfolioValue(anyString(),any())).thenThrow(new RuntimeException("Failed"));
        UpdateRequest updateRequest= UpdateRequest.builder().instrumentName("Test").tradeType("SELL").build();

        String objJackson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateRequest);
        mvc.perform(put("/testId")
                        .content(objJackson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }


    @Test
    void update_Business_Exception() throws Exception {
        Mockito.when(portfolioService.updatePortfolioValue(anyString(),any())).thenThrow(new ValidationException("Failed"));
        UpdateRequest updateRequest= UpdateRequest.builder().instrumentName("Test").tradeType("SELL").build();

        String objJackson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateRequest);
        mvc.perform(put("/testId")
                        .content(objJackson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}