package com.openclassrooms.chatop.configuration;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.openclassrooms.chatop.utils.KeyUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class JwtConfig {

    @Bean
    public JwtEncoder jwtEncoder() throws Exception {
        RSAPrivateKey privateKey = KeyUtils.readPrivateKey("keys/private.pem");
        RSAPublicKey publicKey = KeyUtils.readPublicKey("keys/public.pem");

        JWK jwk = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID("rsa-key") // arbitrary id
                .build();
        JWKSet jwkSet = new JWKSet(jwk);
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(jwkSet);

        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JwtDecoder jwtDecoder() throws Exception {
        RSAPublicKey publicKey = KeyUtils.readPublicKey("keys/public.pem");
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }
}
