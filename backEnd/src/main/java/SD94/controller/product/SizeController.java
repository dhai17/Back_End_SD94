package SD94.controller.product;

import SD94.entity.ProductSize;
import SD94.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class SizeController {

    @Autowired
    ProductSizeRepository repository;

    @GetMapping("/size/list")
    public ResponseEntity<List<ProductSize>> getSize() {
        List<ProductSize> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }
}
