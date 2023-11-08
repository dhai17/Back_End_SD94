package SD94.controller.admin.hoaDon.datHang;

import SD94.dto.ChiTietHoaDonDTO;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.entity.hoaDon.LichSuHoaDon;
import SD94.repository.hoaDon.HoaDonChiTietRepository;
import SD94.repository.hoaDon.HoaDonRepository;
import SD94.repository.hoaDon.LichSuHoaDonRepository;
import SD94.service.service.HoaDonDatHangService;
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
    @Autowired
    HoaDonRepository hoaDonRepository;

    //Trả ra hoá đơn theo ID, danh sách hoá đn, lịch s của hoá ơn đó
    @GetMapping("/choXacNhan/id={id}")
    public ResponseEntity<ChiTietHoaDonDTO> listBill1(@PathVariable("id") long id) {

        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findByIDBill(id);
        List<LichSuHoaDon> lichSuHoaDons = lichSuHoaDonRepository.getTimeLine(id);
        HoaDon hoaDon = hoaDonRepository.findByID(id);
        ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();
        chiTietHoaDonDTO.setHoaDonChiTiets(hoaDonChiTiets);
        chiTietHoaDonDTO.setLichSuHoaDons(lichSuHoaDons);
        chiTietHoaDonDTO.setHoaDon(hoaDon);
        return ResponseEntity.ok(chiTietHoaDonDTO);
    }
}
