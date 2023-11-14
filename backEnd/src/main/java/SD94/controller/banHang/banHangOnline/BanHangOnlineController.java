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
import SD94.service.service.HoaDonDatHangService;
import SD94.service.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.math.RoundingMode;
import java.util.*;

@RestController
@RequestMapping("/api/banHang/online")
public class BanHangOnlineController {
    @Autowired
    GioHangChiTietRepository cartDetailsRepository;

    @Autowired
    HoaDonChiTietRepository billDetailsRepository;

    @Autowired
    HoaDonRepository billRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    KhuyenMaiRepository discountRepository;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    GioHangRepository gioHangRepository;

    @Autowired
    TrangThaiRepository trangThaiRepository;

    @Autowired
    HoaDonDatHangService hoaDonDatHangService;

    @Autowired
    MailService mailService;

    private Long idBill;

    @PostMapping("/checkOut")
    public ResponseEntity<?> checkout(@RequestBody GioHangDTO dto) {
        if (dto.getId_gioHangChiTiet() == null  || dto.getTongTien() == 0) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Bạn chưa chọn sản phẩm muốn đặt");
            return ResponseEntity.badRequest().body(response);
        } else {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setCreatedDate(new Date());
            hoaDon.setCreatedby("hduong");
            billRepository.save(hoaDon);

            hoaDon.setMaHoaDon("HD" + hoaDon.getId());
            hoaDon.setTongTienHoaDon(dto.getTongTien());
            hoaDon.setTongTienDonHang(dto.getTongTien());
            billRepository.save(hoaDon);

            for (long id : dto.getId_gioHangChiTiet()) {
                Optional<GioHangChiTiet> optionalcart = cartDetailsRepository.findById(id);
                if (optionalcart.isPresent()) {
                    GioHangChiTiet gioHangChiTiet = optionalcart.get();
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                    hoaDonChiTiet.setSanPhamChiTiet(gioHangChiTiet.getSanPhamChiTiet());
                    hoaDonChiTiet.setSoLuong(gioHangChiTiet.getSoLuong());
                    hoaDonChiTiet.setDonGia(gioHangChiTiet.getDonGia());
                    hoaDonChiTiet.setThanhTien(gioHangChiTiet.getThanhTien().setScale(0, RoundingMode.HALF_UP).intValue());
                    hoaDonChiTiet.setHoaDon(hoaDon);
                    billDetailsRepository.save(hoaDonChiTiet);
                }
            }
            idBill = hoaDon.getId();
            return ResponseEntity.ok(idBill);
        }
    }

    @GetMapping("/getHoaDon/{id}")
    public ResponseEntity<HoaDon> getHoaDon(@PathVariable("id") long id_HoaDon) {
        HoaDon hoaDon = billRepository.findByID(id_HoaDon);
        return ResponseEntity.ok(hoaDon);
    }

    @GetMapping("/getHoaDonChiTiet/{id}")
    public List<HoaDonChiTiet> getHoaDonChiTiet(@PathVariable("id") long id_HoaDon) {
        List<HoaDonChiTiet> hoaDonChiTiets = billDetailsRepository.findByIDBill(id_HoaDon);
        return hoaDonChiTiets;
    }

    @GetMapping("/check-out")
    public ResponseEntity<HoaDon> getBill() {
        if (idBill != null) {
            Optional<HoaDon> optionalBill = billRepository.findById(idBill);
            if (optionalBill.isPresent()) {
                HoaDon hoaDon = optionalBill.get();
                return ResponseEntity.ok(hoaDon);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add/khuyenMai")
    public HoaDon addDiscount(@RequestBody HoaDonDTO hoaDonDTO) {
        KhuyenMai khuyenMai = discountRepository.findByNameKM(hoaDonDTO.getTenMaGiamGia());
        HoaDon hoaDon = billRepository.findByID(hoaDonDTO.getId());
        int phanTramGiam = khuyenMai.getPhanTramGiam();
        int tienGiamToiDa = khuyenMai.getTienGiamToiDa();
        int tongTienBill = hoaDon.getTongTienHoaDon();

        int tongTienSauGiamCheck = (tongTienBill * phanTramGiam) / 100;
        if (tongTienSauGiamCheck > tienGiamToiDa) {
            int tongTienSauGiam = hoaDon.getTongTienHoaDon() - khuyenMai.getTienGiamToiDa();
            hoaDon.setTienGiam(hoaDon.getTongTienHoaDon() - tongTienSauGiam);
            hoaDon.setTongTienDonHang(tongTienSauGiam);
            hoaDon.setKhuyenMai(khuyenMai);
            billRepository.save(hoaDon);
        } else {
            int tongTien = hoaDon.getTongTienHoaDon() - tongTienSauGiamCheck;
            System.out.println(tongTien);
            hoaDon.setTongTienDonHang(tongTien);
            hoaDon.setTienGiam(tongTienSauGiamCheck);
            hoaDon.setKhuyenMai(khuyenMai);
            billRepository.save(hoaDon);
        }

        HoaDon hoaDon2 = billRepository.findByID(hoaDonDTO.getId());
        return hoaDon2;
    }

    @Transactional
    @PostMapping("/datHang")
    public ResponseEntity datHang(@RequestBody HoaDonDTO dto) {
        HoaDon hoaDon = billRepository.findByID(dto.getId());
        KhachHang khachHang = khachHangRepository.findByEmail(dto.getEmail_user());
        GioHang gioHang = gioHangRepository.findbyCustomerID(khachHang.getId());
        List<GioHangChiTiet> gioHangChiTiets = cartDetailsRepository.findByCartID(gioHang.getId());
        List<HoaDonChiTiet> hoaDonChiTiets = billDetailsRepository.findByIDBill(hoaDon.getId());
        for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
            cartDetailsRepository.deleteGioHangChiTiet(hoaDonChiTiet.getSanPhamChiTiet().getId());
        }

        for (GioHangChiTiet gioHangChiTiet : gioHangChiTiets) {
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findByID(gioHangChiTiet.getSanPhamChiTiet().getId());
            sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - gioHangChiTiet.getSoLuong());
            sanPhamChiTietRepository.save(sanPhamChiTiet);
        }

        TrangThai trangThai = trangThaiRepository.findByID(1L);

        hoaDon.setGhiChu(dto.getGhiChu());
        hoaDon.setTongTienHoaDon(dto.getTongTienHoaDon());
        hoaDon.setTongTienDonHang(dto.getTongTienDonHang());
        hoaDon.setEmailNguoiNhan(dto.getEmail());
        hoaDon.setSDTNguoiNhan(dto.getSoDienThoai());
        hoaDon.setTienShip(dto.getTienShip());
        hoaDon.setDiaChiGiaoHang(dto.getDiaChi());
        hoaDon.setTrangThai(trangThai);
        hoaDon.setKhachHang(khachHang);
        hoaDon.setNguoiNhan(khachHang.getHoTen());
        billRepository.save(hoaDon);

        try {
            mailService.sendOrderConfirmationEmail(hoaDon.getEmailNguoiNhan(), hoaDon);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        hoaDonDatHangService.createTimeLine("Tạo đơn hàng", 1L, hoaDon.getId(), khachHang.getHoTen());
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
