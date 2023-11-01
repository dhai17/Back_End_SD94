package SD94.controller.admin.hoaDon.datHang;


import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.service.service.HoaDonTaiQuayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HoaDonChiTietController {

    @Autowired
    private HoaDonTaiQuayService hoaDonTaiQuayService;

    @GetMapping("/api/detailedInvoice/pending1/id={id}")
    public List<HoaDonChiTiet> listBill1(@PathVariable("id") long id) {
        return hoaDonTaiQuayService.findByIDBill(id);
    }
}
