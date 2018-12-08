package com.cafe.security.service;

import com.cafe.security.auth.SecretKeyProvider;
import com.cafe.security.domain.MinimalProfile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static java.time.ZoneOffset.UTC;

@Component
public class JwtService {
//    private static final String ISSUER = "in.sdqali.jwt";
    private static final String ISSUER = "com.cafe.jwt.key";

    private static final Logger log = Logger.getLogger(JwtService.class);

    @Autowired
    private SecretKeyProvider secretKeyProvider;
    @Autowired
    private ProfileService profileService;

    public JwtService() {
        log.info("bean created");
    }

    private byte[] secretKey = new byte []{'s','4','y' };

    public String tokenFor(MinimalProfile minimalProfile) {
//        byte[] secretKey = secretKeyProvider.getKey();
        Date expiration = Date.from(LocalDateTime.now(UTC).plusHours(2).toInstant(UTC));
        return Jwts.builder()
                .setSubject(minimalProfile.getUsername())
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Optional<MinimalProfile> verify(String token) throws IOException, URISyntaxException {
//        byte[] secretKey = secretKeyProvider.getKey();
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return profileService.minimal(claims.getBody().getSubject());
    }
}
