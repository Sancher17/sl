package com.cafe.security;


import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SecuritiFilter implements Filter {


    @Autowired
    private ITokenRepository tokenRepository;


    public void destroy() {
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "*");
        String token = req.getHeader("Authorization");
        String login = tokenRepository.getLogin(token);
        if (login != null) {
            chain.doFilter(request, response);
        }
    }


    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
}
