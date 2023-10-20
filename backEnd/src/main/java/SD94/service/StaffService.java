package SD94.service;

import SD94.entity.Staff;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {
    List<Staff>findAllStaff();

    ResponseEntity<Staff> createStaff(Staff staffCreate);

    ResponseEntity<Staff> editStaff(Staff staffEdit);

    ResponseEntity<List<Staff>> deleteStaff(Long id);

    List<Staff> searchAllStaff(String search);

    List<Staff> searchDateStaff(String searchDate);
}
