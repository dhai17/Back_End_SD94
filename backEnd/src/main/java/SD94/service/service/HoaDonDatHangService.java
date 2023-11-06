package SD94.service.service;

import SD94.dto.HoaDonDTO;
import SD94.entity.hoaDon.HoaDon;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface HoaDonDatHangService {
    List<HoaDon> findHoaDonByTrangThai(long trang_thai_id);

    ResponseEntity<Map<String, Boolean>> capNhatTrangThai(long trang_thai_id, long id_bill);

    ResponseEntity<Map<String, Boolean>> capNhatTrangThai_TatCa(long trang_thai_id, long trang_thai_id_sau);
    List<HoaDon> capNhatTrangThai_DaChon(HoaDonDTO hoaDonDTO);
    List<HoaDon> capNhatTrangThaiHuy_DaChon(HoaDonDTO hoaDonDTO);

    List<HoaDon> searchAllBill(long trang_thai_id, String search);

    List<HoaDon> searchDateBill(long trang_thai_id, String searchDate);

    ResponseEntity createTimeLine(String thaoTac, long trangThai_id, long hoaDon_id, long nhanVien_id);
}
