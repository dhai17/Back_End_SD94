package SD94.controller.admin.khuyenMai;

import SD94.entity.khuyenMai.KhuyenMai;
import SD94.repository.khuyenMai.KhuyenMaiRepository;
import SD94.service.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/khuyenMai")
public class KhuyenMaiController {
    @Autowired
    KhuyenMaiService khuyenMaiService;

    @Autowired
    KhuyenMaiRepository discountRepository;

    @GetMapping("/danhSach")
    public List<KhuyenMai> listDiscount() {
        return khuyenMaiService.findAllDiscount();
    }

    @GetMapping("/chinhSua/{id}")
    public KhuyenMai editDiscount(@PathVariable("id") Long id) {
        return discountRepository.findByID(id);
    }

    @PutMapping("/luuChinhSua")
    public ResponseEntity<KhuyenMai> saveUpdate(@RequestBody KhuyenMai khuyenMaiUpdate) {
        return khuyenMaiService.saveEdit(khuyenMaiUpdate);
    }

    @PutMapping("/xoa/{id}")
    public ResponseEntity<List<KhuyenMai>> deleteDiscount(@PathVariable("id") Long id) {
        return khuyenMaiService.deleteDiscount(id);
    }

    @PostMapping("/themMoi")
    public ResponseEntity<KhuyenMai> saveCreate(@RequestBody KhuyenMai khuyenMaiCreate) {
        return khuyenMaiService.saveCreate(khuyenMaiCreate);
    }

    @RequestMapping("/timKiem={search}")
    public List<KhuyenMai> searchAllDiscount(@PathVariable("search") String search) {
        return khuyenMaiService.searchAllDiscount(search);
    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<KhuyenMai> searchDateDiscount(@PathVariable("searchDate") String searchDate) {
        return khuyenMaiService.searchDateDiscount(searchDate);
    }
}
