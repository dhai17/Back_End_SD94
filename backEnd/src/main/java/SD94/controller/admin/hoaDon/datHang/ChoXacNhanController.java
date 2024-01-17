package SD94.controller.admin.hoaDon.datHang;

import SD94.dto.HoaDonDTO;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.khachHang.KhachHang;
import SD94.entity.nhanVien.NhanVien;
import SD94.repository.hoaDon.HoaDonRepository;
import SD94.repository.nhanVien.NhanVienRepository;
import SD94.service.service.HoaDonDatHangService;
import SD94.service.service.InHoaDonService;
import SD94.service.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/hoaDon/datHang/choXacNhan")
public class ChoXacNhanController {
    @Autowired
    HoaDonDatHangService hoaDonDatHangService;

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    InHoaDonService inHoaDonService;

    @Autowired
    MailService mailService;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @GetMapping("/danhSach")
    public List<HoaDon> listBill1() {
//        List<HoaDon> result = hoaDonDatHangService.findHoaDonByTrangThai(1L);
//        LocalDateTime currentDateTime = LocalDateTime.now();
//
//        for (HoaDon hoaDon : result) {
//            Date createdDate = hoaDon.getCreatedDate();
//
//            // Chuyển đổi Date sang LocalDateTime
//            Instant instant = createdDate.toInstant();
//            ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
//            LocalDateTime localCreatedDateTime = zonedDateTime.toLocalDateTime();
//
//            // Kiểm tra điều kiện hủy đơn nếu tạo ra trước 1 phút và có trạng thái là 1
//            if (ChronoUnit.MINUTES.between(localCreatedDateTime, currentDateTime) > 15&& hoaDon.getTrangThai().getId() == 1) {
//                hoaDonDatHangService.capNhatTrangThaiHuyDon(5, hoaDon.getId(), "Đơn hàng đã quá 1 phút chưa xác nhận");
//                hoaDonDatHangService.createTimeLine("Huỷ đơn", 5, hoaDon.getId(), "Hệ thống");
//            }
//        }

        // Trả về danh sách đơn hàng sau khi kiểm tra và có thể đã hủy một số đơn
        return hoaDonDatHangService.findHoaDonByTrangThai(1L);
    }



    @PostMapping("/capNhatTrangThai/daXacNhan")
    public ResponseEntity<?> updateStatus2(@RequestBody HoaDonDTO hoaDonDTO)  {
        Long id = hoaDonDTO.getId();
        String email = hoaDonDTO.getEmail_user();
        NhanVien nhanVien = nhanVienRepository.findByEmail(email);
        hoaDonDatHangService.createTimeLine("Xác nhận đơn", 2, id, nhanVien.getHoTen());
        return ResponseEntity.ok().body(hoaDonDatHangService.capNhatTrangThai(2, id));
    }

    @PostMapping("/capNhatTrangThai/huyDon")
    public ResponseEntity<Map<String, Boolean>> updateStatus5(@RequestBody HoaDonDTO hoaDonDTO) {
        Long id = hoaDonDTO.getId();
        String ghiChu = hoaDonDTO.getGhiChu();
        String email = hoaDonDTO.getEmail_user();
        NhanVien nhanVien = nhanVienRepository.findByEmail(email);
        hoaDonDatHangService.capNhatTrangThaiHuyDon(5, id, ghiChu);
        hoaDonDatHangService.createTimeLine("Huỷ đơn", 5, id, nhanVien.getHoTen());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/xacNhanDon/tatCa")
    public ResponseEntity<Map<String, Boolean>> updateStatusAll2(@RequestBody HoaDonDTO hoaDonDTO) {
        String email = hoaDonDTO.getEmail_user();
        NhanVien nhanVien = nhanVienRepository.findByEmail(email);
        hoaDonDatHangService.capNhatTrangThai_TatCa(1,2,"Xác nhận đơn",nhanVien.getHoTen());
        return ResponseEntity.ok().build();
    }
    @PutMapping("/xacNhanDon/daChon")
    public List<HoaDon> updateStatusSelect2(@RequestBody HoaDonDTO hoaDonDTO) {
        String email = hoaDonDTO.getEmail_user();
        NhanVien nhanVien = nhanVienRepository.findByEmail(email);
        return hoaDonDatHangService.capNhatTrangThai_DaChon(hoaDonDTO, 2,"Xác nhận đơn",nhanVien.getHoTen());
    }

    @PutMapping("/huyDon/daChon")
    public List<HoaDon> updateStatusSelect5(@RequestBody HoaDonDTO hoaDonDTO) {
            String email = hoaDonDTO.getEmail_user();
            String ghiChu= hoaDonDTO.getGhiChu();
            NhanVien nhanVien = nhanVienRepository.findByEmail(email);
        return hoaDonDatHangService.capNhatTrangThaiHuy_DaChon(hoaDonDTO, nhanVien.getHoTen(),ghiChu);
    }

    @RequestMapping("/timKiem={search}")
    public List<HoaDon> searchAllBill1(@PathVariable("search") String search) {
        return hoaDonDatHangService.searchAllBill(1, search);
    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<HoaDon> searchDateBill1(@PathVariable("searchDate") String searchDate) {
        return hoaDonDatHangService.searchDateBill(1, searchDate);
    }
    @GetMapping("/inHoaDon/{id}")
    public ResponseEntity<byte[]> inHoaDon(@PathVariable("id") long id) {
        return inHoaDonService.hoaDonDatHangPdf(id);
    }
    @GetMapping("/guiMail/{id}")
    public void guiMail(@PathVariable("id") long id) {
        HoaDon hoaDon = hoaDonRepository.findByID(id);
        if(hoaDon.getEmailNguoiNhan() != null || hoaDon.getEmailNguoiNhan().isEmpty()){
            try {
                mailService.guiMailKhiThaoTac(hoaDon.getEmailNguoiNhan(), hoaDon);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
