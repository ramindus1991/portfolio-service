package com.portfolio.portfolioservice.interceptors;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class AuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String tokon=request.getHeader("token");
            log.info("token {}",tokon);
            filterChain.doFilter(request,response);
            //TODO validate token with auth service
        }catch (Exception e){
            log.info("Autherization failed e {}",e.getMessage());
            throw new RuntimeException("Invalid token");
        }
    }
}
