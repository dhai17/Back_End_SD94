package SD94.controller.banHang.taiQuay;

import SD94.dto.*;
import SD94.entity.gioHang.GioHang;
import SD94.entity.gioHang.GioHangChiTiet;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.entity.hoaDon.TrangThai;
import SD94.entity.khachHang.KhachHang;
import SD94.entity.khuyenMai.KhuyenMai;
import SD94.entity.nhanVien.NhanVien;
import SD94.entity.sanPham.KichCo;
import SD94.entity.sanPham.MauSac;
import SD94.entity.sanPham.SanPham;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.gioHang.GioHangRepository;
import SD94.repository.hoaDon.HoaDonChiTietRepository;
import SD94.repository.hoaDon.HoaDonRepository;
import SD94.repository.hoaDon.TrangThaiRepository;
import SD94.repository.khachHang.KhachHangRepository;
import SD94.repository.khuyenMai.KhuyenMaiRepository;
import SD94.repository.nhanVien.NhanVienRepository;
import SD94.repository.sanPham.KichCoRepository;
import SD94.repository.sanPham.MauSacRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import SD94.repository.sanPham.SanPhamRepository;
import SD94.service.service.InHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/banHang/taiQuay")
public class BanHangTaiQuayController {
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

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    TrangThaiRepository trangThaiRepository;

    @Autowired
    KichCoRepository kichCoRepository;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;

    @Autowired
    GioHangRepository gioHangRepository;

    @Autowired
    InHoaDonService inHoaDonService;

    @GetMapping("/danhSachHoaDon")
    public List<HoaDon> danhSachHoaDonCho() {
        List<HoaDon> hoaDonList = billRepository.getDanhSachHoaDonCho();
        return hoaDonList;
    }

    @GetMapping("/getHoaDonChitiet/{id}")
    public List<HoaDonChiTiet> getHoaDonChiTiet(@PathVariable("id") long id) {
        List<HoaDonChiTiet> hoaDonChiTiets = billDetailsRepository.findByIDBill(id);
        return hoaDonChiTiets;
    }

