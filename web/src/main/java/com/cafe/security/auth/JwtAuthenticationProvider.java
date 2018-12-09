package com.cafe.security.auth;

import com.cafe.dto.user.UserDto;
import com.cafe.security.exceptions.JwtAuthenticationException;
import com.cafe.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("authenticate ------ " + this.getClass().getSimpleName());
        try {
            Optional<UserDto> possibleProfile =
                    jwtService.verify((String) authentication.getCredentials());
            return new JwtAuthenticatedProfile(possibleProfile.get());
        } catch (Exception e) {
            throw new JwtAuthenticationException("Failed to verify token", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}
