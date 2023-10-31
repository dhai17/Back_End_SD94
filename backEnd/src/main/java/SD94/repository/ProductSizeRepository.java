package SD94.repository;

import SD94.entity.product.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductSizeRepository extends JpaRepository<Size, Long> {
    @Query(value = "SELECT * FROM product_size WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<Size> findAll();

    @Query(value = "select * from product_size where id = ? and is_deleted = false", nativeQuery = true)
    Size findByID(Long id);

    @Query(value = "SELECT * FROM product_size WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<Size> findByAll(String input);

    @Query(value = "SELECT * FROM product_size WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<Size> findByDate(LocalDate ngayTao);

    @Query(value = "select * from product_size where name = ?", nativeQuery = true)
    Optional<Size> findByName(String name);

    @Query(value = "SELECT pc.shoe_size AS size_name FROM product_details pd JOIN product_size pc ON pd.id_size = pc.id WHERE pd.id_product = ? GROUP BY pd.id_size\n", nativeQuery = true)
    List<String> getSize(long id_product);
}
