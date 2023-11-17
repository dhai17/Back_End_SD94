package SD94.controller.banHang.taiQuay;

import SD94.dto.*;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.entity.khachHang.KhachHang;
import SD94.entity.khuyenMai.KhuyenMai;
import SD94.repository.hoaDon.HoaDonChiTietRepository;
import SD94.repository.hoaDon.HoaDonRepository;
import SD94.repository.khachHang.KhachHangRepository;
import SD94.repository.khuyenMai.KhuyenMaiRepository;
import SD94.service.service.BanHangTaiQuayService;
import SD94.service.service.InHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/banHang/taiQuay")
public class BanHangTaiQuayController {
    @Autowired
    BanHangTaiQuayService banHangTaiQuayService;

    @Autowired
    InHoaDonService inHoaDonService;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;

    @GetMapping("/danhSachHoaDon")
    public List<HoaDon> danhSachHoaDonCho() {
        List<HoaDon> hoaDonList = hoaDonRepository.getDanhSachHoaDonCho();
        return hoaDonList;
    }

    @GetMapping("/getHoaDonChitiet/{id}")
    public List<HoaDonChiTiet> getHoaDonChiTiet(@PathVariable("id") long id) {
        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findByIDBill(id);
        return hoaDonChiTiets;
    }

    @PostMapping("/taoHoaDon")
    public ResponseEntity<?> taoHoaDon(@RequestBody HoaDonDTO hoaDonDTO) {
        return banHangTaiQuayService.taoHoaDon(hoaDonDTO);
    }

    @PostMapping("/themSanPham")
    public ResponseEntity themSanPham(@RequestBody SanPhamDTO dto) {
        return banHangTaiQuayService.themSanPham(dto);
    }

    @GetMapping("/getHoaDon/{id}")
    public HoaDon getHoaDon(@PathVariable("id") long id) {
        HoaDon hoaDon = hoaDonRepository.findByID(id);
        return hoaDon;
    }

    @PostMapping("/xoaHoaDon")
    public List<HoaDon> xoaHoaDon(@RequestBody HoaDonDTO hoaDonDTO) {
        return banHangTaiQuayService.xoaHoaDon(hoaDonDTO);
    }

    @GetMapping("/khachHang/list")
    public ResponseEntity<List<KhachHang>> listKhachHang() {
        List<KhachHang> khachHangs = khachHangRepository.findAll();
        return ResponseEntity.ok(khachHangs);
    }

    @GetMapping("/khuyenMai/list")
    public ResponseEntity<List<KhuyenMai>> listKhuyenMai() {
        List<KhuyenMai> khuyenMais = khuyenMaiRepository.findAll();
        return ResponseEntity.ok(khuyenMais);
    }

    @PostMapping("/add/khuyenMai")
    public HoaDon themMaGiamGiaTaiQuay(@RequestBody HoaDonDTO hoaDonDTO) {
        return banHangTaiQuayService.themMaGiamGiaTaiQuay(hoaDonDTO);
    }

    @PostMapping("/add/KhachHang")
    public ResponseEntity<?> addKhachHang(@RequestBody KhachHangDTO dto) {
        return banHangTaiQuayService.addKhachHang(dto);
    }

    @PostMapping("/huyDon")
    public ResponseEntity huyDon(@RequestBody HoaDonDTO hoaDonDTO) {
        return banHangTaiQuayService.huyDon(hoaDonDTO);
    }

    @PostMapping("/thanhToan")
    public ResponseEntity thanhToan(@RequestBody HoaDonDTO hoaDonDTO) {
        return banHangTaiQuayService.thanhToan(hoaDonDTO);
    }

    @GetMapping("/inHoaDon/{id}")
    public ResponseEntity<byte[]> inHoaDon(@PathVariable("id") long id) {
        return inHoaDonService.generatePdf(id);
    }

    @PostMapping("/xoaHDCT")
    public ResponseEntity<?> xoaHDCT(@RequestBody HoaDonChiTietDTO dto) {
        return banHangTaiQuayService.xoaHDCT(dto);
    }
}
