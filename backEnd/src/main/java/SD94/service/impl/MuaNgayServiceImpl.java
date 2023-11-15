package SD94.service.impl;

import SD94.dto.HoaDonDTO;
import SD94.dto.SanPhamDTO;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.entity.hoaDon.TrangThai;
import SD94.entity.khuyenMai.KhuyenMai;
import SD94.entity.sanPham.KichCo;
import SD94.entity.sanPham.MauSac;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.hoaDon.HoaDonChiTietRepository;
import SD94.repository.hoaDon.HoaDonRepository;
import SD94.repository.hoaDon.TrangThaiRepository;
import SD94.repository.khuyenMai.KhuyenMaiRepository;
import SD94.repository.sanPham.KichCoRepository;
import SD94.repository.sanPham.MauSacRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import SD94.service.service.MuaNgayService;
import SD94.service.service.HoaDonDatHangService;
import SD94.validator.DataIsEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MuaNgayServiceImpl implements MuaNgayService {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    MauSacRepository mauSacRepository;

    @Autowired
    KichCoRepository kichCoRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;

    @Autowired
    TrangThaiRepository trangThaiRepository;

    @Autowired
    HoaDonDatHangService hoaDonDatHangService;

    private Long idBill;


    @Override
    public Long muaNgayCheckOut(SanPhamDTO dto) {
        MauSac mauSac = mauSacRepository.findByMaMauSac(dto.getMaMauSac());
        KichCo kichCo = kichCoRepository.findByKichCo(dto.getKichCoDaChon());
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.getSanPhamChiTiet(mauSac.getId(), kichCo.getId(), dto.getSan_pham_id());
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
        return hoaDon.getId();
    }

    @Override
    public ResponseEntity<HoaDon> getBill() {
        if (idBill != null) {
            Optional<HoaDon> optionalBill = hoaDonRepository.findById(idBill);
            if (optionalBill.isPresent()) {
                HoaDon hoaDon = optionalBill.get();
                return ResponseEntity.ok(hoaDon);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public HoaDon addDiscount(HoaDonDTO hoaDonDTO) {
        KhuyenMai khuyenMai = khuyenMaiRepository.findByNameKM(hoaDonDTO.getTenMaGiamGia());
        HoaDon hoaDon = hoaDonRepository.findByID(hoaDonDTO.getId());
        int phanTramGiam = khuyenMai.getPhanTramGiam();
        int tienGiamToiDa = khuyenMai.getTienGiamToiDa();
        int tongTienBill = hoaDon.getTongTienDonHang();

        int tongTienSauGiamCheck = (tongTienBill * phanTramGiam) / 100;
        if (tongTienSauGiamCheck > tienGiamToiDa) {
            int tongTienSauGiam = hoaDon.getTongTienDonHang() - khuyenMai.getTienGiamToiDa();
            hoaDon.setTienGiam(hoaDon.getTongTienDonHang() - tongTienSauGiam);
            hoaDon.setTongTienDonHang(tongTienSauGiam);
            hoaDon.setKhuyenMai(khuyenMai);
            hoaDonRepository.save(hoaDon);
        } else {
            int tongTien = hoaDon.getTongTienDonHang() - tongTienSauGiamCheck;
            hoaDon.setTongTienDonHang(tongTien);
            hoaDon.setTienGiam(tongTienSauGiamCheck);
            hoaDon.setKhuyenMai(khuyenMai);
            hoaDonRepository.save(hoaDon);
        }

        HoaDon hoaDon2 = hoaDonRepository.findByID(hoaDonDTO.getId());
        return hoaDon2;
    }

    @Transactional
    @Override
    public ResponseEntity datHang(HoaDonDTO dto) {
        HoaDon hoaDon = hoaDonRepository.findByID(dto.getId());
        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findByIDBill(hoaDon.getId());
        for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findByID(hoaDonChiTiet.getSanPhamChiTiet().getId());
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
    }
}
