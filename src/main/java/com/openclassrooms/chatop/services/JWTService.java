package com.openclassrooms.chatop.services;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class JWTService {
    private final JwtEncoder jwtEncoder;

    public String generateToken(@Nonnull String email) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("chatop")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(email)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(SignatureAlgorithm.RS256).build();
        JwtEncoderParameters params = JwtEncoderParameters.from(jwsHeader, claims);

        Jwt encoded = this.jwtEncoder.encode(params);
        return encoded.getTokenValue();
    }
}
