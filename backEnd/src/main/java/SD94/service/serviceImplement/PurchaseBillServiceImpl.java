package SD94.service.serviceImplement;


import SD94.controller.Message.Message;
import SD94.entity.Bill;
import SD94.repository.PurchaseBillRepository;
import SD94.service.PurchaseBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseBillServiceImpl implements PurchaseBillService {
    @Autowired
    PurchaseBillRepository purchaseBillRepository;

    @Override
    public List<Bill> findAllBill() {
        List<Bill> bills = purchaseBillRepository.findAllBill();
        System.out.println("sadk");
        return bills;
    }

    @Override
    public void updateStatus(long id_status, long id_bill) {
        purchaseBillRepository.updateStatus(id_status, id_bill);
    }

    //Doi trang thai hoa don
//    @Override
//    public ResponseEntity<Bill> saveEdit(Bill billUpdate) {
//        try {
//            Optional<Bill> optionalBill = Optional.ofNullable(purchaseBillRepository.findByID(billUpdate.getId()));
//            if (optionalBill.isPresent()) {
//                Bill bill = optionalBill.get();
//                bill.setStatus(billUpdate.getStatus());
//                purchaseBillRepository.save(bill);
//                return ResponseEntity.ok(bill);
//
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//
//        } catch (Exception e) {
//            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
//        }
//    }
}
