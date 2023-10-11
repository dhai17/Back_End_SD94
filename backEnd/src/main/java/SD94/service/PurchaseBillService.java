package SD94.service;

import SD94.entity.Bill;
import SD94.entity.Discount;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseBillService {
    List<Bill> findAllBill();

//    ResponseEntity<Bill> saveEdit(Bill billUpdate);

//    ResponseEntity<List<Bill>> deleteBill(Long id);
//
//    ResponseEntity<Bill> saveCreate(Bill discountCreate);
//
//    List<Bill> searchAllDiscount(String search);
//
//    List<Bill> searchDateDiscount(String searchDate);
}
