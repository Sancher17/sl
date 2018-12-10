package com.cafe.security.auth;

import com.cafe.dto.user.UserDto;
import com.cafe.security.exceptions.JwtAuthenticationException;
import com.cafe.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            UserDto userDto = jwtService.verify((String) authentication.getCredentials());
            return new JwtAuthenticatedProfile(userDto);
        } catch (Exception e) {
            throw new JwtAuthenticationException("Не удалось верефицировать токен ", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}
