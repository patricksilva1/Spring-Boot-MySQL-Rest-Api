package dev.patricksilva.crud.models.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import dev.patricksilva.crud.models.security.services.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${caixa-ada.app.jwtSecret}")
    private String jwtSecret;

    @Value("${caixa-ada.app.jwtExpirationMs}")
    private long jwtExpirationMs;

    // Gera o token JWT usando a API da versão 0.12.0
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        // Cria os claims utilizando Jwts.claims()
        Claims claims = Jwts.claims().build();

        // Em vez de setSubject(), insere o subject no mapa:
        claims.put(Claims.SUBJECT, userPrincipal.getEmail());
        claims.put("id", userPrincipal.getId());
        claims.put("firstName", userPrincipal.getFirstName());
        claims.put("lastName", userPrincipal.getLastName());
        claims.put("cpf", userPrincipal.getCpf());
        claims.put("phone", userPrincipal.getPhone());
        // Adicione outros campos conforme necessário

        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // Obtém a chave de assinatura a partir do secret
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // Extrai os claims do token utilizando a API antiga
    public Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            logger.error("JWT token expirado: {}", e.getMessage());
            throw e;
        } catch (SignatureException e) {
            logger.error("Assinatura JWT inválida: {}", e.getMessage());
            throw e;
        } catch (MalformedJwtException e) {
            logger.error("JWT token inválido: {}", e.getMessage());
            throw e;
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token não suportado: {}", e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string está vazia: {}", e.getMessage());
            throw e;
        }
    }

    // Extrai o subject (e-mail) do token
    public String getEmailFromJwtToken(String token) {
        return getAllClaimsFromToken(token).get(Claims.SUBJECT, String.class);
    }

    // Valida o token: se conseguir extrair os claims, o token é considerado válido
    public boolean validateJwtToken(String token) {
        try {
            getAllClaimsFromToken(token);
            return true;
        } catch (Exception e) {
            logger.error("Falha na validação do token JWT: {}", e.getMessage());
        }
        return false;
    }
}
