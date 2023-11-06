package SD94.config;
import SD94.dto.UserDTO;
import SD94.entity.khachHang.KhachHang;
import SD94.entity.nhanVien.NhanVien;
import SD94.entity.security.UserRole;
import SD94.repository.khachHang.KhachHangRepository;
import SD94.repository.nhanVien.NhanVienRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Component
public class JwtUtils {
    private final SecretKey secretKey;

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;
    public JwtUtils(@Value("${app.jwtSecret}") String jwtSecret) {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

//    public String generateToken(NhanVien nhanVien) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("hoTen", nhanVien.getHoTen());
//        claims.put("email", nhanVien.getEmail());
//        claims.put("role", nhanVien.getUserRoles());
//        return createToken(claims);
//    }
//
//    private String createToken(Map<String, Object> claims) {
//
//        return Jwts.builder().setClaims(claims).setSubject(claims.toString()).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
//    }
public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = null;
    UserDTO userDTO = null;
    NhanVien nhanVien = this.nhanVienRepository.findByEmail(userDetails.getUsername());
    if (nhanVien != null) {
        userDTO = new UserDTO();
        userDTO.setEmail(nhanVien.getEmail());
        userDTO.setName(nhanVien.getHoTen());
        userDTO.setMatKhau(nhanVien.getMatKhau());
        userDTO.setRole(nhanVien.getUserRoles());
        claims = new HashMap<>();
        claims.put("hoTen", userDTO.getName());
        claims.put("email", userDTO.getEmail());
        claims.put("role", getRolesAsString(userDTO.getRole()));
    } else {
        KhachHang khachHang = khachHangRepository.findByEmail(userDetails.getUsername());
        if (khachHang != null) {
            userDTO = new UserDTO();
            userDTO.setEmail(khachHang.getEmail());
            userDTO.setName(khachHang.getHoTen());
            userDTO.setMatKhau(khachHang.getMatKhau());
            userDTO.setRole(khachHang.getUserRoles());
            claims = new HashMap<>();
            claims.put("hoTen", userDTO.getName());
            claims.put("email", userDTO.getEmail());
            claims.put("role", getRolesAsString(userDTO.getRole()));
        } else {
            throw new UsernameNotFoundException("Thông tin đăng nhập không hợp lệ!!");
        }
    }
    return createToken(claims);
}

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("email").toString()) // Đặt Subject thành email hoặc một trường unique khác
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    private String getRolesAsString(Set<UserRole> userRoles) {
        List<String> roles = new ArrayList<>();
        for (UserRole role : userRoles) {
            roles.add(role.getRole().getRoleName());
        }
        return String.join(",", roles); // Trả về chuỗi các Role, có thể sử dụng dấu phân cách phù hợp
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
