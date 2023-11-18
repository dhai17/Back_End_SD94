package SD94.controller.admin.hoaDon.taiQuay;


import SD94.dto.HoaDonDTO;
import SD94.entity.hoaDon.HoaDon;
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
@RequestMapping("/hoaDon/taiQuay/dangBan")
public class DangBanController {
    @Autowired
    HoaDonDatHangService hoaDonDatHangService;

    @Autowired
    NhanVienRepository nhanVienRepository;
    @GetMapping("/danhSach")
    public List<HoaDon> listBill2() {
        return hoaDonDatHangService.findHoaDonByTrangThai(6);
    }

    @PostMapping("/capNhatTrangThai/daHuyDon")
    public ResponseEntity<Map<String, Boolean>> updateStatus5(@RequestBody HoaDonDTO hoaDonDTO) {
        Long id = hoaDonDTO.getId();
        String email = hoaDonDTO.getEmail_user();
        NhanVien nhanVien = nhanVienRepository.findByEmail(email);
        hoaDonDatHangService.capNhatTrangThai(8, id);
        hoaDonDatHangService.createTimeLine("Huỷ đơn đang bán", 8, id, nhanVien.getHoTen());
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/timKiem={search}")
    public List<HoaDon> searchAllBill2(@PathVariable("search") String search) {
        return hoaDonDatHangService.searchAllBill(6, search);

    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<HoaDon> searchDateBill2(@PathVariable("searchDate") String searchDate) {
        return hoaDonDatHangService.searchDateBill(6, searchDate);
    }
}
