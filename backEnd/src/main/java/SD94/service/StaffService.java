package SD94.service;

import SD94.entity.Staff;
import SD94.entity.security.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface StaffService {
    List<Staff>findAllStaff();

    ResponseEntity<Staff> createStaff(Staff staffCreate);

    ResponseEntity<Staff> editStaff(Staff staffEdit);

    ResponseEntity<List<Staff>> deleteStaff(Long id);

    List<Staff> searchAllStaff(String search);

    List<Staff> searchDateStaff(String searchDate);

    public Staff createStaffV1(Staff user, Set<UserRole> userRoles) throws Exception;
    Staff getStaff(String email);


    void deleteUser(Long userId);
}
