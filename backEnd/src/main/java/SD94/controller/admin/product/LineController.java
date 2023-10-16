package SD94.controller.admin.product;

import SD94.entity.ProductLine;
import SD94.repository.ProductLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class LineController {

    @Autowired
    ProductLineRepository repository;

    @GetMapping("/line/list")
    public ResponseEntity<List<ProductLine>> getLine() {
        List<ProductLine> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

}
