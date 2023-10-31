package SD94.controller.admin.product;

import SD94.entity.sanPham.NhaSanXuat;
import SD94.repository.ProducerRepository;
import SD94.service.service.NhaSanXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProducerController {

    @Autowired
    ProducerRepository repository;

    @Autowired
    NhaSanXuatService nhaSanXuatService;

    @GetMapping("/producer/list")
    public ResponseEntity<List<NhaSanXuat>> getProducer() {
        List<NhaSanXuat> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/producer/saveCreate")
    public ResponseEntity<NhaSanXuat> saveCreate(@RequestBody NhaSanXuat nhaSanXuat) {
        return nhaSanXuatService.saveCreate(nhaSanXuat);
    }

    @PutMapping("/producer/delete/{id}")
    public ResponseEntity<List<NhaSanXuat>> delete(@PathVariable("id") Long id) {
        return nhaSanXuatService.deleteProducer(id);
    }

    @GetMapping("/producer/edit={id}")
    public NhaSanXuat edit(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/producer/saveUpdate")
    public ResponseEntity<NhaSanXuat> saveUpdate(@RequestBody NhaSanXuat nhaSanXuat) {
        return nhaSanXuatService.saveEdit(nhaSanXuat);
    }

}