    @PostMapping("/taoHoaDon")
    public ResponseEntity<?> taoHoaDon(@RequestBody HoaDonDTO hoaDonDTO) {
        List<HoaDon> hoaDonList = billRepository.getDanhSachHoaDonCho();
        int check = hoaDonList.size();
        if (check < 8) {
            NhanVien nhanVien = nhanVienRepository.findByEmail(hoaDonDTO.getEmail_user());
            TrangThai trangThai = trangThaiRepository.findByID(6L);
            HoaDon hoaDon = new HoaDon();
            hoaDon.setTrangThai(trangThai);
            hoaDon.setLoaiHoaDon(1);
            hoaDon.setCreatedby(nhanVien.getHoTen());
            hoaDon.setCreatedDate(new Date());
            billRepository.save(hoaDon);

            hoaDon.setMaHoaDon("HD" + hoaDon.getId());
            billRepository.save(hoaDon);
            return ResponseEntity.ok(hoaDon.getId());

        }else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Số lượng hóa đơn chờ đã đạt giới hạn");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/themSanPham")
    public ResponseEntity themSanPham(@RequestBody SanPhamDTO dto) {
        MauSac mauSac = mauSacRepository.findByMaMauSac(dto.getMaMauSac());
        KichCo kichCo = kichCoRepository.findByKichCo(dto.getKichCoDaChon());
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.getSanPhamChiTiet(mauSac.getId(), kichCo.getId(), dto.getSan_pham_id());
        SanPham sanPham = sanPhamRepository.findByID(dto.getSan_pham_id());
        HoaDon hoaDon = hoaDonRepository.findByID(dto.getId_hoaDon());
        Optional<HoaDonChiTiet> optionalHDCT = hoaDonChiTietRepository.checkHDCT(hoaDon.getId(), sanPhamChiTiet.getId());

        if (optionalHDCT.isPresent()) {
            HoaDonChiTiet hoaDonChiTiet = optionalHDCT.get();
            int soLuongMoi = hoaDonChiTiet.getSoLuong() + dto.getSoLuong();
            int thanhTienMoi = (int) (sanPham.getGia() * soLuongMoi);
            hoaDonChiTiet.setSoLuong(soLuongMoi);
            hoaDonChiTiet.setThanhTien(thanhTienMoi);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        } else {
            int thanhTien = (int) (dto.getSoLuong() * sanPham.getGia());

            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
            hoaDonChiTiet.setDonGia(dto.getDonGia());
            hoaDonChiTiet.setSoLuong(dto.getSoLuong());
            hoaDonChiTiet.setThanhTien(thanhTien);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        }

        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findByIDBill(dto.getId_hoaDon());

        int totalAmount = 0;
        for (HoaDonChiTiet hdct : hoaDonChiTiets) {
            totalAmount += hdct.getThanhTien();
        }

        hoaDon.setTongTienHoaDon(totalAmount);
        hoaDon.setTongTienDonHang(totalAmount);
        hoaDonRepository.save(hoaDon);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getHoaDon/{id}")
    public HoaDon getHoaDon(@PathVariable("id") long id) {
        HoaDon hoaDon = billRepository.findByID(id);
        return hoaDon;
    }

    @PostMapping("/xoaHoaDon")
    public List<HoaDon> xoaHoaDon(@RequestBody HoaDonDTO hoaDonDTO) {
        HoaDon hoaDon = billRepository.findByID(hoaDonDTO.getId());
        TrangThai trangThai = trangThaiRepository.findByID(8L);
        hoaDon.setTrangThai(trangThai);
        billRepository.save(hoaDon);

        List<HoaDon> hoaDon2 = billRepository.getDanhSachHoaDonCho();
        return hoaDon2;
    }



    @PostMapping("/xoa-san-pham")
    public String xoaSanPham(@RequestParam("id_bill") long id_bill,
                             @RequestParam("id_bill_details") long id_bill_details) {
        Optional<HoaDonChiTiet> detailedInvoice = billDetailsRepository.findById(id_bill_details);
        Optional<HoaDon> optionalBill = billRepository.findById(id_bill);
        if (detailedInvoice.isPresent() && optionalBill.isPresent()) {
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
        KhuyenMai khuyenMai = khuyenMaiRepository.findByNameKM(hoaDonDTO.getTenMaGiamGia());
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
        return hoaDon2;
    }

    @PostMapping("/add/KhachHang")
    public ResponseEntity<?> addKhachHang(@RequestBody KhachHangDTO dto) {
        HoaDon hoaDon = billRepository.findByID(dto.getId_hoaDon());
        if (dto.getId() != null) {
            KhachHang khachHang = khachHangRepository.findByID(dto.getId());
            hoaDon.setKhachHang(khachHang);
            hoaDon.setNguoiNhan(khachHang.getHoTen());
            hoaDon.setSDTNguoiNhan(khachHang.getSoDienThoai());
            billRepository.save(hoaDon);
        } else {
            KhachHang khachHangCheck = khachHangRepository.findBySDT(dto.getSoDienThoai());
            if (khachHangCheck != null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Số điện thoại này đã tồn tại");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            } else {
                KhachHang kh = new KhachHang();
                kh.setHoTen(dto.getHoTen());
                kh.setSoDienThoai(dto.getSoDienThoai());
                khachHangRepository.save(kh);

                GioHang gioHang = new GioHang();
                gioHang.setKhachHang(kh);
                gioHangRepository.save(gioHang);

                hoaDon.setKhachHang(kh);
                hoaDon.setNguoiNhan(dto.getHoTen());
                hoaDon.setSDTNguoiNhan(dto.getSoDienThoai());
                hoaDonRepository.save(hoaDon);
            }
        }

        HoaDon hoaDonReturn = hoaDonRepository.findByID(dto.getId_hoaDon());
        return ResponseEntity.ok(hoaDonReturn);
    }

    @PostMapping("/huyDon")
    public ResponseEntity huyDon(@RequestBody HoaDonDTO hoaDonDTO) {
        HoaDon hoaDon = billRepository.findByID(hoaDonDTO.getId());
        TrangThai trangThai = trangThaiRepository.findByID(8L);

        hoaDon.setTrangThai(trangThai);
        hoaDonRepository.save(hoaDon);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hủy đơn thành công");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/thanhToan")
    public ResponseEntity thanhToan(@RequestBody HoaDonDTO hoaDonDTO) {
        HoaDon hoaDon = billRepository.findByID(hoaDonDTO.getId());
        TrangThai trangThai = trangThaiRepository.findByID(7L);
        NhanVien nhanVien = nhanVienRepository.findByEmail(hoaDonDTO.getEmail_user());

        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findByIDBill(hoaDonDTO.getId());
        for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findByID(hoaDonChiTiet.getSanPhamChiTiet().getId());
            sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - hoaDonChiTiet.getSoLuong());
            sanPhamChiTietRepository.save(sanPhamChiTiet);
        }

        hoaDon.setTrangThai(trangThai);
        hoaDon.setNhanVien(nhanVien);
        hoaDonRepository.save(hoaDon);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Thanh toán thành công");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/inHoaDon/{id}")
    public ResponseEntity<byte[]> inHoaDon(@PathVariable("id") long id) {
        return inHoaDonService.generatePdf(id);
    }

    @PostMapping("/xoaHDCT")
    public ResponseEntity<?> xoaHDCT(@RequestBody HoaDonChiTietDTO dto) {
        Optional<HoaDonChiTiet> optionalHoaDonChiTiet = hoaDonChiTietRepository.findById(dto.getId());
        if(optionalHoaDonChiTiet.isPresent()){
            HoaDonChiTiet hoaDonChiTiet = optionalHoaDonChiTiet.get();
            hoaDonChiTiet.setDeleted(true);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Xóa thành công");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
