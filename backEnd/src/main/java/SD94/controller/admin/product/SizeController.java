package SD94.controller.admin.product;

import SD94.entity.ProductSize;
import SD94.repository.ProductSizeRepository;
import SD94.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class SizeController {

    @Autowired
    ProductSizeRepository repository;

    @Autowired
    ProductSizeService productSizeService;

    @GetMapping("/size/list")
    public ResponseEntity<List<ProductSize>> getSize() {
        List<ProductSize> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/size/saveCreate")
    public ResponseEntity<ProductSize> saveCreate(@RequestBody ProductSize productSize) {
        return productSizeService.saveCreate(productSize);
    }

    @PutMapping("/size/delete/{id}")
    public ResponseEntity<List<ProductSize>> delete(@PathVariable("id") Long id) {
        return productSizeService.deleteProductSize(id);
    }

    @GetMapping("/size/edit={id}")
    public ProductSize edit(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/size/saveUpdate")
    public ResponseEntity<ProductSize> saveUpdate(@RequestBody ProductSize productSize) {
        return productSizeService.saveEdit(productSize);
    }
}
