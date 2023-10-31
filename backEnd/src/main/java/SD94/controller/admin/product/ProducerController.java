package SD94.controller.admin.product;

import SD94.entity.product.Manufacturer;
import SD94.repository.ProducerRepository;
import SD94.service.service.ProducerService;
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
    ProducerService producerService;

    @GetMapping("/producer/list")
    public ResponseEntity<List<Manufacturer>> getProducer() {
        List<Manufacturer> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/producer/saveCreate")
    public ResponseEntity<Manufacturer> saveCreate(@RequestBody Manufacturer manufacturer) {
        return producerService.saveCreate(manufacturer);
    }

    @PutMapping("/producer/delete/{id}")
    public ResponseEntity<List<Manufacturer>> delete(@PathVariable("id") Long id) {
        return producerService.deleteProducer(id);
    }

    @GetMapping("/producer/edit={id}")
    public Manufacturer edit(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/producer/saveUpdate")
    public ResponseEntity<Manufacturer> saveUpdate(@RequestBody Manufacturer manufacturer) {
        return producerService.saveEdit(manufacturer);
    }

}
