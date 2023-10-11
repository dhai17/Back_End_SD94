package SD94.repository;

import SD94.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PurchaseBillRepository extends JpaRepository<Bill, Long> {
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<Bill> findAllBill();

    @Query(value = "select * from bill where id = ? and is_deleted = false", nativeQuery = true)
    Bill findByID(Long id);

//    @Query(value = "SELECT * FROM bill WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
//    List<Discount> findDiscountByAll(String input);

//    @Query(value = "SELECT * FROM bill WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
//    List<Discount> findDiscountByDate(LocalDate ngayTao);
//
//    @Query(value = "select * from discount where name = ?", nativeQuery = true)
//    Optional<Bill> findByName(String name);
}
