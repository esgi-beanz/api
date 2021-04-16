package fr.esgi.beanz.api.security;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
    private static final String AUTHORITIES_KEY = "auth";

    private final long tokenValidityInMilliseconds = Duration.ofMinutes(5).getSeconds() * 1000;

    private final byte[] secret;

    public JwtTokenProvider(@Value("${security.token.secret}") CharSequence secret) {
        this.secret = secret.toString().getBytes();
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, secret).setExpiration(validity).compact();
    }

    public Authentication getAuthentication(String token) {
        final var claims = parseToken(token).getBody();

        final var authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .filter(role -> !role.isBlank()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        final var principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            parseToken(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Jws<Claims> parseToken(String authToken) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
    }
}