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
@RequestMapping("/hoaDon/datHang/dangGiaoHang")
public class DangGiaoHangController {
    @Autowired
    HoaDonDatHangService hoaDonDatHangService;

    @GetMapping("/danhSach")
    public List<HoaDon> listBill3() {
        return hoaDonDatHangService.findHoaDonByTrangThai(3);
    }

    @PostMapping("/capNhatTrangThai/daGiaoHang")
    public ResponseEntity<Map<String, Boolean>> updateStatus4(@RequestBody Map<String, String> body) {
        String id_bill = body.get("id_bill");
        Long id = Long.valueOf(id_bill);
        hoaDonDatHangService.capNhatTrangThai(4, id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/timKiem={search}")
    public List<HoaDon> searchAllBill3(@PathVariable("search") String search) {
        return hoaDonDatHangService.searchAllBill(4, search);
    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<HoaDon> searchDateBill3(@PathVariable("searchDate") String searchDate) {
        return hoaDonDatHangService.searchDateBill(4, searchDate);
    }
}
