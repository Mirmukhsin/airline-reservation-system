package lesson_10.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTTokenUtil {
    private static final String SECRET_KEY = "7134743777217A25432A462D4A614E645267556B58703272357538782F413F44";

    // Generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .issuer("Panda")
                .expiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .signWith(signKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    // Check token
    public boolean isValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Username from token
    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    // Get Claims from token
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }

    // Get key
    private Key signKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
