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
@RequestMapping("/hoaDon/datHang/choGiaoHang")
public class ChoGiaoHangController {
    @Autowired
    HoaDonDatHangService hoaDonDatHangService;

    @GetMapping("/danhSach")
    public List<HoaDon> listBill2() {
        return hoaDonDatHangService.findHoaDonByTrangThai(2);
    }

    @PostMapping("/capNhatTrangThai/dangGiaoHang")
    public ResponseEntity<Map<String, Boolean>> updateStatus3(@RequestBody Map<String, String> body) {
        String id_bill = body.get("id_bill");
        Long id = Long.valueOf(id_bill);
        hoaDonDatHangService.capNhatTrangThai(3, id);
        hoaDonDatHangService.createTimeLine("Xác nhận giao đơn hàng", 3, id, 1);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/capNhatTrangThai/dangGiaoHang-tatCa")
    public ResponseEntity<Map<String, Boolean>> updateStatusAll3() {
        hoaDonDatHangService.capNhatTrangThai_TatCa(2,3);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/timKiem={search}")
    public List<HoaDon> searchAllBill2(@PathVariable("search") String search) {
        return hoaDonDatHangService.searchAllBill(2, search);

    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<HoaDon> searchDateBill2(@PathVariable("searchDate") String searchDate) {
        return hoaDonDatHangService.searchDateBill(2, searchDate);
    }
}
