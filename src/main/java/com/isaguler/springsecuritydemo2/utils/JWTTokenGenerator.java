package com.isaguler.springsecuritydemo2.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTokenGenerator {

    @Value("${jwt.variables.key}")
    private String jwtSecret;

    @Value("${jwt.variables.issuer}")
    private String jwtIssuer;

    @Value("${jwt.variables.expirationtime.minutes}")
    private long expirationTimeAsMinutes;

    public String generateJWTToken(Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (expirationTimeAsMinutes*60*1000)))
                .withIssuer(jwtIssuer)
                .withClaim("", "")
                .sign(Algorithm.HMAC256(jwtSecret.getBytes()));
    }

    public DecodedJWT verifyJWT(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());

        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        try {
            return jwtVerifier.verify(jwtToken);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
