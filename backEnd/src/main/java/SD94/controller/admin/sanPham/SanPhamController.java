package SD94.controller.admin.sanPham;

import SD94.dto.SanPhamDTO;
import SD94.dto.ThemMoiSanPhamDTO;
import SD94.entity.sanPham.*;
import SD94.repository.sanPham.*;
import SD94.service.service.SanPhamChiTietService;
import SD94.service.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/sanPham")
public class SanPhamController {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    SanPhamService sanPhamService;

    @GetMapping("/danhSach")
    public ResponseEntity<List<SanPham>> getProduct() {
        List<SanPham> list = sanPhamRepository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/ChiTietSanPham")
    public List<Object> chiTietSanPham(@RequestParam long id_SanPham) {
        return sanPhamService.chiTietSanPham(id_SanPham);
    }


    //Tạo mới và gen ra sản phẩm chi tiết
    @PostMapping("/TaoSanPham")
    public List<SanPhamChiTiet> saveCreate(@RequestBody SanPhamDTO sanPhamDTO) {
        return sanPhamService.taoSanPham(sanPhamDTO);
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
    public ResponseEntity<SanPham> saveUpdate(@RequestBody SanPham sanPham) {
        return sanPhamService.saveEdit(sanPham);
    }

    @RequestMapping("/timKiem={search}")
    public List<SanPham> searchAll(@PathVariable("search") String search) {
        return sanPhamService.searchAllProduct(search);
    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<SanPham> searchDate(@PathVariable("searchDate") String search) {
        return sanPhamService.searchDateProduct(search);
    }

}
