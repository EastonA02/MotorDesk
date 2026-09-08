package com.Easton.motordesk_backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

//mark class as Spring managed bean(object)
@Component
public class JwtUtil {

    //inject secret key from environment variables
    @Value("${jwt.secret}")
    private String key;

    private SecretKey secretKey;

    //Create secret key object
    @PostConstruct
    public void init() {
        // Converts the raw string into bytes
        byte[] keyBytes = Decoders.BASE64.decode(key);

        //Pass raw bytes to build the SecretKey Object
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    //generate token
    public String generateToken(String username){
        Date now = new Date();

        //key expires after 2 hours
        Date expiration = new Date(now.getTime() + 7200000);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    //validate token from incoming requests
    public boolean validateToken(String token, String expectedUsername){
        //extract token
        var claims = Jwts.parser() // set up parser to decode token
                .verifyWith(this.secretKey) // tell it what key to check the signature against
                .build() // finalize parser config into a usable object
                .parseSignedClaims(token) //actually decode + verify the token string is not tampered with or signed it with a different key
                .getPayload(); // pull out the claims (subject/username, expiration, etc.) so your code below can read them

        String username = claims.getSubject();
        boolean isExpired = claims.getExpiration().before(new Date());

        return (username.equals(expectedUsername) && !isExpired);
    }

}
