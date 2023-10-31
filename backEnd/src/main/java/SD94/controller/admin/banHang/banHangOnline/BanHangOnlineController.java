package SD94.controller.admin.banHang.banHangOnline;

import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.entity.hoaDon.TrangThai;
import SD94.entity.gioHang.GioHangChiTiet;
import SD94.entity.khuyenMai.KhuyenMai;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banHang/online")
public class BanHangOnlineController {
    @Autowired
    CartDetailsRepository cartDetailsRepository;

    @Autowired
    BillDetailsRepository billDetailsRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    @Autowired
    DiscountRepository discountRepository;

    private Long idBill;

    @PostMapping("/datHang")
    public ResponseEntity<Long> checkout(@RequestParam("id_cart_details") long[] id_cart_details,
                                         @RequestParam("total") int total) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setCreatedDate(new Date());
        hoaDon.setCreatedby("abc");
        billRepository.save(hoaDon);

        for (long id : id_cart_details) {
            Optional<GioHangChiTiet> optionalcart = cartDetailsRepository.findById(id);
            if (optionalcart.isPresent()) {
                GioHangChiTiet gioHangChiTiet = optionalcart.get();
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setSanPhamChiTiet(gioHangChiTiet.getSanPhamChiTiet());
                hoaDonChiTiet.setSoLuong(gioHangChiTiet.getSoLuong());
                hoaDonChiTiet.setDonGia(gioHangChiTiet.getDonGia());
                hoaDonChiTiet.setThanhTien(total);
                hoaDonChiTiet.setHoaDon(hoaDon);
                billDetailsRepository.save(hoaDonChiTiet);
            }
        }

        idBill = hoaDon.getId();
        return ResponseEntity.ok(idBill);
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
        if (optionalBill.isPresent()){
            HoaDon hoaDon = optionalBill.get();
            int phanTramGiam = khuyenMai.getPhanTramGiam();
            int tienGiamToiDa = khuyenMai.getTienGiamToiDa();
            int tongTienBill = total;

            int tongTienSauGiam = (tongTienBill * phanTramGiam)/100;
            if(tongTienSauGiam > tienGiamToiDa){
                tongTienSauGiam = tongTienBill - tienGiamToiDa;
                hoaDon.setTongTienHoaDon(tongTienSauGiam);
                hoaDon.setKhuyenMai(khuyenMai);
                billRepository.save(hoaDon);
                return ResponseEntity.badRequest().body(Collections.singletonList("Tien giam lon hon tien giam toi da").toString());
            }else {
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
                SanPhamChiTiet sanPhamChiTiet = productDetailsRepository.findByID(idProductDetails);
                sanPhamChiTiet.setSoLuong(soLuongCapNhat);
                productDetailsRepository.save(sanPhamChiTiet);

                GioHangChiTiet cart = cartDetailsRepository.findByProductDetailsID(idProductDetails);
                cart.setDeleted(true);
                cartDetailsRepository.save(cart);
            }
        }
        return "done";
    }

}
