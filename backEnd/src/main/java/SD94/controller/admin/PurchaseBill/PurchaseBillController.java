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

    @RequestMapping("/api/bill/pending1/search={search}")
    public List<Bill> searchAllBill1(@PathVariable("search") String search) {
        System.out.println(search);
        return purchaseBillService.searchAllBill1(search);

    }

    @RequestMapping("/api/bill/pending1/searchDate={searchDate}")
    public List<Bill> searchDateBill1(@PathVariable("searchDate") String searchDate) {
        return purchaseBillService.searchDateBill1(searchDate);
    }
    @RequestMapping("/api/bill/pending2/search={search}")
    public List<Bill> searchAllBill2(@PathVariable("search") String search) {
        System.out.println(search);
        return purchaseBillService.searchAllBill2(search);

    }

    @RequestMapping("/api/bill/pending2/searchDate={searchDate}")
    public List<Bill> searchDateBill2(@PathVariable("searchDate") String searchDate) {
        return purchaseBillService.searchDateBill2(searchDate);
    }
    @RequestMapping("/api/bill/pending3/search={search}")
    public List<Bill> searchAllBill3(@PathVariable("search") String search) {
        System.out.println(search);
        return purchaseBillService.searchAllBill3(search);

    }

    @RequestMapping("/api/bill/pending3/searchDate={searchDate}")
    public List<Bill> searchDateBill3(@PathVariable("searchDate") String searchDate) {
        return purchaseBillService.searchDateBill3(searchDate);
    }
    @RequestMapping("/api/bill/pending4/search={search}")
    public List<Bill> searchAllBill4(@PathVariable("search") String search) {
        System.out.println(search);
        return purchaseBillService.searchAllBill4(search);

    }

    @RequestMapping("/api/bill/pending4/searchDate={searchDate}")
    public List<Bill> searchDateBill4(@PathVariable("searchDate") String searchDate) {
        return purchaseBillService.searchDateBill4(searchDate);
    }

    @RequestMapping("/api/bill/pending5/search={search}")
    public List<Bill> searchAllBill5(@PathVariable("search") String search) {
        System.out.println(search);
        return purchaseBillService.searchAllBill5(search);

    }

    @RequestMapping("/api/bill/pending5/searchDate={searchDate}")
    public List<Bill> searchDateBill5(@PathVariable("searchDate") String searchDate) {
        return purchaseBillService.searchDateBill5(searchDate);
    }
}
