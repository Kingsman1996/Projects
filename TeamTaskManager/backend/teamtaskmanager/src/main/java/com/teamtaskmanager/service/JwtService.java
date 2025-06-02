package com.teamtaskmanager.service;

import com.teamtaskmanager.entity.Account;
import com.teamtaskmanager.entity.Authority;
import com.teamtaskmanager.repository.AuthorityRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final AuthorityRepository authorityRepository;
    public static final String ROLE_CLAIM = "roleList";

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(Account account) {
        List<Authority> authorityList = authorityRepository.findByAccount(account);
        List<String> roleList = new ArrayList<>();
        for (Authority authority : authorityList) {
            roleList.add(authority.getRole().getName());
        }
        return Jwts.builder()
                .setSubject(account.getUsername())
                .claim(ROLE_CLAIM, roleList)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(signKey())
                .compact();
    }

    public SecretKey signKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractSubject(String token) {
        return extractToken(token).getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        return (extractSubject(token).equals(username) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractToken(token).getExpiration();
    }
}

