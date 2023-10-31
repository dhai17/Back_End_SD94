package SD94.controller.admin.product;

import SD94.entity.sanPham.LoaiSanPham;
import SD94.repository.ProductLineRepository;
import SD94.service.service.LoaiSanPhamService;
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
    LoaiSanPhamService loaiSanPhamService;

    @GetMapping("/line/list")
    public ResponseEntity<List<LoaiSanPham>> getLine() {
        List<LoaiSanPham> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/line/saveCreate")
    public ResponseEntity<LoaiSanPham> saveCreate(@RequestBody LoaiSanPham loaiSanPham) {
        return loaiSanPhamService.saveCreate(loaiSanPham);
    }

    @PutMapping("/line/delete/{id}")
    public ResponseEntity<List<LoaiSanPham>> delete(@PathVariable("id") Long id) {
        return loaiSanPhamService.deleteProductLine(id);
    }

    @GetMapping("/line/edit={id}")
    public LoaiSanPham edit(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/line/saveUpdate")
    public ResponseEntity<LoaiSanPham> saveUpdate(@RequestBody LoaiSanPham loaiSanPham) {
        return loaiSanPhamService.saveEdit(loaiSanPham);
    }

}
