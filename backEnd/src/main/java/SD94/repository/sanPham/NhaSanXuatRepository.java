package SD94.repository.sanPham;

import SD94.entity.sanPham.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface NhaSanXuatRepository extends JpaRepository<ChatLieu, Long> {
    @Query(value = "SELECT * FROM product_material WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<ChatLieu> findAll();

    @Query(value = "select * from product_material where id = ? and is_deleted = false", nativeQuery = true)
    ChatLieu findByID(Long id);

    @Query(value = "SELECT * FROM product_material WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<ChatLieu> findByAll(String input);

    @Query(value = "SELECT * FROM product_material WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<ChatLieu> findByDate(LocalDate ngayTao);

    @Query(value = "select * from product_material where name = ?", nativeQuery = true)
    Optional<ChatLieu> findByName(String name);
}
