package com.aritra.healthbridge.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationMs;

    // Converts the base64 secret STRING into a usable cryptographic KEY object.
    // HMAC math needs raw bytes wrapped in a SecretKey, not a plain string.
    // Both signing (login) and verifying (every request) call this, so the key is always identical.

    private SecretKey getSignedKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // ---------------------------------------------------------------
    // JOB 1: GENERATE A TOKEN  (runs ONCE, at login)
    // ---------------------------------------------------------------
    public String generateToken(String userName){
        Date now = new Date();
        Date expiry = new Date(now.getTime()+expirationMs);

        return Jwts.builder()
                .subject(userName)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSignedKey()) // THE SIGNING MOMENT: payload + secret -> signature
                .compact(); // squash header.payload.signature into one final String

    }

    // ---------------------------------------------------------------
    // JOB 2: VALIDATE + READ A TOKEN  (runs on EVERY later request)
    // ---------------------------------------------------------------

    // Public helper: get the username out of a token.
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    // Public helper: get the expiry date out of a token.
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);  // "from the claims, give me the expiration"
    }
    // The core engine. Parses + verifies the token, then pulls out ONE field.
    // <T> = generic: the field could be a String (username), a Date (expiry), etc.
    // The "resolver" decides WHICH field to return - passed in by the callers above.
    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parser()       // start a token reader
                .verifyWith(getSignedKey()) // tell it which key to verify the signature with
                .build()    // finish configuring the parser
                .parseSignedClaims(token)   // CRITICAL: recompute signature & compare;
                                            // THROWS here if tampered OR expired
                .getPayload();   // token trusted -> hand back the payload (claims)

        return resolver.apply(claims);      // pull out the one field the caller asked for
        // this is also a java concept ->  resolver applies or sends what was asked as a part of T(either username or date )
    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public boolean isTokenValid(String token,String expectedUsername){
        final String userName = extractUsername(token);
        return userName.equals(expectedUsername) && ! isTokenExpired(token);
    }
}
