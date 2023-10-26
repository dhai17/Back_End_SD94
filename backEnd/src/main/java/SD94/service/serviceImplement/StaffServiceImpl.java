package SD94.service.serviceImplement;

import SD94.controller.Message.Message;
import SD94.entity.Staff;
import SD94.entity.security.UserRole;
import SD94.helper.UserFoundException;
import SD94.repository.RoleRepository;
import SD94.repository.StaffRepository;
import SD94.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffRepository staffRepository;

    @Override
    public List<Staff> findAllStaff() {
        List<Staff> staffs = staffRepository.findAllStaff();
        return staffs;
    }

    @Override
    public ResponseEntity<Staff> createStaff(Staff staffCreate) {

        Optional<Staff> optionalStaff = staffRepository.findByName(staffCreate.getName());
        String errorMessage;
        Message errorRespone;

        if (optionalStaff.isPresent()) {
            errorMessage = " Trùng mã nhân viên";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }
        if (staffCreate.getName() == null || staffCreate.getGender() == null ||
                staffCreate.getAddress() == null || staffCreate.getPhoneNumber() == null ||
                staffCreate.getDateOfBirth() == null || staffCreate.getEmail() == null ||
                staffCreate.getPassword() == null) {
            errorMessage = "Nhap day du thong tin";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }

        //SDT
        if (staffCreate.getPhoneNumber().length() != 10) {
            errorMessage = "SDT phải đủ 10 số";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }
        //email
        String email = staffCreate.getEmail();
        //dinh dang email
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        //partern de ktra email
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            errorMessage = "Mail khong dung dinh dang";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }

        //dateOfbirth
        Date dateC = new Date();
        Date dateOfBirth = staffCreate.getDateOfBirth();


        if (dateOfBirth.after(dateC)) {
            errorMessage = "Ngay sinh khong duoc vuot qua thoi gian hien tai";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }
        try {
            Staff staff = new Staff();
            staff.setName(staffCreate.getName());
            staff.setGender(staffCreate.getGender());
            staff.setEmail(staffCreate.getEmail());
            staff.setAddress(staffCreate.getAddress());
            staff.setDateOfBirth(staffCreate.getDateOfBirth());
            staff.setPassword(staffCreate.getPassword());
            staff.setPhoneNumber(staffCreate.getPhoneNumber());
            staffRepository.save(staff);
            return ResponseEntity.ok(staff);
        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }

    }


    // StaffUpdate
    @Override
    public ResponseEntity<Staff> editStaff(Staff staffEdit) {
        String errorMessage;
        Message errorRespone;

        if (staffEdit.getName() == null || staffEdit.getGender() == null ||
                staffEdit.getAddress() == null || staffEdit.getPhoneNumber() == null ||
                staffEdit.getDateOfBirth() == null || staffEdit.getEmail() == null ||
                staffEdit.getPassword() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }

        //SDT
//        if (staffEdit.getPhoneNumber().intValue()!=10) {
//            errorMessage = "Phone number must have 10 digits";
//            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
//            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
//        }
        //email
        String email = staffEdit.getEmail();
        //dinh dang email
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        //partern de ktra email
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            errorMessage = "Mail is not in the correct format";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }


        //dateOfbirth
        Date dateOfBirth = staffEdit.getDateOfBirth();
        Date dateC = new Date();
        if (dateOfBirth.after(dateC)) {
            errorMessage = "Ngày sinh không được vượt quá thời gian hiện tại";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Staff> optionalStaff = staffRepository.findById(staffEdit.getId());
            if (optionalStaff.isPresent()) {
                Staff staff = optionalStaff.get();
                staff.setName(staffEdit.getName());
                staff.setEmail(staffEdit.getEmail());
                staff.setAddress(staffEdit.getAddress());
                staff.setDateOfBirth(staffEdit.getDateOfBirth());
                staff.setPassword(staffEdit.getPassword());
                staff.setPhoneNumber(staffEdit.getPhoneNumber());
                staff.setGender(staffEdit.getGender());
                staffRepository.save(staff);
                return ResponseEntity.ok(staff);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<List<Staff>> deleteStaff(Long id) {
        try {
            Optional<Staff> optionalStaff = staffRepository.findById(id);
            if (optionalStaff.isPresent()) {
                Staff staff = optionalStaff.get();
                staff.setDeleted(true);
                staffRepository.save(staff);
                List<Staff> staffList = findAllStaff();
                return ResponseEntity.ok(staffList);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @Override
    public List<Staff> searchAllStaff(String search) {
        List<Staff> staffList = staffRepository.findStaffAll(search);
        return staffList;
    }

    @Override
    public List<Staff> searchDateStaff(String searchDate) {
        LocalDate searchdate = LocalDate.parse(searchDate);
        List<Staff> staffList = staffRepository.findStaffDate(searchdate);
        return staffList;

    }

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Staff createStaffV1(Staff user, Set<UserRole> userRoles) throws Exception {
        Staff local = staffRepository.findByEmail(user.getEmail());
        if (local != null) {
            System.out.println("User is already there");
            throw new UserFoundException();
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.staffRepository.save(user);
        }
        return local;
    }

    @Override
    public Staff getStaff(String email) {
        return staffRepository.findByEmail(email);

    }

    @Override
    public void deleteUser(Long userId) {
        staffRepository.deleteById(userId);
    }
}

