package SD94.secutity;

//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
//import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
//    private final SecretKey secretKey;
//
//    @Value("${app.jwtExpirationInMs}")
//    private int jwtExpirationInMs;

//    public String generateToken(Authentication authentication) {
//        User userDetails = (User) authentication.getPrincipal();
//        String userId = userDetails.getUsername(); // Đây là username, bạn có thể thay bằng ID nếu cần
//
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", userId); // Sử dụng ID hoặc username tùy theo yêu cầu của bạn
//        claims.put("username", userDetails.getUsername());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userId) // Sử dụng ID hoặc username tùy theo yêu cầu của bạn
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(secretKey, SignatureAlgorithm.HS512)
//                .compact();
//    }
//
//    public Long getUserIdFromToken(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody();
//
//        return Long.parseLong(claims.getSubject());
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//            return true;
//        } catch (SignatureException ex) {
//            // Xử lý lỗi khi token không hợp lệ
//        } catch (MalformedJwtException ex) {
//            // Xử lý lỗi khi token không đúng định dạng
//        } catch (ExpiredJwtException ex) {
//            // Xử lý lỗi khi token đã hết hạn
//        } catch (UnsupportedJwtException ex) {
//            // Xử lý lỗi khi token không được hỗ trợ
//        } catch (IllegalArgumentException ex) {
//            // Xử lý lỗi khi token rỗng
//        }
//        return false;
//    }
//
//    public JwtTokenProvider(@Value("${app.jwtSecret}") String jwtSecret) {
//        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//    }
}
