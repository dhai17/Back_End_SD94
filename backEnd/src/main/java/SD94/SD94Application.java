package SD94;

import SD94.entity.nhanVien.NhanVien;
import SD94.entity.security.Role;
import SD94.entity.security.UserRole;
import SD94.repository.nhanVien.NhanVienRepository;
import SD94.service.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashSet;
import java.util.Set;

@EnableWebMvc
@SpringBootApplication
public class SD94Application implements CommandLineRunner {
    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SD94Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        NhanVien nhanVien = null;
//        nhanVien = nhanVienRepository.findByEmail("admin@gmail.com");
//        if (nhanVien != null) {
//            String account = nhanVien.getEmail();
//            System.out.println(account);
//        } else {
//            nhanVien = new NhanVien();
//            nhanVien.setDiaChi("Hà Nội");
//            nhanVien.setGioiTinh(true);
//            nhanVien.setMatKhau(this.bCryptPasswordEncoder.encode("123123"));
//            nhanVien.setEmail("admin@gmail.com");
//            nhanVien.setHoTen("SD94");
//
//            Role role = new Role();
//            role.setRoleId(44L);
//            role.setRoleName("ADMIN");
//
//            Set<UserRole> userRoleSet = new HashSet<>();
//            UserRole userRole = new UserRole();
//            userRole.setRole(role);
//            userRole.setStaff(nhanVien);
//            userRoleSet.add(userRole);
//
//
//            NhanVien user = nhanVienService.createStaffV1(nhanVien, userRoleSet);
//            System.out.println(user.getUsername());
//        }
    }
}
