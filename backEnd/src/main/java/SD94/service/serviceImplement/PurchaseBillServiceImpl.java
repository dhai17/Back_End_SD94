package SD94.service.serviceImplement;


import SD94.entity.Bill;
import SD94.repository.PurchaseBillRepository;
import SD94.service.PurchaseBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
