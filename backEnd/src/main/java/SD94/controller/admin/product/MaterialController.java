package SD94.controller.admin.product;

import SD94.entity.product.Material;
import SD94.repository.ProductMaterialRepository;
import SD94.service.service.ProductMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class MaterialController {

    @Autowired
    ProductMaterialRepository repository;

    @Autowired
    ProductMaterialService productMaterialService;

    @GetMapping("/material/list")
    public ResponseEntity<List<Material>> getMaterial() {
        List<Material> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/material/saveCreate")
    public ResponseEntity<Material> saveCreate(@RequestBody Material material) {
        return productMaterialService.saveCreate(material);
    }

    @PutMapping("/material/delete/{id}")
    public ResponseEntity<List<Material>> delete(@PathVariable("id") Long id) {
        return productMaterialService.deleteProductMaterial(id);
    }

    @GetMapping("/material/edit={id}")
    public Material edit(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/material/saveUpdate")
    public ResponseEntity<Material> saveUpdate(@RequestBody Material material) {
        return productMaterialService.saveEdit(material);
    }

}
