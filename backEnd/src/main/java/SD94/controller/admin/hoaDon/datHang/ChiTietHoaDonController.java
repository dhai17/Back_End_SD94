package SD94.controller.admin.hoaDon.datHang;

import SD94.dto.ChiTietHoaDonDTO;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.entity.hoaDon.LichSuHoaDon;
import SD94.entity.hoaDon.TrangThai;
import SD94.repository.hoaDon.HoaDonChiTietRepository;
import SD94.repository.hoaDon.HoaDonRepository;
import SD94.repository.hoaDon.LichSuHoaDonRepository;
import SD94.repository.hoaDon.TrangThaiRepository;
import SD94.service.service.HoaDonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/hoaDon/chiTietHoaDon")
public class ChiTietHoaDonController {
    @Autowired
    HoaDonDatHangService hoaDonDatHangService;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    TrangThaiRepository trangThaiRepository;

    //Trả ra hoá đơn theo ID, danh sách hoá đn, lịch s của hoá ơn đó
    @GetMapping("/choXacNhan/id={id}")
    public ResponseEntity<?> CTChoXacNhan(@PathVariable("id") long hoa_don_id) {
        return hoaDonDatHangService.CTChoXacNhan(hoa_don_id);
    }

    @GetMapping("/choGiaoHang/id={id}")
    public ResponseEntity<?> CTChoGiaoHang(@PathVariable("id") long hoa_don_id) {
        return hoaDonDatHangService.CTChoGiaoHang(hoa_don_id);
    }

    @GetMapping("/dangGiaoHang/id={id}")
    public ResponseEntity<?> CTDangGiaoHang(@PathVariable("id") long hoa_don_id) {
        return hoaDonDatHangService.CTDangGiaoHang(hoa_don_id);
    }

    @GetMapping("/daGiaoHang/id={id}")
    public ResponseEntity<?> CTDaGiaoHang(@PathVariable("id") long hoa_don_id) {
        return hoaDonDatHangService.CTDaGiaoHang(hoa_don_id);
    }

    @GetMapping("/daHuy/id={id}")
    public ResponseEntity<?> CTDaHuy(@PathVariable("id") long hoa_don_id) {
        return hoaDonDatHangService.CTDaHuy(hoa_don_id);
    }

}
