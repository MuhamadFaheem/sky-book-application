package com.miniproject.skybook.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.miniproject.skybook.model.entity.AppUser;
import com.miniproject.skybook.model.entity.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JWTUtils {
    @Value("${jwt_secret}")
    private String JWTsecret;
    //private String JWTsecret = "Open Sesame";
    private String appName = "SkyBook Apps";
    @Value("${jwt_expired}")
    private long JWTexpired;

    public String generatedToken(AppUser appUser){
        List<String> roles = new ArrayList<>();
        Algorithm algorithm = Algorithm.HMAC256(JWTsecret.getBytes(StandardCharsets.UTF_8));
        return JWT.create()
                .withIssuer(appName)
                .withSubject(appUser.getId())
                .withExpiresAt(Instant.now().plusSeconds(JWTexpired))
                .withIssuedAt(Instant.now())
                .withClaim("role", appUser.getRole().name())//payload
                .sign(algorithm);
    }

    public Boolean verifyJWTToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(JWTsecret.getBytes(StandardCharsets.UTF_8));
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getIssuer().equals(appName);
    };

    public Map<String,String> getUserInfobyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWTsecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("userId", decodedJWT.getSubject());
            userInfo.put("role", decodedJWT.getClaim("role").asString());

            return userInfo;
        }catch (JWTVerificationException e){
            throw new RuntimeException();
        }
    }
}
