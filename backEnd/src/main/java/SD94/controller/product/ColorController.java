package SD94.controller.product;

import SD94.entity.ProductColor;
import SD94.repository.ProductColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ColorController {
    @Autowired
    ProductColorRepository repository;

    @GetMapping("/color/list")
    public ResponseEntity<List<ProductColor>> getColor() {
        List<ProductColor> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }
}
