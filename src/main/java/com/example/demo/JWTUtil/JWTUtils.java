package com.example.demo.JWTUtil;

import com.example.demo.Entity.AdminEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repo.AdminRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.file.AccessDeniedException;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JWTUtils {
    @Autowired
    private AdminRepo adminRepo;
    private Key secretKey;
    public static long expiryDuration = 600000L;

    public JWTUtils() {
    }

    @PostConstruct
    public void init() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateJWT(AdminEntity admin) {
        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration;
        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);
        Claims claims = Jwts.claims().setIssuer(admin.getId().toString()).setIssuedAt(issuedAt).setExpiration(expiryAt);
        claims.put("adminID", admin.getAdminID());
        claims.put("password", admin.getPassword());
        claims.put("accessTags", admin.getAccessTags());
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, this.secretKey).compact();
    }

    public String generateJWTUser(UserEntity user) {
        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration;
        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);
        Claims claims = Jwts.claims().setIssuer(user.getId().toString()).setIssuedAt(issuedAt).setExpiration(expiryAt);
        claims.put("Email", user.getEmail());
        claims.put("password", user.getNtId());
        claims.put("accessTags", "USER");
        return Jwts.builder().setClaims(claims).signWith(this.secretKey).compact();
    }

    public Claims verify(String authorization) throws Exception {
        try {
            return (Claims)Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(authorization).getBody();
        } catch (Exception var3) {
            throw new AccessDeniedException("Access Denied");
        }
    }
}
