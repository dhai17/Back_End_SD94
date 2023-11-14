package SD94.service.service;

import SD94.dto.GioHangDTO;
import SD94.dto.HoaDonDTO;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.HoaDonChiTiet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BanHangOnlineService {
    ResponseEntity<Long> checkout(GioHangDTO dto);

    ResponseEntity<HoaDon> getHoaDon(long id_hoa_don);

    List<HoaDonChiTiet> getHoaDonChiTiet(long id_hoa_don);

    ResponseEntity<HoaDon> getBill();

    HoaDon addDiscount(HoaDonDTO hoaDonDTO);

    ResponseEntity datHang(HoaDonDTO dto);



}
