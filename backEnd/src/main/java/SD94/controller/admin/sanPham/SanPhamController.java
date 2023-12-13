package SD94.controller.admin.sanPham;

import SD94.dto.HoaDonDTO;
import SD94.dto.SanPhamDTO;
import SD94.entity.sanPham.*;
import SD94.repository.sanPham.*;
import SD94.service.service.MailService;
import SD94.service.service.SanPhamService;
import SD94.validator.DatHangCheckoutValidate;
import SD94.validator.SanPhamValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/sanPham")
public class SanPhamController {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    MailService mailService;

    @GetMapping("/danhSach")
    public ResponseEntity<?> getProduct() {
        List<SanPham> list = sanPhamRepository.findAll();
        List<SanPhamDTO> dtoList = new ArrayList<>();
        for (SanPham sp : list) {
            SanPhamDTO sanPhamDTO = new SanPhamDTO();
            sanPhamDTO.setId(sp.getId());
            sanPhamDTO.setTenSanPham(sp.getTenSanPham());
            sanPhamDTO.setGia(sp.getGia());
            sanPhamDTO.setTrangThai(sp.getTrangThai());
            String hinh_anh = hinhAnhRepository.getTenAnhSanPham_HienThiDanhSach(sp.getId());
            sanPhamDTO.setAnh_san_pham(hinh_anh);

            dtoList.add(sanPhamDTO);
        }
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/ChiTietSanPham")
    public List<Object> chiTietSanPham(@RequestParam long id_SanPham) {
        return sanPhamService.chiTietSanPham(id_SanPham);
    }

    //Tạo mới và gen ra sản phẩm chi tiết
    @PostMapping("/TaoSanPham")
    public ResponseEntity<?> saveCreate(@RequestBody SanPhamDTO sanPhamDTO) {
        ResponseEntity<?> response = SanPhamValidate.checkTaoSanPham(sanPhamDTO);
        if (!response.getStatusCode().is2xxSuccessful()) {
            return response;
        } else {
            return sanPhamService.taoSanPham(sanPhamDTO);
        }
    }

    @DeleteMapping("/xoa/{id}")
    public ResponseEntity<List<SanPham>> deleteProduct(@PathVariable("id") Long id) {
        return sanPhamService.deleteProduct(id);
    }

    @DeleteMapping("/xoa-san-pham-chi-tiet/{id}")
    public void deleteProductDetails(@RequestBody SanPhamChiTiet sanPhamChiTiet) {
        sanPhamRepository.deleteById(sanPhamChiTiet.getId());
    }

    @GetMapping("/chinhSua/{id}")
    public SanPham editProduct(@PathVariable("id") Long id) {
        return sanPhamRepository.findByID(id);
    }

    @PutMapping("/luuChinhSua")
    public ResponseEntity<SanPham> saveUpdate(@RequestBody SanPhamDTO sanPham) {
        return sanPhamService.saveEdit(sanPham);
    }

    @RequestMapping("/timKiem={search}")
    public ResponseEntity<?> searchAll(@PathVariable("search") String search) {
        return sanPhamService.searchAllProduct(search);
    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<SanPham> searchDate(@PathVariable("searchDate") String search) {
        return sanPhamService.searchDateProduct(search);
    }

    @GetMapping("/getAnhSanPham/{id}")
    public ResponseEntity<?> getAnhMacDinh(@PathVariable("id") long id_sanPham) {
        List<HinhAnh> hinhAnhs = hinhAnhRepository.getHinhAnhByProductID(id_sanPham);
        return ResponseEntity.ok().body(hinhAnhs);
    }

}