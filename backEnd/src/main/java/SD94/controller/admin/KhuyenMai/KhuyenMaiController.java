package SD94.controller.admin.KhuyenMai;

import SD94.entity.khuyenMai.KhuyenMai;
import SD94.repository.KhuyenMaiRepository;
import SD94.service.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class KhuyenMaiController {
    @Autowired
    KhuyenMaiService khuyenMaiService;

    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;

    @GetMapping("/api/khuyenMai/list")
    public List<KhuyenMai> listkhuyenMai() {
        return khuyenMaiService.findAllkhuyenMai();
    }

    @GetMapping("/api/khuyenMai/edit/khuyeMaiID/{id}")
    public KhuyenMai editkhuyenMai(@PathVariable("id") Long id) {
        return khuyenMaiRepository.findByID(id);
    }

    @PutMapping("/api/khuyenMai/saveUpdate")
    public ResponseEntity<KhuyenMai> saveUpdate(@RequestBody KhuyenMai khuyenMaiUpdate) {
        return khuyenMaiService.saveEdit(khuyenMaiUpdate);
    }

    @PostMapping("/api/khuyenMai/deletekhuyeMai")
    public ResponseEntity<List<KhuyenMai>> deletekhuyenMai(@RequestBody KhuyenMai khuyenMai) {
        long id = khuyenMai.getId();
        return khuyenMaiService.deletekhuyenMai(id);
    }

    @PostMapping("/api/khuyenMai/saveCreate")
    public ResponseEntity<KhuyenMai> saveCreate(@RequestBody KhuyenMai khuyenMaiCreate) {
        System.out.println(khuyenMaiCreate);
        return khuyenMaiService.saveCreate(khuyenMaiCreate);
    }

    @RequestMapping("/api/khuyenMai/search={search}")
    public List<KhuyenMai> searchAllkhuyenMai(@PathVariable("search") String search) {
        return khuyenMaiService.searchAllkhuyenMai(search);
    }

    @RequestMapping("/api/khuyenMai/searchDate={searchDate}")
    public List<KhuyenMai> searchDatekhuyenMai(@PathVariable("searchDate") String searchDate) {
        return khuyenMaiService.searchDatekhuyenMai(searchDate);
    }
}
