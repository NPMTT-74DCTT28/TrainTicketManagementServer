package com.nmptt.ticketapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    @Value("${jwt.secret-key}")
    private String secretKey;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractMaNhanVien(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(String maNhanVien, String vaiTro) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("vaiTro", vaiTro);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(maNhanVien)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, String maNhanVien) {
        final String extractedMaNhanVien = extractMaNhanVien(token);
        return extractedMaNhanVien.equals(maNhanVien) && !isTokenExpired(token);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractVaiTro(String token) {
        return extractClaim(token, claims -> claims.get("vaiTro").toString());
    }
}
