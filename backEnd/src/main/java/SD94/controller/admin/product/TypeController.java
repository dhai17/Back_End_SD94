package SD94.controller.admin.product;

import SD94.entity.product.TypeProduct;
import SD94.repository.ProductLineRepository;
import SD94.service.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class TypeController {

    @Autowired
    ProductLineRepository repository;

    @Autowired
    ProductLineService productLineService;

    @GetMapping("/line/list")
    public ResponseEntity<List<TypeProduct>> getLine() {
        List<TypeProduct> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/line/saveCreate")
    public ResponseEntity<TypeProduct> saveCreate(@RequestBody TypeProduct typeProduct) {
        return productLineService.saveCreate(typeProduct);
    }

    @PutMapping("/line/delete/{id}")
    public ResponseEntity<List<TypeProduct>> delete(@PathVariable("id") Long id) {
        return productLineService.deleteProductLine(id);
    }

    @GetMapping("/line/edit={id}")
    public TypeProduct edit(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/line/saveUpdate")
    public ResponseEntity<TypeProduct> saveUpdate(@RequestBody TypeProduct typeProduct) {
        return productLineService.saveEdit(typeProduct);
    }

}
