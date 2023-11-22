package SD94.controller.admin.thongKe;

import SD94.repository.thongKe.ThongKeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thongKe")
public class ThongKeController {
    @Autowired
    private ThongKeRepository thongKeRepository;


    @GetMapping("/tongDoanhSo")
    public ResponseEntity<?> thongKeAll() {
        return ResponseEntity.ok().body(thongKeRepository.getThongKe());
    }

}
