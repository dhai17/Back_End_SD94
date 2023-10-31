package SD94.controller.admin.product;

import SD94.entity.sanPham.ChatLieu;
import SD94.repository.ProductMaterialRepository;
import SD94.service.service.ChatLieuService;
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
    ChatLieuService productMaterialService;

    @GetMapping("/material/list")
    public ResponseEntity<List<ChatLieu>> getMaterial() {
        List<ChatLieu> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/material/saveCreate")
    public ResponseEntity<ChatLieu> saveCreate(@RequestBody ChatLieu chatLieu) {
        return productMaterialService.saveCreate(chatLieu);
    }

    @PutMapping("/material/delete/{id}")
    public ResponseEntity<List<ChatLieu>> delete(@PathVariable("id") Long id) {
        return productMaterialService.deleteProductMaterial(id);
    }

    @GetMapping("/material/edit={id}")
    public ChatLieu edit(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/material/saveUpdate")
    public ResponseEntity<ChatLieu> saveUpdate(@RequestBody ChatLieu chatLieu) {
        return productMaterialService.saveEdit(chatLieu);
    }

}
