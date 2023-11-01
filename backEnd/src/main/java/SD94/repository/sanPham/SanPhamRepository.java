package SD94.repository.sanPham;

import SD94.entity.sanPham.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    @Query(value = "SELECT * FROM san_pham WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<SanPham> findAll();

    @Query(value = "select * from san_pham where id = ? and is_deleted = false", nativeQuery = true)
    SanPham findByID(Long id);

    @Query(value = "SELECT * FROM san_pham WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<SanPham> findByAll(String input);

    @Query(value = "SELECT * FROM san_pham WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<SanPham> findByDate(LocalDate ngayTao);

    @Query(value = "select * from san_pham where name = ?", nativeQuery = true)
    Optional<SanPham> findByName(String name);


}
