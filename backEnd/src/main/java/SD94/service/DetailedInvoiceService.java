package SD94.service;


import SD94.entity.BillDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailedInvoiceService {
    List<BillDetails> findByIDBill(Long id);
}
