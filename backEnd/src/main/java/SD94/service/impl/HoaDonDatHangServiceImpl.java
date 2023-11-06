package SD94.service.impl;


import SD94.dto.HoaDonDTO;
import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.LichSuHoaDon;
import SD94.entity.hoaDon.TrangThai;
import SD94.entity.nhanVien.NhanVien;
import SD94.repository.hoaDon.HoaDonRepository;
import SD94.repository.hoaDon.LichSuHoaDonRepository;
import SD94.repository.hoaDon.TrangThaiRepository;
import SD94.repository.nhanVien.NhanVienRepository;
import SD94.service.service.HoaDonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.util.*;

@Service
public class HoaDonDatHangServiceImpl implements HoaDonDatHangService {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    TrangThaiRepository trangThaiRepository;

    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

    @Autowired
    NhanVienRepository nhanVienRepository;

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
    public ResponseEntity<Map<String, Boolean>> capNhatTrangThai_TatCa(long trang_thai_id, long trang_thai_id_sau) {
        List<HoaDon> list = hoaDonRepository.findHoaDonByTrangThai(trang_thai_id);
        for (HoaDon hoaDon : list) {
            Optional<TrangThai> optionalTrangThai = trangThaiRepository.findById(trang_thai_id_sau);
            if (optionalTrangThai.isPresent()) {
                TrangThai trangThai = optionalTrangThai.get();
                hoaDon.setTrangThai(trangThai);
                createTimeLine("Xác nhận đơn", trang_thai_id_sau, hoaDon.getId(), 1);
                hoaDonRepository.save(hoaDon);
            }
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public List<HoaDon> capNhatTrangThai_DaChon(HoaDonDTO hoaDonDTO) {
        for (Long id_hoaDon : hoaDonDTO.getId_hoaDon()) {
            HoaDon hoaDon = hoaDonRepository.findByID(id_hoaDon);
            Optional<TrangThai> optionalTrangThai = trangThaiRepository.findById(2L);
            if (optionalTrangThai.isPresent()) {
                TrangThai trangThai = optionalTrangThai.get();
                hoaDon.setTrangThai(trangThai);
                createTimeLine("Xác nhận đơn", 2L, id_hoaDon, 1);
                hoaDonRepository.save(hoaDon);
            }
        }
        List<HoaDon> hoaDonList = hoaDonRepository.findHoaDonByTrangThai(1L);
        return hoaDonList;
    }

    @Override
    public List<HoaDon> capNhatTrangThaiHuy_DaChon(HoaDonDTO hoaDonDTO) {
        for (Long id_hoaDon : hoaDonDTO.getId_hoaDon()) {
            HoaDon hoaDon = hoaDonRepository.findByID(id_hoaDon);
            Optional<TrangThai> optionalTrangThai = trangThaiRepository.findById(5L);
            if (optionalTrangThai.isPresent()) {
                TrangThai trangThai = optionalTrangThai.get();
                hoaDon.setTrangThai(trangThai);
                createTimeLine("Huỷ đơn", 5L, id_hoaDon, 1);
                hoaDonRepository.save(hoaDon);
            }
        }
        List<HoaDon> hoaDonList = hoaDonRepository.findHoaDonByTrangThai(1L);
        return hoaDonList;
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

    @Override
    public ResponseEntity createTimeLine(String thaoTac, long trangThai_id, long hoaDon_id, long nhanVien_id) {
        HoaDon hoaDon = hoaDonRepository.findByID(hoaDon_id);
        TrangThai trangThai = trangThaiRepository.findByID(trangThai_id);
        NhanVien nhanVien = nhanVienRepository.findByID(nhanVien_id);
        LichSuHoaDon lichSuHoaDon = new LichSuHoaDon();
        lichSuHoaDon.setThaoTac(thaoTac);
        lichSuHoaDon.setHoaDon(hoaDon);
        lichSuHoaDon.setTrangThai(trangThai);
        lichSuHoaDon.setNhanVien(nhanVien);
        lichSuHoaDon.setCreatedDate(new Date());
        lichSuHoaDon.setCreatedby(nhanVien.getHoTen());
        lichSuHoaDonRepository.save(lichSuHoaDon);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
