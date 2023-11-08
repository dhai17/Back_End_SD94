package SD94.controller.security;

import SD94.config.JwtUtils;
import SD94.dto.UserDTO;
import SD94.entity.khachHang.KhachHang;
import SD94.entity.nhanVien.NhanVien;
import SD94.helper.UserNotFoundException;
import SD94.model.request.JwtRequest;
import SD94.model.response.JwtResponse;
import SD94.repository.nhanVien.NhanVienRepository;
import SD94.service.impl.StaffDetailsServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StaffDetailsServiceImpl staffDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    NhanVienRepository nhanVienRepository;

    // generte token
    @SneakyThrows
    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
            UserDetails userDetails = this.staffDetailsService.loadUserByUsername(jwtRequest.getEmail());

            String token = this.jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled ");
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials");
        }
    }

    // return the details of current user
    @GetMapping("/current-user")
    public NhanVien getCurrentUser(Principal principal) {
        return (NhanVien) this.staffDetailsService.loadUserByUsername(principal.getName());
    }
}
