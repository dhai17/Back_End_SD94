package SD94.controller.admin.khachHang;

import SD94.entity.khachHang.DiaChi;
import SD94.entity.khachHang.KhachHang;
import SD94.entity.security.Role;
import SD94.entity.security.UserRole;
import SD94.repository.khachHang.DiaChiRepository;
import SD94.repository.khachHang.KhachHangRepository;
import SD94.repository.role.RoleRepository;
import SD94.repository.role.UserRoleRepository;
import SD94.service.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/khachHang")
public class KhachHangController {
    @Autowired
    KhachHangService khachHangService;

    @Autowired
    KhachHangRepository customerRepository;

    @Autowired
    DiaChiRepository addressRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @GetMapping("/danhSach")
    public List<KhachHang> listCustomer() {
        return khachHangService.findAllCustomer();
    }

    @PostMapping(value = "/themMoi")
    public ResponseEntity<KhachHang> createCustomer(@RequestBody KhachHang khachHangCreate) {
        return khachHangService.createCustomer(khachHangCreate);
    }

    @PostMapping("/xoaKhachHang")
    public ResponseEntity<List<KhachHang>> deleteCustomer(@RequestBody KhachHang khachHang) {
        Long id = khachHang.getId();
        return khachHangService.deleteCustomer(id);
    }

    @GetMapping("/chinhSua/{id}")
    public KhachHang customerEdit(@PathVariable("id") Long id) {
        return customerRepository.findByID(id);
    }

    @PutMapping("/luu-chinh-sua")
    public ResponseEntity<KhachHang> saveUpdate(@RequestBody KhachHang khachHangUpdate) {
        return khachHangService.editCustomer(khachHangUpdate);
    }

    @RequestMapping("/timKiem={search}")
    public List<KhachHang> searchAllCustomer(@PathVariable("search") String search) {
        return khachHangService.searchAllCustomer(search);
    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<KhachHang> searchDateCustomer(@PathVariable("searchDate") String searchDate) {
        return khachHangService.searchDateCustomer(searchDate);
    }

    @RequestMapping("/themDiaChi")
    public String addAddress(@RequestParam("dia_chi") String dia_chi,
                             @RequestParam("khach_hang_id") long khach_hang_id) {
        Optional<KhachHang> optionalCustomer = customerRepository.findById(khach_hang_id);
        if (optionalCustomer.isPresent()) {
            KhachHang khachHang = optionalCustomer.get();
            DiaChi diaChi = new DiaChi();
            diaChi.setKhachHang(khachHang);
            diaChi.setDiaChi(dia_chi);
            diaChi.setDiaChiMacDinh(false);
            addressRepository.save(diaChi);
        }
        return "add address done";
    }

    @PostMapping("/them-dia-chi-mac-dinh")
    public String setAddress(@RequestParam("dia_chi_id") long dia_chi_id,
                             @RequestParam("khach_hang_id") long khach_hang_id) {
        List<DiaChi> diaChis = addressRepository.findByCustomerID(khach_hang_id);
        for (DiaChi diaChi : diaChis) {
            diaChi.setDiaChiMacDinh(false);
            addressRepository.save(diaChi);
        }

        DiaChi diaChi = addressRepository.findbyCustomerAndID(khach_hang_id, dia_chi_id);
        diaChi.setDiaChiMacDinh(true);
        addressRepository.save(diaChi);
        return "true";
    }

    @PostMapping("/register")
    public ResponseEntity Register(@RequestBody KhachHang create) {
        KhachHang khachHangCheckEmail = customerRepository.findByEmail(create.getEmail());
        KhachHang khachHangCheckSDT = customerRepository.findBySDT(create.getSoDienThoai());
        if(khachHangCheckEmail != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Trùng email");
        } else if (khachHangCheckSDT != null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Trùng số điện thoại");
        }else {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy");
            inputDateFormat.setTimeZone(TimeZone.getTimeZone("ICT"));
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String ngaySinhFormatted = outputDateFormat.format(create.getNgaySinh());
            KhachHang khachHang = new KhachHang();
            khachHang.setHoTen(create.getHoTen());
            khachHang.setNgaySinh(new Date(ngaySinhFormatted));
            khachHang.setSoDienThoai(create.getSoDienThoai());
            khachHang.setDiaChi(create.getDiaChi());
            khachHang.setEmail(create.getEmail());
            khachHang.setMatKhau(passwordEncoder.encode(create.getMatKhau()));
            customerRepository.save(khachHang);

            Role role = roleRepository.find("CUSTOMER");

            Set<UserRole> userRoleSet = new HashSet<>();
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setKhachHang(khachHang);
            userRoleSet.add(userRole);

            userRoleRepository.save(userRole);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
}
