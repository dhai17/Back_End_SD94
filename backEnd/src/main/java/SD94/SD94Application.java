package SD94;

import SD94.entity.Staff;
import SD94.entity.security.Role;
import SD94.entity.security.UserRole;
import SD94.service.StaffService;
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
    private StaffService staffService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(SD94Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

//        System.out.println("starting code");
//        Staff staff = new Staff();
//        staff.setAddress("Ducnv-123");
//        staff.setGender(true);
//        staff.setPassword(this.bCryptPasswordEncoder.encode("123123"));
//        staff.setEmail("admin@fpt.com");
//        staff.setName("Duc Nguyen");
//
//        Role role1 = new Role();
//        role1.setRoleId(44L);
//        role1.setRoleName("ADMIN");
//
//        Set<UserRole> userRoleSet = new HashSet<>();
//        UserRole userRole = new UserRole();
//        userRole.setRole(role1);
//        userRole.setStaff(staff);
//        userRoleSet.add(userRole);
//
//        Staff user1 = staffService.createStaffV1(staff, userRoleSet);
//        System.out.println(user1.getUsername());

    }
}
