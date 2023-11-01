package SD94.service.impl;


import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.TrangThai;
import SD94.repository.hoaDon.HoaDonRepository;
import SD94.repository.hoaDon.TrangThaiRepository;
import SD94.service.service.HoaDonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HoaDonDatHangServiceImpl implements HoaDonDatHangService {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    TrangThaiRepository trangThaiRepository;

    @Override
    public List<HoaDon> findHoaDonByTrangThai(long trang_thai_id) {
        List<HoaDon> hoaDonList = hoaDonRepository.findHoaDonByTrangThai(trang_thai_id);
        return hoaDonList;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> capNhatTrangThai(long trang_thai_id, long id_bill) {
        HoaDon hoaDon = hoaDonRepository.findByID(id_bill);
        Optional<TrangThai> optionalTrangThai = trangThaiRepository.findById(trang_thai_id);
        if (optionalTrangThai.isPresent()) {
            TrangThai trangThai = optionalTrangThai.get();
            hoaDon.setTrangThai(trangThai);
            hoaDonRepository.save(hoaDon);
        }
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<Map<String, Boolean>> capNhatTrangThai_TatCa(long trang_thai_id) {
        List<HoaDon> list = hoaDonRepository.findHoaDonByTrangThai(trang_thai_id);
        for (HoaDon hoaDon : list) {
            Optional<TrangThai> optionalTrangThai = trangThaiRepository.findById(trang_thai_id);
            if (optionalTrangThai.isPresent()) {
                TrangThai trangThai = optionalTrangThai.get();
                hoaDon.setTrangThai(trangThai);
                hoaDonRepository.save(hoaDon);
            }
        }
        return ResponseEntity.ok().build();
    }


    @Override
    public List<HoaDon> searchAllBill(long trang_thai_id, String search) {
        List<HoaDon> hoaDonList = hoaDonRepository.findBill(trang_thai_id, search);
        return hoaDonList;
    }

    @Override
    public List<HoaDon> searchDateBill(long trang_thai_id, String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<HoaDon> hoaDonList = hoaDonRepository.findBillByDate(trang_thai_id, search);
        return hoaDonList;
    }
}
