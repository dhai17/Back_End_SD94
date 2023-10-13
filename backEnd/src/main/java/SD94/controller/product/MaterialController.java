package SD94.controller.product;

import SD94.entity.ProductMaterial;
import SD94.repository.ProductMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class MaterialController {

    @Autowired
    ProductMaterialRepository repository;

    @GetMapping("/material/list")
    public ResponseEntity<List<ProductMaterial>> getMaterial() {
        List<ProductMaterial> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

}
