package SD94.controller.admin.product;

import SD94.entity.product.Size;
import SD94.repository.ProductSizeRepository;
import SD94.service.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class SizeController {

    @Autowired
    ProductSizeRepository repository;

    @Autowired
    ProductSizeService productSizeService;

    @GetMapping("/size/list")
    public ResponseEntity<List<Size>> getSize() {
        List<Size> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/size/saveCreate")
    public ResponseEntity<Size> saveCreate(@RequestBody Size size) {
        return productSizeService.saveCreate(size);
    }

    @PutMapping("/size/delete/{id}")
    public ResponseEntity<List<Size>> delete(@PathVariable("id") Long id) {
        return productSizeService.deleteProductSize(id);
    }

    @GetMapping("/size/edit={id}")
    public Size edit(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/size/saveUpdate")
    public ResponseEntity<Size> saveUpdate(@RequestBody Size size) {
        return productSizeService.saveEdit(size);
    }
}
