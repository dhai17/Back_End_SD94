package SD94.controller.customer.sanPham;

import SD94.dto.GioHangDTO;
import SD94.entity.gioHang.GioHang;
import SD94.entity.gioHang.GioHangChiTiet;
import SD94.entity.khachHang.KhachHang;
import SD94.entity.sanPham.KichCo;
import SD94.entity.sanPham.MauSac;
import SD94.entity.sanPham.SanPham;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.gioHang.GioHangChiTietRepository;
import SD94.repository.gioHang.GioHangRepository;
import SD94.repository.khachHang.KhachHangRepository;
import SD94.repository.sanPham.KichCoRepository;
import SD94.repository.sanPham.MauSacRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import SD94.repository.sanPham.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/customer/cart")
public class AddToCartController {

    @Autowired
    KichCoRepository kichCoRepository;

    @Autowired
    MauSacRepository mauSacRepository;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    GioHangRepository gioHangRepository;

    @Autowired
    GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @PostMapping("/addToCart")
    public ResponseEntity addToCart(@RequestBody GioHangDTO dto) {

        MauSac mauSac = mauSacRepository.findByMaMauSac(dto.getMaMauSac());
        KichCo kichCo = kichCoRepository.findByKichCo(dto.getKichCo());
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.getSanPhamChiTiet(mauSac.getId(), kichCo.getId(), dto.getSan_pham_id());
        KhachHang khachHang = khachHangRepository.findByEmail(dto.getEmail());
        SanPham sanPham = sanPhamRepository.findByID(dto.getSan_pham_id());
        GioHang gioHang = gioHangRepository.findbyCustomerID(khachHang.getId());
        Optional<GioHangChiTiet> optionalGioHangChiTiet = gioHangChiTietRepository.checkGioHangChiTiet(sanPhamChiTiet.getId(), gioHang.getId());

        if (optionalGioHangChiTiet.isPresent()) {
            GioHangChiTiet gioHangChiTiet = optionalGioHangChiTiet.get();
            int soLuongMoi = gioHangChiTiet.getSoLuong() + dto.getSoLuong();
            float thanhTienMoi = sanPham.getGia() * soLuongMoi;
            gioHangChiTiet.setSoLuong(soLuongMoi);
            gioHangChiTiet.setThanhTien(BigDecimal.valueOf(thanhTienMoi));
            gioHangChiTietRepository.save(gioHangChiTiet);
        } else {
            float thanhTien = dto.getSoLuong() * sanPham.getGia();

            GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGioHang(gioHang);
            gioHangChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
            gioHangChiTiet.setDonGia(dto.getDonGia());
            gioHangChiTiet.setSoLuong(dto.getSoLuong());
            gioHangChiTiet.setThanhTien(BigDecimal.valueOf(thanhTien));
            gioHangChiTietRepository.save(gioHangChiTiet);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
