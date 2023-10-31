package SD94.controller.admin.product;

import SD94.entity.product.Color;
import SD94.repository.ProductColorRepository;
import SD94.service.service.ProductColorService;
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
    public ResponseEntity<List<Color>> getColor() {
        List<Color> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/color/saveCreate")
    public ResponseEntity<Color> saveCreate(@RequestBody Color color) {
        return productColorService.saveCreate(color);
    }

    @PutMapping("/color/delete/{id}")
    public ResponseEntity<List<Color>> delete(@PathVariable("id") Long id) {
        return productColorService.deleteProductColor(id);
    }

    @GetMapping("/color/edit/id={id}")
    public Color edit(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/color/saveUpdate")
    public ResponseEntity<Color> saveUpdate(@RequestBody Color color) {
        return productColorService.saveEdit(color);
    }

}
