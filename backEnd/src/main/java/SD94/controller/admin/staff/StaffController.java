package SD94.controller.admin.staff;

import SD94.entity.Staff;
import SD94.repository.StaffRepository;
import SD94.service.service.StaffService;
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
    StaffService staffService;
    @Autowired
    StaffRepository staffRepository;


    @RequestMapping("/api/staff/list")
    public List<Staff> listStaff() {
        return staffService.findAllStaff();
    }

    @PostMapping("/api/staff/createStaff")
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staffCreate) {
        return staffService.createStaff(staffCreate);
    }

    @PutMapping("/api/staff/deleteStaff={id}")
    public ResponseEntity<List<Staff>> deleteStaff(@PathVariable("id") Long id) {
        return staffService.deleteStaff(id);
    }

//    @GetMapping("/api/staff/edit/staffID={id}")
//    public Staff editStaff(@PathVariable("id") Long id) {
//        return staffRepository.findByID(id);
//    }


    //Update
    @PutMapping("/api/staff/saveUpdate")
    public ResponseEntity<Staff> saveUpdate(@RequestBody Staff staffEdit) {
        return staffService.editStaff(staffEdit);
    }


    @RequestMapping("/api/staff/search={search}")
    public List<Staff> searchAllStaff(@PathVariable("search") String search) {
        return staffService.searchAllStaff(search);
    }

    @RequestMapping("/api/staff/searchDate={searchDate}")
    public List<Staff> searchDateStaff(@PathVariable("searchDate") String searchDate) {
        return staffService.searchDateStaff(searchDate);
    }

    @RequestMapping("/api/staff/edit/staffID={id}")
    public Staff editStaff (@PathVariable("id") Long id){
        return staffRepository.findByID(id);
    }

}
