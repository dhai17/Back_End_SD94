package SD94.service.impl;


import SD94.entity.hoaDon.HoaDon;
import SD94.repository.PurchaseBillRepository;
import SD94.service.service.HoaDonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class HoaDonDatHangServiceImpl implements HoaDonDatHangService {
    @Autowired
    PurchaseBillRepository purchaseBillRepository;

    @Override
    public List<HoaDon> findAllBill1() {
        List<HoaDon> hoaDons = purchaseBillRepository.findAllBill1();
        return hoaDons;
    }

    @Override
    public List<HoaDon> findAllBill2() {
        List<HoaDon> hoaDons = purchaseBillRepository.findAllBill2();
        return hoaDons;
    }

    @Override
    public List<HoaDon> findAllBill3() {
        List<HoaDon> hoaDons = purchaseBillRepository.findAllBill3();
        return hoaDons;
    }
    @Override
    public List<HoaDon> findAllBill4() {
        List<HoaDon> hoaDons = purchaseBillRepository.findAllBill4();
        return hoaDons;
    }

    @Override
    public List<HoaDon> findAllBill5() {
        List<HoaDon> hoaDons = purchaseBillRepository.findAllBill5();
        return hoaDons;
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Boolean>> updateStatus1(String id_bill) {
        Long id = Long.valueOf(id_bill);
        purchaseBillRepository.updateStatus(1L, id);
        return ResponseEntity.ok().build();
    }
    @Transactional
    @Override
    public ResponseEntity<Map<String, Boolean>> updateStatus2(String id_bill) {
        Long id = Long.valueOf(id_bill);
        purchaseBillRepository.updateStatus(2L, id);
        return ResponseEntity.ok().build();
    }
    @Transactional
    @Override
    public ResponseEntity<Map<String, Boolean>> updateStatus3(String id_bill) {
        Long id = Long.valueOf(id_bill);
        purchaseBillRepository.updateStatus(3L, id);
        return ResponseEntity.ok().build();
    }
    @Transactional
    @Override
    public ResponseEntity<Map<String, Boolean>> updateStatus4(String id_bill) {
        Long id = Long.valueOf(id_bill);
        purchaseBillRepository.updateStatus(4L, id);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Boolean>> updateStatus5(String id_bill) {
        Long id = Long.valueOf(id_bill);
        purchaseBillRepository.updateStatus(5L, id);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<HoaDon> searchAllBill1(String search) {
        List<HoaDon> hoaDonList = purchaseBillRepository.findBillByAll1(search);
        return hoaDonList;
    }

    @Override
    public List<HoaDon> searchDateBill1(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<HoaDon> hoaDonList = purchaseBillRepository.findBillByDate1(search);
        return hoaDonList;
    }

    @Override
    public List<HoaDon> searchAllBill2(String search) {
        List<HoaDon> hoaDonList = purchaseBillRepository.findBillByAll2(search);
        return hoaDonList;
    }

    @Override
    public List<HoaDon> searchDateBill2(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<HoaDon> hoaDonList = purchaseBillRepository.findBillByDate2(search);
        return hoaDonList;
    }

    @Override
    public List<HoaDon> searchAllBill3(String search) {
        List<HoaDon> hoaDonList = purchaseBillRepository.findBillByAll3(search);
        return hoaDonList;
    }

    @Override
    public List<HoaDon> searchDateBill3(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<HoaDon> hoaDonList = purchaseBillRepository.findBillByDate3(search);
        return hoaDonList;
    }

    @Override
    public List<HoaDon> searchAllBill4(String search) {
        List<HoaDon> hoaDonList = purchaseBillRepository.findBillByAll4(search);
        return hoaDonList;
    }

    @Override
    public List<HoaDon> searchDateBill4(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<HoaDon> hoaDonList = purchaseBillRepository.findBillByDate4(search);
        return hoaDonList;
    }

    @Override
    public List<HoaDon> searchAllBill5(String search) {
        List<HoaDon> hoaDonList = purchaseBillRepository.findBillByAll5(search);
        return hoaDonList;
    }

    @Override
    public List<HoaDon> searchDateBill5(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<HoaDon> hoaDonList = purchaseBillRepository.findBillByDate5(search);
        return hoaDonList;
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Boolean>> updateStatusAll2() {
        List<HoaDon> list = findAllBill1();
        for(HoaDon hoaDon : list){
            Long id = hoaDon.getId();
            purchaseBillRepository.updateStatus(2l, id);
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Boolean>> updateStatusAll3() {
        List<HoaDon> list = findAllBill2();
        for(HoaDon hoaDon : list){
            Long id = hoaDon.getId();
            purchaseBillRepository.updateStatus(3l, id);
        }
        return null;
    }

}
