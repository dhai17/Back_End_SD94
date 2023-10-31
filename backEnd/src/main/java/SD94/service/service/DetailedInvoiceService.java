package SD94.service.service;


import SD94.entity.bill.BillDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailedInvoiceService {
    List<BillDetails> findAllDetailedInvoice();
    List<BillDetails> findByIDBill(Long id);
}
