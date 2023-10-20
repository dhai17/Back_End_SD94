package SD94.service;


import SD94.entity.DetailedInvoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailedInvoiceService {
    List<DetailedInvoice> findAllDetailedInvoice();
    List<DetailedInvoice> findByIDBill(Long id);
}
