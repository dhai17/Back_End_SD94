package SD94.service.serviceImplement;



import SD94.entity.DetailedInvoice;
import SD94.repository.DetailedInvoiceRepository;
import SD94.service.DetailedInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailedInvoiceServiceImpl implements DetailedInvoiceService {

    @Autowired
    DetailedInvoiceRepository detailedInvoiceRepository;

    @Override
    public List<DetailedInvoice> findAllDetailedInvoice() {
        List<DetailedInvoice> billDetail = detailedInvoiceRepository.findAllDetailedInvoice();
        return billDetail;
    }

    @Override
    public List<DetailedInvoice> findByIDBill(Long id) {
        List<DetailedInvoice> billDetail = detailedInvoiceRepository.findByIDBill(id);
        return billDetail;
    }


}
