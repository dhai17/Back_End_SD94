package SD94.service.impl;


import SD94.entity.bill.Bill;
import SD94.repository.PurchaseBillRepository;
import SD94.service.service.PurchaseBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseBillServiceImpl implements PurchaseBillService {
    @Autowired
    PurchaseBillRepository purchaseBillRepository;

    @Override
    public List<Bill> findAllBill1() {
        List<Bill> bills = purchaseBillRepository.findAllBill1();
        return bills;
    }

    @Override
    public List<Bill> findAllBill2() {
        List<Bill> bills = purchaseBillRepository.findAllBill2();
        return bills;
    }

    @Override
    public List<Bill> findAllBill3() {
        List<Bill> bills = purchaseBillRepository.findAllBill3();
        return bills;
    }
    @Override
    public List<Bill> findAllBill4() {
        List<Bill> bills = purchaseBillRepository.findAllBill4();
        return bills;
    }

    @Override
    public List<Bill> findAllBill5() {
        List<Bill> bills = purchaseBillRepository.findAllBill5();
        return bills;
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
    public List<Bill> searchAllBill1(String search) {
        List<Bill> billList = purchaseBillRepository.findBillByAll1(search);
        return billList;
    }

    @Override
    public List<Bill> searchDateBill1(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Bill> billList = purchaseBillRepository.findBillByDate1(search);
        return billList;
    }

    @Override
    public List<Bill> searchAllBill2(String search) {
        List<Bill> billList = purchaseBillRepository.findBillByAll2(search);
        return billList;
    }

    @Override
    public List<Bill> searchDateBill2(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Bill> billList = purchaseBillRepository.findBillByDate2(search);
        return billList;
    }

    @Override
    public List<Bill> searchAllBill3(String search) {
        List<Bill> billList = purchaseBillRepository.findBillByAll3(search);
        return billList;
    }

    @Override
    public List<Bill> searchDateBill3(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Bill> billList = purchaseBillRepository.findBillByDate3(search);
        return billList;
    }

    @Override
    public List<Bill> searchAllBill4(String search) {
        List<Bill> billList = purchaseBillRepository.findBillByAll4(search);
        return billList;
    }

    @Override
    public List<Bill> searchDateBill4(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Bill> billList = purchaseBillRepository.findBillByDate4(search);
        return billList;
    }

    @Override
    public List<Bill> searchAllBill5(String search) {
        List<Bill> billList = purchaseBillRepository.findBillByAll5(search);
        return billList;
    }

    @Override
    public List<Bill> searchDateBill5(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Bill> billList = purchaseBillRepository.findBillByDate5(search);
        return billList;
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Boolean>> updateStatusAll2() {
        List<Bill> list = findAllBill1();
        for(Bill bill: list){
            Long id = bill.getId();
            purchaseBillRepository.updateStatus(2l, id);
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Boolean>> updateStatusAll3() {
        List<Bill> list = findAllBill2();
        for(Bill bill: list){
            Long id = bill.getId();
            purchaseBillRepository.updateStatus(3l, id);
        }
        return null;
    }

}
