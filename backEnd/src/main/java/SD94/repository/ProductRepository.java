package SD94.repository;

import SD94.entity.Product;
import SD94.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<Product> findAll();

    @Query(value = "select * from product where id = ? and is_deleted = false", nativeQuery = true)
    Product findByID(Long id);

    @Query(value = "SELECT * FROM product WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<Product> findByAll(String input);

    @Query(value = "SELECT * FROM product WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<Product> findByDate(LocalDate ngayTao);

    @Query(value = "select * from product where name = ?", nativeQuery = true)
    Optional<Product> findByName(String name);


}
