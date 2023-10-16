package SD94.repository;

import SD94.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
    @Query(value = "SELECT * FROM product_size WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<ProductSize> findAll();

    @Query(value = "select * from product_size where shoe_size = ? and is_deleted = false", nativeQuery = true)
    ProductSize findByID(Long id);

    @Query(value = "SELECT * FROM product_size WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<ProductSize> findByAll(String input);

    @Query(value = "SELECT * FROM product_size WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<ProductSize> findByDate(LocalDate ngayTao);

    @Query(value = "select * from product_size where name = ?", nativeQuery = true)
    Optional<ProductSize> findByName(String name);

    @Query(value = "SELECT pc.shoe_size AS size_name FROM product_details pd JOIN product_size pc ON pd.id_size = pc.id WHERE pd.id_product = ? GROUP BY pd.id_size\n", nativeQuery = true)
    List<String> getSize(long id_product);
}
