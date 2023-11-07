package SD94.controller.admin.banHang.taiQuay;

import SD94.dto.KichCoDTO;
import SD94.dto.MauSacDTO;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.entity.hoaDon.TrangThai;
import SD94.entity.sanPham.KichCo;
import SD94.entity.sanPham.MauSac;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.hoaDon.HoaDonChiTietRepository;
import SD94.repository.hoaDon.HoaDonRepository;
import SD94.repository.sanPham.KichCoRepository;
import SD94.repository.sanPham.MauSacRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class InStoreController {
    @Autowired
    MauSacRepository mauSacRepository;

    @Autowired
    KichCoRepository productSizeRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    HoaDonRepository billRepository;

    @Autowired
    HoaDonChiTietRepository billDetailsRepository;

    @RequestMapping("/api/getSize")
    public ResponseEntity<List<String>> getSize(@RequestParam("product_id") String id) {
        Long id_product = Long.valueOf(id);
        List<String> productSizes = productSizeRepository.getKichCo(id_product);
        return ResponseEntity.ok().body(productSizes);
    }

    @RequestMapping("/api/getColor")
    public ResponseEntity<List<String>> getColor(@RequestParam("product_id") String id) {
        Long id_product = Long.valueOf(id);
        List<String> productColor = mauSacRepository.getColor(id_product);
        return ResponseEntity.ok().body(productColor);
    }

    @RequestMapping("/api/getProduct")
    public ResponseEntity<List<String>> getProduct(@RequestParam("product_id") String id) {
        Long id_product = Long.valueOf(id);
        List<String> product = sanPhamChiTietRepository.getProduct(id_product);
        return ResponseEntity.ok().body(product);
    }

    @RequestMapping("/new-bill")
    public String newBill(){
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon("HD034");
        hoaDon.setCreatedby("hduong");
        hoaDon.setCreatedDate(new Date());
        billRepository.save(hoaDon);
        return "create bill done";
    }

    @RequestMapping("/cancel")
    public String huyDon(@RequestParam("id_bill") long id_bill) {
        Optional<HoaDon> optionalBill = billRepository.findById(1L);
        if (optionalBill.isPresent()) {
            HoaDon hoaDon = optionalBill.get();
            TrangThai trangThai = new TrangThai();
            trangThai.setId(8L);
            hoaDon.setTrangThai(trangThai);
            billRepository.save(hoaDon);
        }

        return "Payment bill done";
    }

    @RequestMapping("/select-product-details")
    public String productDetails(@RequestParam("id_product") long id_product,
                                 @RequestParam("id_color") long id_color,
                                 @RequestParam("id_size") long id_size) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findByColorAndSize(id_product, id_color, id_size);
        Optional<HoaDon> optionalBill = billRepository.findById(1L);
        if (optionalBill.isPresent()) {
            HoaDon hoaDon = optionalBill.get();
            HoaDonChiTiet bill_details = new HoaDonChiTiet();
            bill_details.setSoLuong(2);
            bill_details.setDonGia(100);
            bill_details.setThanhTien(200);
            bill_details.setHoaDon(hoaDon);
            bill_details.setSanPhamChiTiet(sanPhamChiTiet);
            billDetailsRepository.save(bill_details);
        }
        return "save product to bill done";
    }

    @RequestMapping("/payment")
    public String thanhtoanHoaDonTaiQuay(@RequestParam("id_bill") long id_bill) {
        Optional<HoaDon> optionalBill = billRepository.findById(id_bill);
        if (optionalBill.isPresent()) {
            HoaDon hoaDon = optionalBill.get();
            TrangThai trangThai = new TrangThai();
            trangThai.setId(7L);
            hoaDon.setTrangThai(trangThai);
            billRepository.save(hoaDon);
        }

        return "Payment bill done";
    }

    @PostMapping("/xoa-san-pham")
    public String xoaSanPham(@RequestParam("id_bill") long id_bill,
                             @RequestParam("id_bill_details") long id_bill_details){
        Optional<HoaDonChiTiet> detailedInvoice = billDetailsRepository.findById(id_bill_details);
        Optional<HoaDon> optionalBill = billRepository.findById(id_bill);
        if(detailedInvoice.isPresent() && optionalBill.isPresent()){
            HoaDonChiTiet details = detailedInvoice.get();
            HoaDon hoaDon = optionalBill.get();

            details.setDeleted(true);
            billDetailsRepository.save(details);

            int tongTien = details.getThanhTien();
            int tongTienBill = hoaDon.getTongTienHoaDon();
            int capNhatTongTien = tongTienBill - tongTien;
            hoaDon.setTongTienHoaDon(capNhatTongTien);

            long id_product_details = details.getSanPhamChiTiet().getId();
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findByID(id_product_details);
            int soLuong = details.getSoLuong();
            int soLuongBanDau = sanPhamChiTiet.getSoLuong();
            sanPhamChiTiet.setSoLuong(soLuongBanDau + soLuong);
            sanPhamChiTietRepository.save(sanPhamChiTiet);
        }
        return "deleted san pham thanh cong";
    }
}
