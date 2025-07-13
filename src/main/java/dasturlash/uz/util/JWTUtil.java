package dasturlash.uz.util;

import dasturlash.uz.dto.JWTDTO;
import dasturlash.uz.enums.ProfileRoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {
    private static final int tokenLiveTime = 1000 * 3600 * 24;
    private static final String secretKey = "MzJbay1qaHVkb3ktY29tcGxleC1zZWNyZXQta2V5gfdkjjbvmluZw==";

    public static String encodeUsername(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        return Jwts
                .builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenLiveTime))
                .signWith(getSecretKey())
                .compact();
    }

    public static JWTDTO decodeUsername(String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String email = claims.getSubject();
        JWTDTO jwtDTO = new JWTDTO();
        jwtDTO.setEmail(email);
        return jwtDTO;
    }

    public static String encodeUsernameAndRole(String email, ProfileRoleEnum role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("role", role);
        return Jwts
                .builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenLiveTime))
                .signWith(getSecretKey())
                .compact();
    }

    public static JWTDTO decodeUsernameAndRole(String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String username = claims.getSubject();
        ProfileRoleEnum role = (ProfileRoleEnum) claims.get("role");
        JWTDTO jwtDTO = new JWTDTO();
        jwtDTO.setEmail(username);
        jwtDTO.setRole(role);
        return jwtDTO;
    }

    private static SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
