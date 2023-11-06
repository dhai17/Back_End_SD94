package SD94.controller.admin.hoaDon.datHang;

import SD94.dto.ChiTietHoaDonDTO;
import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.entity.hoaDon.LichSuHoaDon;
import SD94.repository.hoaDon.HoaDonChiTietRepository;
import SD94.repository.hoaDon.LichSuHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/hoaDon/chiTietHoaDon")
public class ChiTietHoaDonController {
    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

    @GetMapping("/choXacNhan/id={id}")
    public ResponseEntity<ChiTietHoaDonDTO> listBill1(@PathVariable("id") long id) {

        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findByIDBill(id);
        List<LichSuHoaDon> lichSuHoaDons = lichSuHoaDonRepository.getTimeLine(id);
        ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();
        chiTietHoaDonDTO.setHoaDonChiTiets(hoaDonChiTiets);
        chiTietHoaDonDTO.setLichSuHoaDons(lichSuHoaDons);
        return ResponseEntity.ok(chiTietHoaDonDTO);
    }
}
