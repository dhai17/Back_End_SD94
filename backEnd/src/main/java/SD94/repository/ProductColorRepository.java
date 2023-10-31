package SD94.repository;

import SD94.entity.product.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductColorRepository extends JpaRepository<Color, Long> {
    @Query(value = "select * from product_color where is_deleted =false ORDER BY id DESC", nativeQuery = true)
    List<Color> findAll();

    @Query(value = "select * from product_color where id = ? and is_deleted = false", nativeQuery = true)
    Color findByID(Long id);

    @Query(value = "SELECT * FROM product_color WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<Color> findByAll(String input);

    @Query(value = "SELECT * FROM product_color WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<Color> findByDate(LocalDate ngayTao);

    @Query(value = "select * from product_color where name = ?", nativeQuery = true)
    Optional<Color> findByName(String name);

    @Query(value = "SELECT pc.code AS color_name FROM product_details pd JOIN product_color pc ON pd.id_color = pc.id WHERE pd.id_product = ? GROUP BY pd.id_color;", nativeQuery = true)
    List<String> getColor(long id);
}
