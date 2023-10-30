package SD94.controller.admin.discount;

import SD94.entity.Discount;
import SD94.repository.DiscountRepository;
import SD94.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @Autowired
    DiscountRepository discountRepository;

    @GetMapping("/api/discount/list")
    public List<Discount> listDiscount() {
        return discountService.findAllDiscount();
    }

    @GetMapping("/api/discount/edit/discountID={id}")
    public Discount editDiscount(@PathVariable("id") Long id) {
        return discountRepository.findByID(id);
    }

    @PutMapping("/api/discount/saveUpdate")
    public ResponseEntity<Discount> saveUpdate(@RequestBody Discount discountUpdate) {
        return discountService.saveEdit(discountUpdate);
    }

    @PutMapping("/api/discount/deleteDiscount={id}")
    public ResponseEntity<List<Discount>> deleteDiscount(@PathVariable("id") Long id) {
        return discountService.deleteDiscount(id);
    }

    @PostMapping("/api/discount/saveCreate")
    public ResponseEntity<Discount> saveCreate(@RequestBody Discount discountCreate) {
        return discountService.saveCreate(discountCreate);
    }

    @RequestMapping("/api/discount/search={search}")
    public List<Discount> searchAllDiscount(@PathVariable("search") String search) {
        return discountService.searchAllDiscount(search);
    }

    @RequestMapping("/api/discount/searchDate={searchDate}")
    public List<Discount> searchDateDiscount(@PathVariable("searchDate") String searchDate) {
        return discountService.searchDateDiscount(searchDate);
    }
}
