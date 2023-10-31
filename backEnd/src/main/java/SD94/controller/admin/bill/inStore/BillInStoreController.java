package SD94.controller.admin.bill.inStore;


import SD94.entity.bill.BillDetails;
import SD94.service.service.DetailedInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillInStoreController {

    @Autowired
    private DetailedInvoiceService detailedInvoiceService;

    @GetMapping("/api/detailedInvoice/pending1/id={id}")
    public List<BillDetails> listBill1(@PathVariable("id") long id) {
        return detailedInvoiceService.findByIDBill(id);
    }
}
