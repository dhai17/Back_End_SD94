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
        hoaDonDatHangService.createTimeLine("Xác nhận đã giao hàng", 4, id, "a");
        return ResponseEntity.ok().build();
    }
    @PostMapping("/capNhatTrangThai/huyDon5")
    public ResponseEntity<Map<String, Boolean>> updateStatus5(@RequestBody Map<String, String> body) {
        String id_bill = body.get("id_bill");
        Long id = Long.valueOf(id_bill);
        hoaDonDatHangService.capNhatTrangThai(5, id);
        hoaDonDatHangService.createTimeLine("Huỷ đơn", 5, id, "a");
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/timKiem={search}")
    public List<HoaDon> searchAllBill3(@PathVariable("search") String search) {
        return hoaDonDatHangService.searchAllBill(3, search);
    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<HoaDon> searchDateBill3(@PathVariable("searchDate") String searchDate) {
        return hoaDonDatHangService.searchDateBill(3, searchDate);
    }
}
