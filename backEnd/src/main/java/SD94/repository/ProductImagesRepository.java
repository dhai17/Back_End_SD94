package SD94.repository;

import SD94.entity.product.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImagesRepository extends JpaRepository<Image, Long> {
    @Query(value = "SELECT * FROM productImages WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<Image> findAll();

    @Query(value = "select * from productImages where id = ? and is_deleted = false", nativeQuery = true)
    Image findByID(Long id);

    @Query(value = "SELECT * FROM productImages WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<Image> findByAll(String input);

    @Query(value = "SELECT * FROM productImages WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<Image> findByDate(LocalDate ngayTao);

    @Query(value = "select * from productImages where name = ?", nativeQuery = true)
    Optional<Image> findByName(String name);
}
