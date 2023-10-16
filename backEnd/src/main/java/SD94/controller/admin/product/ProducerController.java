package SD94.controller.admin.product;

import SD94.entity.Producer;
import SD94.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProducerController {

    @Autowired
    ProducerRepository repository;

    @GetMapping("/producer/list")
    public ResponseEntity<List<Producer>> getProducer() {
        List<Producer> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

}
