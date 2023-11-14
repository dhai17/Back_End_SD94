package SD94.controller.banHang.banHangOnline;

import SD94.dto.HoaDonDTO;
import SD94.dto.SanPhamDTO;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.repository.hoaDon.HoaDonChiTietRepository;
import SD94.repository.hoaDon.HoaDonRepository;
import SD94.service.service.MuaNgayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/muaNgay")
public class MuaNgayController {
    @Autowired
    MuaNgayService muaNgayService;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @PostMapping("/check-out")
    public ResponseEntity<?> muaNgayCheckOut(@RequestBody SanPhamDTO dto) {
<<<<<<< HEAD

        if (dto.getMaMauSac() == null || dto.getKichCoDaChon() == null || dto.getSan_pham_id() == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Trống dữ liệu");
            return ResponseEntity.badRequest().body(response);
        } else {
            MauSac mauSac = mauSacRepository.findByMaMauSac(dto.getMaMauSac());
            KichCo kichCo = kichCoRepository.findByKichCo(dto.getKichCoDaChon());
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.getSanPhamChiTiet(mauSac.getId(), kichCo.getId(), dto.getSan_pham_id());
            if (sanPhamChiTiet.getTrangThai() == 1) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Sản phẩm đã dừng bán");
                return ResponseEntity.badRequest().body(response);
            } else {
                int tongTien = dto.getDonGia() * dto.getSoLuong();
                HoaDon hoaDon = new HoaDon();
                hoaDon.setCreatedDate(new Date());
                hoaDon.setTongTienDonHang(tongTien);
                hoaDon.setTongTienHoaDon(tongTien);
                hoaDonRepository.save(hoaDon);
                hoaDon.setMaHoaDon("HD" + hoaDon.getId());
                hoaDonRepository.save(hoaDon);

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setDonGia(Math.round(sanPhamChiTiet.getSanPham().getGia()));
                hoaDonChiTiet.setHoaDon(hoaDon);
                hoaDonChiTiet.setThanhTien(tongTien);
                hoaDonChiTiet.setSoLuong(dto.getSoLuong());
                hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
                hoaDonChiTietRepository.save(hoaDonChiTiet);
                idBill = hoaDon.getId();
                return ResponseEntity.ok(hoaDon.getId());
            }
        }


=======
        return ResponseEntity.ok(muaNgayService.muaNgayCheckOut(dto));
>>>>>>> 8cf12f31e6c7d90f041eecd9c0dde5e03c3aec11
    }

    @GetMapping("/getHoaDon/{id}")
    public ResponseEntity<HoaDon> getHoaDonMuaNgay(@PathVariable("id") long id_HoaDon) {
        HoaDon hoaDon = hoaDonRepository.findByID(id_HoaDon);
        return ResponseEntity.ok(hoaDon);
    }

    @GetMapping("/getHoaDonChiTiet/{id}")
    public List<HoaDonChiTiet> getHoaDonChiTiet(@PathVariable("id") long id_HoaDon) {
        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findByIDBill(id_HoaDon);
        return hoaDonChiTiets;
    }

    @GetMapping("/check-out")
    public ResponseEntity<HoaDon> getBill() {
        return muaNgayService.getBill();
    }

    @PostMapping("/add/khuyenMai")
    public HoaDon addDiscount(@RequestBody HoaDonDTO hoaDonDTO) {
        HoaDon hoaDon = muaNgayService.addDiscount(hoaDonDTO);
        return hoaDon;
    }

    @PostMapping("/datHang")
    public ResponseEntity datHang(@RequestBody HoaDonDTO dto) {
<<<<<<< HEAD
        HoaDon hoaDon = hoaDonRepository.findByID(dto.getId());
        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findByIDBill(hoaDon.getId());
        for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findByID(hoaDonChiTiet.getSanPhamChiTiet().getId());
            System.out.println(sanPhamChiTiet);
            sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - hoaDonChiTiet.getSoLuong());
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
        hoaDon.setNguoiNhan(dto.getNguoiTao());
        hoaDonRepository.save(hoaDon);

        hoaDonDatHangService.createTimeLine("Tạo đơn hàng", 1L, hoaDon.getId(), dto.getNguoiTao());
        return ResponseEntity.ok(HttpStatus.OK);
=======
        return muaNgayService.datHang(dto);
>>>>>>> 8cf12f31e6c7d90f041eecd9c0dde5e03c3aec11
    }

}
