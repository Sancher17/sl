package com.cafe.security.auth;

import com.cafe.controllers.login.LoginController;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.SecurityContextServerWebExchange;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtAuthFilter implements Filter {

    private static final Logger log = Logger.getLogger(JwtAuthFilter.class);

    public JwtAuthFilter() {
        log.info("bean created");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter");
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String authorization = servletRequest.getHeader("Authorization");
        if (authorization != null) {
            JwtAuthToken token = new JwtAuthToken(authorization.replaceAll("Bearer ", ""));
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
