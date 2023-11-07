package SD94.controller.banHang.banHangOnline;

import SD94.dto.GioHangDTO;
import SD94.dto.HoaDonDTO;
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
import SD94.repository.khachHang.KhachHangRepository;
import SD94.repository.khuyenMai.KhuyenMaiRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.RoundingMode;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    private Long idBill;

    @PostMapping("/checkOut")
    public ResponseEntity<Long> checkout(@RequestBody GioHangDTO dto) {
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

    @PostMapping("/add/discount")
    public ResponseEntity<String> addDiscount(@RequestParam("id_bill") long id_bill,
                                              @RequestParam("id_discount") long id_discount,
                                              @RequestParam("total") int total) {
        KhuyenMai khuyenMai = discountRepository.findByID(id_discount);
        Optional<HoaDon> optionalBill = billRepository.findById(id_bill);
        if (optionalBill.isPresent()) {
            HoaDon hoaDon = optionalBill.get();
            int phanTramGiam = khuyenMai.getPhanTramGiam();
            int tienGiamToiDa = khuyenMai.getTienGiamToiDa();
            int tongTienBill = total;

            int tongTienSauGiam = (tongTienBill * phanTramGiam) / 100;
            if (tongTienSauGiam > tienGiamToiDa) {
                tongTienSauGiam = tongTienBill - tienGiamToiDa;
                hoaDon.setTongTienHoaDon(tongTienSauGiam);
                hoaDon.setKhuyenMai(khuyenMai);
                billRepository.save(hoaDon);
                return ResponseEntity.badRequest().body(Collections.singletonList("Tien giam lon hon tien giam toi da").toString());
            } else {
                hoaDon.setTongTienHoaDon(tongTienSauGiam);
                hoaDon.setKhuyenMai(khuyenMai);
                billRepository.save(hoaDon);
            }
        }

        return ResponseEntity.ok().body("done");
    }

    //nút đặt hàng ship cod
    @PostMapping("/shipCode")
    public String shipCode(@RequestParam("id_bill") long id_bill,
                           @RequestParam("total") int total,
                           @RequestParam("money_ship") int money_ship,
                           @RequestParam("diaChiGiaohang") String diaChiGiaohang,
                           @RequestParam("SoDienThoaiNguoiNhan") String SoDienThoaiNguoiNhan,
                           @RequestParam("emailNguoiNhan") String emailNguoiNhan,
                           @RequestParam("ghiChu") String ghiChu) {
        Optional<HoaDon> optionalBill = billRepository.findById(id_bill);
        if (optionalBill.isPresent()) {
            TrangThai status = new TrangThai();
            status.setId(1L);
            HoaDon hoaDon = optionalBill.get();
            hoaDon.setDiaChiGiaoHang(diaChiGiaohang);
            hoaDon.setEmailNguoiNhan(emailNguoiNhan);
            hoaDon.setGhiChu(ghiChu);
            hoaDon.setSDTNguoiNhan(SoDienThoaiNguoiNhan);
            hoaDon.setTienShip(money_ship);
            hoaDon.setTongTienHoaDon(total);
            hoaDon.setTrangThai(status);
            billRepository.save(hoaDon);

            List<HoaDonChiTiet> listBillDetails = billDetailsRepository.findByIDBill(id_bill);
            for (HoaDonChiTiet billDtails : listBillDetails) {
                int soLuongDangCo = billDtails.getSoLuong();
                int soLuongDangCoSanPham = billDtails.getSanPhamChiTiet().getSoLuong();
                int soLuongCapNhat = soLuongDangCoSanPham - soLuongDangCo;
                long idProductDetails = billDtails.getSanPhamChiTiet().getId();
                SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findByID(idProductDetails);
                sanPhamChiTiet.setSoLuong(soLuongCapNhat);
                sanPhamChiTietRepository.save(sanPhamChiTiet);

                GioHangChiTiet cart = cartDetailsRepository.findByProductDetailsID(idProductDetails);
                cart.setDeleted(true);
                cartDetailsRepository.save(cart);
            }
        }
        return "done";
    }

    @Transactional
    @PostMapping("/datHang")
    public ResponseEntity datHang(@RequestBody HoaDonDTO dto) {
        HoaDon hoaDon = billRepository.findByID(dto.getId());
        KhachHang khachHang = khachHangRepository.findByEmail(dto.getEmail_user());
        GioHang gioHang = gioHangRepository.findbyCustomerID(khachHang.getId());
        List<HoaDonChiTiet> hoaDonChiTiets = billDetailsRepository.findByIDBill(hoaDon.getId());
        for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
            cartDetailsRepository.deleteGioHangChiTiet(hoaDonChiTiet.getSanPhamChiTiet().getId());
        }
        hoaDon.setGhiChu(dto.getGhiChu());
        hoaDon.setTongTienHoaDon(dto.getTongTienHoaDon());
        hoaDon.setTongTienDonHang(dto.getTongTienDonHang());
        hoaDon.setEmailNguoiNhan(dto.getEmail());
        hoaDon.setSDTNguoiNhan(dto.getSoDienThoai());
        hoaDon.setTienShip(dto.getTienShip());
        billRepository.save(hoaDon);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
