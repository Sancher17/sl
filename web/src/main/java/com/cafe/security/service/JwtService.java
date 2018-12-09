package com.cafe.security.service;

import com.cafe.dto.user.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static java.time.ZoneOffset.UTC;

@Component
public class JwtService {

    private static final Logger log = Logger.getLogger(JwtService.class);

    @Autowired
    private ProfileService profileService;

    private String secretKey = "theVerySecretKey";

    public String tokenFor(UserDto userDto) {
        Date expiration = Date.from(LocalDateTime.now(UTC).plusMinutes(5).toInstant(UTC));
        return Jwts.builder()
                .setSubject(userDto.getLogin())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }


    public Optional<UserDto> verify(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return profileService.minimal(claims.getBody().getSubject());
    }
}
