package SD94.repository;

import SD94.entity.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    @Query(value = "SELECT * FROM discount WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<Discount> findAllDiscount();

    @Query(value = "select * from discount where id = ? and is_deleted = false", nativeQuery = true)
    Discount findByID(Long id);

    @Query(value = "SELECT * FROM discount WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<Discount> findDiscountByAll(String input);

    @Query(value = "SELECT * FROM discount WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<Discount> findDiscountByDate(LocalDate ngayTao);

    @Query(value = "select * from discount where name = ?", nativeQuery = true)
    Optional<Discount> findByName(String name);

    @Modifying
    @Query(value = "update discount set status = ?1 where id = ?2", nativeQuery = true)
    void updateStatusDiscount(int status, long id);
}
