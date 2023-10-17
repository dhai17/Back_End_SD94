package SD94.repository;

import SD94.entity.DetailedInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailsRepository extends JpaRepository<DetailedInvoice, Long> {
    @Query(value = "select * from detailed_invoice where id_bill = ?", nativeQuery = true)
    List<DetailedInvoice> findByIdBill(long id);
}
