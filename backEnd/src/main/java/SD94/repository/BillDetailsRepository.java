package SD94.repository;


import SD94.entity.hoaDon.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailsRepository extends JpaRepository<HoaDonChiTiet, Long> {
    @Query(value = "SELECT * FROM detailed_invoice WHERE is_deleted = false  ORDER BY id DESC", nativeQuery = true)
    List<HoaDonChiTiet> findAllDetailedInvoice();
    @Query(value = "select * from detailed_invoice where id_bill = ? and is_deleted = false", nativeQuery = true)
    List<HoaDonChiTiet> findByIDBill(Long id);
}
