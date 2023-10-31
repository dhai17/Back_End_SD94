package SD94.controller.admin.discount;

import SD94.entity.khuyenMai.KhuyenMai;
import SD94.repository.DiscountRepository;
import SD94.service.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class DiscountController {
    @Autowired
    KhuyenMaiService discountService;

    @Autowired
    DiscountRepository discountRepository;

    @GetMapping("/api/discount/list")
    public List<KhuyenMai> listDiscount() {
        return discountService.findAllDiscount();
    }

    @GetMapping("/api/discount/edit/discountID={id}")
    public KhuyenMai editDiscount(@PathVariable("id") Long id) {
        return discountRepository.findByID(id);
    }

    @PutMapping("/api/discount/saveUpdate")
    public ResponseEntity<KhuyenMai> saveUpdate(@RequestBody KhuyenMai khuyenMaiUpdate) {
        return discountService.saveEdit(khuyenMaiUpdate);
    }

    @PutMapping("/api/discount/deleteDiscount={id}")
    public ResponseEntity<List<KhuyenMai>> deleteDiscount(@PathVariable("id") Long id) {
        return discountService.deleteDiscount(id);
    }

    @PostMapping("/api/discount/saveCreate")
    public ResponseEntity<KhuyenMai> saveCreate(@RequestBody KhuyenMai khuyenMaiCreate) {
        return discountService.saveCreate(khuyenMaiCreate);
    }

    @RequestMapping("/api/discount/search={search}")
    public List<KhuyenMai> searchAllDiscount(@PathVariable("search") String search) {
        return discountService.searchAllDiscount(search);
    }

    @RequestMapping("/api/discount/searchDate={searchDate}")
    public List<KhuyenMai> searchDateDiscount(@PathVariable("searchDate") String searchDate) {
        return discountService.searchDateDiscount(searchDate);
    }
}
