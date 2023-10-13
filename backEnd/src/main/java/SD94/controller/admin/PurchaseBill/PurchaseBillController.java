package SD94.controller.admin.PurchaseBill;

import SD94.entity.Bill;
import SD94.service.PurchaseBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PurchaseBillController {
    @Autowired
    PurchaseBillService purchaseBillService;
    @GetMapping("/api/purchasebill/list1")
    public List<Bill> listBill1() {
        return purchaseBillService.findAllBill1();
    }

    @GetMapping("/api/purchasebill/list2")
    public List<Bill> listBill2() {
        return purchaseBillService.findAllBill2();
    }

    @GetMapping("/api/purchasebill/list3")
    public List<Bill> listBill3() {
        return purchaseBillService.findAllBill3();
    }

    @GetMapping("/api/purchasebill/list4")
    public List<Bill> listBill4() {
        return purchaseBillService.findAllBill4();
    }

    @GetMapping("/api/purchasebill/list5")
    public List<Bill> listBill5() {
        return purchaseBillService.findAllBill5();
    }

    @PostMapping("/api/bill/pending2")
    public ResponseEntity<Map<String, Boolean>> updateStatus2(@RequestBody Map<String, String> body) {
        String id_bill = body.get("id_bill");
        purchaseBillService.updateStatus2(id_bill);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/bill/pending3")
    public ResponseEntity<Map<String, Boolean>> updateStatus3(@RequestBody Map<String, String> body) {
        String id_bill = body.get("id_bill");
        purchaseBillService.updateStatus3(id_bill);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/bill/pending4")
    public ResponseEntity<Map<String, Boolean>> updateStatus4(@RequestBody Map<String, String> body) {
        String id_bill = body.get("id_bill");
        purchaseBillService.updateStatus4(id_bill);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/bill/pending1")
    public ResponseEntity<Map<String, Boolean>> updateStatus1(@RequestBody Map<String, String> body) {
        String id_bill = body.get("id_bill");
        purchaseBillService.updateStatus1(id_bill);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/bill/refuse")
    public ResponseEntity<Map<String, Boolean>> updateStatus5(@RequestBody Map<String, String> body) {
        String id_bill = body.get("id_bill");
        purchaseBillService.updateStatus5(id_bill);
        return ResponseEntity.ok().build();
    }


    @PutMapping ("/api/bill/pending2/confirm-all2")
    public ResponseEntity<Map<String, Boolean>> updateStatusAll2() {
        purchaseBillService.updateStatusAll2();
        return ResponseEntity.ok().build();
    }

    @PutMapping ("/api/bill/pending3/confirm-all3")
    public ResponseEntity<Map<String, Boolean>> updateStatusAll3() {
        purchaseBillService.updateStatusAll3();
        return ResponseEntity.ok().build();
    }
}
