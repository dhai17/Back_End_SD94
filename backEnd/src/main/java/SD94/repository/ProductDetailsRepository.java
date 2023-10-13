package SD94.repository;

import SD94.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
    @Query(value = "SELECT * FROM product_details WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<ProductDetails> findAll();

    @Query(value = "select * from product_details where id = ? and is_deleted = false", nativeQuery = true)
    ProductDetails findByID(Long id);

    @Query(value = "SELECT * FROM product_details WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<ProductDetails> findByAll(String input);

    @Query(value = "SELECT * FROM product_details WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<ProductDetails> findByDate(LocalDate ngayTao);

    @Query(value = "select * from product_details where name = ?", nativeQuery = true)
    Optional<ProductDetails> findByName(String name);

    @Query(value = "select * from product_details where id_product = ?", nativeQuery = true)
    List<ProductDetails> findByProductID(Long id);

    @Query(value = "select * from product_details where id_product = ? and id_color = ? and id_size = ?", nativeQuery = true)
    ProductDetails findByColorAndSize(long id_product, long id_color, long id_size);
}
