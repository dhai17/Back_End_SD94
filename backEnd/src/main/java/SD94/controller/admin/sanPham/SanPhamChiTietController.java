package SD94.controller.admin.sanPham;

import SD94.dto.HoaDonChiTietDTO;
import SD94.entity.sanPham.KichCo;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.sanPham.KichCoRepository;
import SD94.repository.sanPham.MauSacRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import SD94.service.service.SanPhamChiTietService;
import SD94.service.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sanPhamChiTiet")
public class SanPhamChiTietController {

    @Autowired
    SanPhamChiTietRepository productDetailsRepository;

    @Autowired
    SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    MauSacRepository productColorRepository;

    @Autowired
    KichCoRepository productSizeRepository;

    //Hien thi
    @GetMapping("/danhSach")
    public ResponseEntity<List<SanPhamChiTiet>> getColor() {
        List<SanPhamChiTiet> list = productDetailsRepository.findAll();
        return ResponseEntity.ok().body(list);
    }

    //Hien thi theo id
    @GetMapping("/chinhSua/{id}")
    public SanPhamChiTiet editProductDetails(@PathVariable("id") Long id){
        return productDetailsRepository.findByID(id);
    }

    //Sửa và lưu
    @PutMapping("/luuChinhSua")
    public ResponseEntity<SanPhamChiTiet> saveUpdate(@RequestBody SanPhamChiTiet sanPhamChiTiet){
        return sanPhamChiTietService.saveEdit(sanPhamChiTiet);
    }

    //Xóa
    @DeleteMapping("/xoa/{id}")
    public ResponseEntity<List<SanPhamChiTiet>> deleteProductDetails(@PathVariable("id") Long id){
//        System.out.println(id);
        return sanPhamChiTietService.deleteProductDetails(id);
    }

    @PostMapping("/themMoi")
    public ResponseEntity<SanPhamChiTiet> saveCreate(@RequestBody HoaDonChiTietDTO detailsDTO){
        KichCo size = productSizeRepository.findByID(detailsDTO.getIdSize());
        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
        sanPhamChiTiet.setKichCo(size);
        sanPhamChiTiet.setSoLuong(detailsDTO.getQuantity());
        productDetailsRepository.save(sanPhamChiTiet);
        return null;
    }

    //Tìm kiếm
    @RequestMapping("/timKiem={search}")
    public List<SanPhamChiTiet> searchAll(@PathVariable("search") String search){
        return sanPhamChiTietService.searchAllProductDetails(search);
    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<SanPhamChiTiet> searchDate(@PathVariable("searchDate") String search){
        return sanPhamChiTietService.searchDateProductDetails(search);
    }

}
