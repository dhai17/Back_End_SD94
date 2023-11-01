package SD94.controller.admin.hoaDon.datHang;

import SD94.entity.hoaDon.HoaDon;
import SD94.service.service.HoaDonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/hoaDon/datHang/daGiaoHang")
public class DaGiaoHangController {
    @Autowired
    HoaDonDatHangService hoaDonDatHangService;

    @GetMapping("/danhSach")
    public List<HoaDon> listBill4() {
        return hoaDonDatHangService.findHoaDonByTrangThai(4);
    }

    @RequestMapping("/timKiem={search}")
    public List<HoaDon> searchAllBill4(@PathVariable("search") String search) {
        return hoaDonDatHangService.searchAllBill(4, search);
    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<HoaDon> searchDateBill4(@PathVariable("searchDate") String searchDate) {
        return hoaDonDatHangService.searchDateBill(4, searchDate);
    }

}
