package com.cafe.services;

import com.cafe.api.services.IJwtService;
import com.cafe.api.services.IUserService;
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

import static java.time.ZoneOffset.UTC;

@Component
public class JwtService implements IJwtService {

    private static final Logger log = Logger.getLogger(JwtService.class);

    @Autowired
    private IUserService userService;

    private String secretKey = "theVerySecretKey";

    public String tokenFor(UserDto userDto) {
        Date expiration = Date.from(LocalDateTime.now(UTC).plusHours(5).toInstant(UTC));
        return Jwts.builder()
                .setSubject(userDto.getLogin())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public UserDto verify(String token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        return new UserDto(userService.getByNameLogin(claims.getBody().getSubject()));
    }
}
