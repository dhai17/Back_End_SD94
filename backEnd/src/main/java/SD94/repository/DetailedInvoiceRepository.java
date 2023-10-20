package SD94.repository;


import SD94.entity.DetailedInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailedInvoiceRepository extends JpaRepository<DetailedInvoice, Long> {
    @Query(value = "SELECT * FROM detailed_invoice WHERE is_deleted = false  ORDER BY id DESC", nativeQuery = true)
    List<DetailedInvoice> findAllDetailedInvoice();
    @Query(value = "select * from detailed_invoice " +
            "where id_bill = ? and is_deleted = false", nativeQuery = true)
    List<DetailedInvoice> findByIDBill(Long id);
}
