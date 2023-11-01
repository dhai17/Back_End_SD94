package SD94.repository.sanPham;

import SD94.entity.sanPham.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Long> {
    @Query(value = "select * from mau_sac where is_deleted =false ORDER BY id DESC", nativeQuery = true)
    List<MauSac> findAll();

    @Query(value = "select * from mau_sac where id = ? and is_deleted = false", nativeQuery = true)
    MauSac findByID(Long id);

    @Query(value = "SELECT * FROM mau_sac WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<MauSac> findByAll(String input);

    @Query(value = "SELECT * FROM mau_sac WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<MauSac> findByDate(LocalDate ngayTao);

    @Query(value = "select * from mau_sac where name = ?", nativeQuery = true)
    Optional<MauSac> findByName(String name);

    @Query(value = "SELECT pc.code AS color_name FROM product_details pd JOIN mau_sac pc ON pd.id_color = pc.id WHERE pd.id_product = ? GROUP BY pd.id_color;", nativeQuery = true)
    List<String> getColor(long id);
}
