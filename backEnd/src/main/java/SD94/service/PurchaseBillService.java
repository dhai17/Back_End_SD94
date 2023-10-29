package SD94.service;

import SD94.entity.Bill;
import SD94.entity.Discount;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Service
public interface PurchaseBillService {
    List<Bill> findAllBill1();
    List<Bill> findAllBill2();
    List<Bill> findAllBill3();
    List<Bill> findAllBill4();
    List<Bill> findAllBill5();

    ResponseEntity<Map<String, Boolean>> updateStatus1(String id_bill);
    ResponseEntity<Map<String, Boolean>> updateStatus2(String id_bill);
    ResponseEntity<Map<String, Boolean>> updateStatus3(String id_bill);
    ResponseEntity<Map<String, Boolean>> updateStatus4(String id_bill);
    ResponseEntity<Map<String, Boolean>> updateStatus5(String id_bill);
    List<Bill> searchAllBill1(String search);

    List<Bill> searchDateBill1(String searchDate);

    List<Bill> searchAllBill2(String search);

    List<Bill> searchDateBill2(String searchDate);

    List<Bill> searchAllBill3(String search);

    List<Bill> searchDateBill3(String searchDate);

    List<Bill> searchAllBill4(String search);

    List<Bill> searchDateBill4(String searchDate);

    List<Bill> searchAllBill5(String search);

    List<Bill> searchDateBill5(String searchDate);

    ResponseEntity<Map<String, Boolean>> updateStatusAll2();
    ResponseEntity<Map<String, Boolean>> updateStatusAll3();

}
