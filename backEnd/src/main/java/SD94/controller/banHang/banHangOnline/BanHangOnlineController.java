package SD94.controller.banHang.banHangOnline;

import SD94.dto.GioHangDTO;
import SD94.dto.HoaDonDTO;
import SD94.dto.KhachHangDTO;
import SD94.entity.gioHang.GioHang;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.entity.hoaDon.TrangThai;
import SD94.entity.gioHang.GioHangChiTiet;
import SD94.entity.khachHang.KhachHang;
import SD94.entity.khuyenMai.KhuyenMai;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.gioHang.GioHangChiTietRepository;
import SD94.repository.gioHang.GioHangRepository;
import SD94.repository.hoaDon.HoaDonChiTietRepository;
import SD94.repository.hoaDon.HoaDonRepository;
import SD94.repository.hoaDon.TrangThaiRepository;
import SD94.repository.khachHang.KhachHangRepository;
import SD94.repository.khuyenMai.KhuyenMaiRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import SD94.service.service.BanHangOnlineService;
import SD94.service.service.HoaDonDatHangService;
import SD94.service.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banHang/online")
public class BanHangOnlineController {

    @Autowired
    BanHangOnlineService banHangOnlineService;

    @PostMapping("/checkOut")
    public ResponseEntity<Long> checkout(@RequestBody GioHangDTO dto) {
        return banHangOnlineService.checkout(dto);
    }

    @GetMapping("/getHoaDon/{id}")
    public ResponseEntity<HoaDon> getHoaDon(@PathVariable("id") long id_HoaDon) {
        return banHangOnlineService.getHoaDon(id_HoaDon);
    }

    @GetMapping("/getHoaDonChiTiet/{id}")
    public List<HoaDonChiTiet> getHoaDonChiTiet(@PathVariable("id") long id_HoaDon) {
        return banHangOnlineService.getHoaDonChiTiet(id_HoaDon);
    }

    @GetMapping("/check-out")
    public ResponseEntity<HoaDon> getBill() {
        return banHangOnlineService.getBill();
    }

    @PostMapping("/add/khuyenMai")
    public HoaDon addDiscount(@RequestBody HoaDonDTO hoaDonDTO) {
        return banHangOnlineService.addDiscount(hoaDonDTO);
    }

    @PostMapping("/datHang")
    public ResponseEntity datHang(@RequestBody HoaDonDTO dto) {
        return banHangOnlineService.datHang(dto);
    }

}
