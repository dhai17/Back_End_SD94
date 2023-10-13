package SD94.repository;

import SD94.entity.ProductMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Long> {
    @Query(value = "SELECT * FROM product_material WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<ProductMaterial> findAll();

    @Query(value = "select * from product_material where material = ? and is_deleted = false", nativeQuery = true)
    ProductMaterial findByID(Long id);

    @Query(value = "SELECT * FROM product_material WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<ProductMaterial> findByAll(String input);

    @Query(value = "SELECT * FROM product_material WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<ProductMaterial> findByDate(LocalDate ngayTao);

    @Query(value = "select * from product_material where name = ?", nativeQuery = true)
    Optional<ProductMaterial> findByName(String name);
}
