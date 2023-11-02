package SD94.controller.admin.hoaDon.datHang;

import SD94.entity.hoaDon.HoaDon;
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

    @GetMapping("/danhSach")
    public List<HoaDon> listBill1() {
        return hoaDonDatHangService.findHoaDonByTrangThai(1);
    }

    @PostMapping("/capNhatTrangThai/daXacNhan")
    public List<HoaDon> updateStatus2(@RequestBody HoaDon hoaDon) {
        Long id = hoaDon.getId();
        hoaDonDatHangService.capNhatTrangThai(2, id);
        return hoaDonDatHangService.findHoaDonByTrangThai(1);
    }

    @PostMapping("/capNhatTrangThai/huyDon")
    public ResponseEntity<Map<String, Boolean>> updateStatus5(@RequestBody Map<String, String> body) {
        String id_bill = body.get("id_bill");
        Long id = Long.valueOf(id_bill);
        hoaDonDatHangService.capNhatTrangThai(5, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/xacNhanDon/tatCa")
    public ResponseEntity<Map<String, Boolean>> updateStatusAll2() {
        hoaDonDatHangService.capNhatTrangThai_TatCa(1,2);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/xacNhanDon/daChon")
    public ResponseEntity<Void> updateStatusSelect2(@RequestBody List<String> listID) {
        hoaDonDatHangService.capNhatTrangThai_DaChon(listID, 2);
        return ResponseEntity.ok().build();
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
