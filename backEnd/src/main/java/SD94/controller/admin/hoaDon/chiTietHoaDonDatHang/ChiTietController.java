package SD94.controller.admin.hoaDon.chiTietHoaDonDatHang;

import SD94.entity.hoaDon.LichSuHoaDon;
import SD94.repository.hoaDon.LichSuHoaDonRepository;
import SD94.service.service.HoaDonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    @RequestMapping("/admin/hoaDon/ChiTietHoaDon")
public class ChiTietController {
    @Autowired
    HoaDonDatHangService hoaDonDatHangService;

    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

    @GetMapping("/getTimeLine/ChoXacNhan")
    public List<LichSuHoaDon> getTimeLineChoXacNhan(@RequestParam long id_hoaDon){
        List<LichSuHoaDon> lichSuHoaDon = lichSuHoaDonRepository.getTimeLine( id_hoaDon);
        return lichSuHoaDon;
    }
}
