package SD94.controller.admin.product;

import SD94.entity.*;
import SD94.entity.sanPham.KichCo;

import SD94.repository.sanPham.KichCoRepository;
import SD94.service.service.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class KichCoController {

    @Autowired
    KichCoRepository repository;

    @Autowired
    KichCoService kichCoService;

    @GetMapping("/KichCo/list")
    public ResponseEntity<List<KichCo>> getKichCo() {
        List<KichCo> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/KichCo/saveCreate")
    public ResponseEntity<KichCo> saveCreate(@RequestBody KichCo KichCo) {
        return kichCoService.saveCreate(KichCo);
    }

    @PutMapping("/KichCo/delete/{id}")
    public ResponseEntity<List<KichCo>> delete(@PathVariable("id") Long id) {
        return kichCoService.deleteProductSize(id);
    }

    @GetMapping("/KichCo/edit={id}")
    public KichCo edit(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/KichCo/saveUpdate")
    public ResponseEntity<KichCo> saveUpdate(@RequestBody KichCo KichCo) {
        return kichCoService.saveEdit(KichCo);
    }
}
