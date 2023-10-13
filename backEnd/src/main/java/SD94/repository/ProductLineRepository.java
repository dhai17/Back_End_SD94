package SD94.repository;

import SD94.entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, Long> {
    @Query(value = "SELECT * FROM product_line WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<ProductLine> findAll();

    @Query(value = "select * from product_line where name = ? and is_deleted = false", nativeQuery = true)
    ProductLine findByID(Long id);

    @Query(value = "SELECT * FROM product_line WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<ProductLine> findByAll(String input);

    @Query(value = "SELECT * FROM product_line WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<ProductLine> findByDate(LocalDate ngayTao);

    @Query(value = "select * from product_line where name = ?", nativeQuery = true)
    Optional<ProductLine> findByName(String name);
}
