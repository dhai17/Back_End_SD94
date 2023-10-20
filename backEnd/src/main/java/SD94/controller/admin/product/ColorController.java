package SD94.controller.admin.product;

import SD94.entity.ProductColor;
import SD94.repository.ProductColorRepository;
import SD94.service.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ColorController {
    @Autowired
    ProductColorRepository repository;

    @Autowired
    ProductColorService productColorService;

    @GetMapping("/color/list")
    public ResponseEntity<List<ProductColor>> getColor() {
        List<ProductColor> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/color/saveCreate")
    public ResponseEntity<ProductColor> saveCreate(@RequestBody ProductColor productColor) {
        return productColorService.saveCreate(productColor);
    }

    @PutMapping("/color/delete/{id}")
    public ResponseEntity<List<ProductColor>> delete(@PathVariable("id") Long id) {
        return productColorService.deleteProductColor(id);
    }

    @GetMapping("/color/edit/{id}")
    public ProductColor edit(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/color/saveUpdate")
    public ResponseEntity<ProductColor> saveUpdate(@RequestBody ProductColor productColor) {
        return productColorService.saveEdit(productColor);
    }

}
