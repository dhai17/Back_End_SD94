package SD94.controller.admin.hoaDon.datHang;

import SD94.dto.HoaDonDTO;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.khachHang.KhachHang;
import SD94.entity.nhanVien.NhanVien;
import SD94.repository.nhanVien.NhanVienRepository;
import SD94.service.service.HoaDonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/danhSach")
    public List<HoaDon> listBill1() {
        return hoaDonDatHangService.findHoaDonByTrangThai(1);
    }

    @PostMapping("/capNhatTrangThai/daXacNhan")
    public List<HoaDon> updateStatus2(@RequestBody HoaDonDTO hoaDonDTO) {
        Long id = hoaDonDTO.getId();
        String email = hoaDonDTO.getEmail_user();
        NhanVien nhanVien = nhanVienRepository.findByEmail(email);
        hoaDonDatHangService.capNhatTrangThai(2, id);
        hoaDonDatHangService.createTimeLine("Xác nhận đơn", 2, id, nhanVien.getHoTen());

        return hoaDonDatHangService.findHoaDonByTrangThai(1);
    }

    @PostMapping("/capNhatTrangThai/huyDon")
    public ResponseEntity<Map<String, Boolean>> updateStatus5(@RequestBody HoaDonDTO hoaDonDTO) {
        Long id = hoaDonDTO.getId();
        String email = hoaDonDTO.getEmail_user();
        NhanVien nhanVien = nhanVienRepository.findByEmail(email);
        hoaDonDatHangService.capNhatTrangThai(5, id);
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
            NhanVien nhanVien = nhanVienRepository.findByEmail(email);
        return hoaDonDatHangService.capNhatTrangThaiHuy_DaChon(hoaDonDTO, nhanVien.getHoTen());
    }

    @RequestMapping("/timKiem={search}")
    public List<HoaDon> searchAllBill1(@PathVariable("search") String search) {
        return hoaDonDatHangService.searchAllBill(1, search);
    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<HoaDon> searchDateBill1(@PathVariable("searchDate") String searchDate) {
        return hoaDonDatHangService.searchDateBill(1, searchDate);
    }
}
