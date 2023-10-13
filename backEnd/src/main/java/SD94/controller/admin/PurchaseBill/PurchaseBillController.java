package SD94.controller.admin.PurchaseBill;

import SD94.entity.Bill;
import SD94.service.PurchaseBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PurchaseBillController {
    @Autowired
    PurchaseBillService purchaseBillService;
    @GetMapping("/api/purchasebill/list")
    public List<Bill> listBill() {
        return purchaseBillService.findAllBill();
    }

    @PostMapping("/api/bill/pending")
    public @ResponseBody Map<String, Boolean> updateStatus(@RequestBody long id_bill) {

        Map<String, Boolean> respone = new  HashMap<>();

        respone.put("done", true);
        return respone;

    }

}
