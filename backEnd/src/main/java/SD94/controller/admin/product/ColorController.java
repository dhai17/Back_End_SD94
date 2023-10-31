package SD94.controller.admin.product;

import SD94.entity.sanPham.MauSac;
import SD94.repository.ProductColorRepository;
import SD94.service.service.MauSacService;
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
    MauSacService mauSacService;

    @GetMapping("/color/list")
    public ResponseEntity<List<MauSac>> getColor() {
        List<MauSac> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/color/saveCreate")
    public ResponseEntity<MauSac> saveCreate(@RequestBody MauSac mauSac) {
        return mauSacService.saveCreate(mauSac);
    }

    @PutMapping("/color/delete/{id}")
    public ResponseEntity<List<MauSac>> delete(@PathVariable("id") Long id) {
        return mauSacService.deleteProductColor(id);
    }

    @GetMapping("/color/edit/id={id}")
    public MauSac edit(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/color/saveUpdate")
    public ResponseEntity<MauSac> saveUpdate(@RequestBody MauSac mauSac) {
        return mauSacService.saveEdit(mauSac);
    }

}
