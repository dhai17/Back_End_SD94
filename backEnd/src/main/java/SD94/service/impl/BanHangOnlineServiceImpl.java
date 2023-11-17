package SD94.service.impl;

import SD94.dto.GioHangDTO;
import SD94.dto.HoaDonChiTietDTO;
import SD94.dto.HoaDonDTO;
import SD94.entity.gioHang.GioHang;
import SD94.entity.gioHang.GioHangChiTiet;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.entity.hoaDon.TrangThai;
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
import SD94.repository.sanPham.HinhAnhRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import SD94.service.service.BanHangOnlineService;
import SD94.service.service.HoaDonDatHangService;
import SD94.service.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.math.RoundingMode;
import java.util.*;

@Service
public class BanHangOnlineServiceImpl implements BanHangOnlineService {
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

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    private Long idBill;


    @Override
    public Long checkout(GioHangDTO dto) {
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
        return hoaDon.getId();
    }

    @Override
    public ResponseEntity<HoaDon> getHoaDon(long id_hoa_don) {
        HoaDon hoaDon = billRepository.findByID(id_hoa_don);
        return ResponseEntity.ok(hoaDon);
    }

    @Override
    public ResponseEntity<?> getHoaDonChiTiet(long id_hoa_don) {
        List<HoaDonChiTiet> hoaDonChiTiets = billDetailsRepository.findByIDBill(id_hoa_don);
        List<HoaDonChiTietDTO> dto = new ArrayList<>();
        for(HoaDonChiTiet hoaDonChiTiet: hoaDonChiTiets){
            SanPhamChiTiet sanPhamChiTiet = hoaDonChiTiet.getSanPhamChiTiet();
            HoaDon hoaDon = hoaDonChiTiet.getHoaDon();
            String anh_san_pham = hinhAnhRepository.getAnhMacDinh(sanPhamChiTiet.getSanPham().getId(), sanPhamChiTiet.getMauSac().getId());

            HoaDonChiTietDTO hoaDonChiTietDTO = new HoaDonChiTietDTO();
            hoaDonChiTietDTO.setId(hoaDonChiTiet.getId());
            hoaDonChiTietDTO.setIdProduct(sanPhamChiTiet.getSanPham().getId());
            hoaDonChiTietDTO.setIdColor(sanPhamChiTiet.getMauSac().getId());
            hoaDonChiTietDTO.setIdSize(sanPhamChiTiet.getKichCo().getId());
            hoaDonChiTietDTO.setSoLuong(hoaDonChiTiet.getSoLuong());
            hoaDonChiTietDTO.setDonGia(hoaDonChiTiet.getDonGia());
            hoaDonChiTietDTO.setThanhTien(hoaDonChiTiet.getThanhTien());
            hoaDonChiTietDTO.setHoaDon(hoaDon);
            hoaDonChiTietDTO.setSanPhamChiTiet(sanPhamChiTiet);
            hoaDonChiTietDTO.setAnhSanPham(anh_san_pham);

            dto.add(hoaDonChiTietDTO);
        }
        return ResponseEntity.ok().body(dto);
    }

    @Override
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

    @Override
    public ResponseEntity<?> addDiscount(HoaDonDTO hoaDonDTO) {
        KhuyenMai khuyenMai = discountRepository.findByNameKM(hoaDonDTO.getTenMaGiamGia());
        if (khuyenMai == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("mess", "Khuyen mai khong ton tai");
            return ResponseEntity.badRequest().body(response);
        }
        long now = new Date().getTime();
        long a = khuyenMai.getNgayKetThuc().getTime();
        if (a < now) {
            Map<String, Object> response = new HashMap<>();
            response.put("mess", "Khuyen mai da het han");
            return ResponseEntity.badRequest().body(response);
        } else {
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
                hoaDon.setTongTienDonHang(tongTien);
                hoaDon.setTienGiam(tongTienSauGiamCheck);
                hoaDon.setKhuyenMai(khuyenMai);
                billRepository.save(hoaDon);
            }
            HoaDon hoaDon2 = billRepository.findByID(hoaDonDTO.getId());
            return ResponseEntity.ok(hoaDon2);
        }
    }

    @Transactional
    @Override
    public ResponseEntity datHang(HoaDonDTO dto) {

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
