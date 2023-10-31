package SD94.controller.admin.staff;

import SD94.entity.nhanVien.NhanVien;
import SD94.repository.StaffRepository;
import SD94.service.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class StaffController {

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    StaffRepository staffRepository;


    @RequestMapping("/api/staff/list")
    public List<NhanVien> listStaff() {
        return nhanVienService.findAllStaff();
    }

    @PostMapping("/api/staff/createStaff")
    public ResponseEntity<NhanVien> createStaff(@RequestBody NhanVien staffCreate) {
        return nhanVienService.createStaff(staffCreate);
    }

    @PutMapping("/api/staff/deleteStaff={id}")
    public ResponseEntity<List<NhanVien>> deleteStaff(@PathVariable("id") Long id) {
        return nhanVienService.deleteStaff(id);
    }

//    @GetMapping("/api/staff/edit/staffID={id}")
//    public Staff editStaff(@PathVariable("id") Long id) {
//        return staffRepository.findByID(id);
//    }


    //Update
    @PutMapping("/api/staff/saveUpdate")
    public ResponseEntity<NhanVien> saveUpdate(@RequestBody NhanVien staffEdit) {
        return nhanVienService.editStaff(staffEdit);
    }


    @RequestMapping("/api/staff/search={search}")
    public List<NhanVien> searchAllStaff(@PathVariable("search") String search) {
        return nhanVienService.searchAllStaff(search);
    }

    @RequestMapping("/api/staff/searchDate={searchDate}")
    public List<NhanVien> searchDateStaff(@PathVariable("searchDate") String searchDate) {
        return nhanVienService.searchDateStaff(searchDate);
    }

    @RequestMapping("/api/staff/edit/staffID={id}")
    public NhanVien editStaff (@PathVariable("id") Long id){
        return staffRepository.findByID(id);
    }

}
