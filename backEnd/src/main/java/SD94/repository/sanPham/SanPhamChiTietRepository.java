package SD94.repository.sanPham;

import SD94.entity.sanPham.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Long> {
    @Query(value = "SELECT * FROM san_pham_chi_tiet WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<SanPhamChiTiet> findAll();

    @Query(value = "select * from san_pham_chi_tiet where id = ? and is_deleted = false", nativeQuery = true)
    SanPhamChiTiet findByID(Long id);

    @Query(value = "SELECT * FROM san_pham_chi_tiet WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<SanPhamChiTiet> findByAll(String input);

    @Query(value = "SELECT * FROM san_pham_chi_tiet WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<SanPhamChiTiet> findByDate(LocalDate ngayTao);

    @Query(value = "select * from san_pham_chi_tiet where name = ?", nativeQuery = true)
    Optional<SanPhamChiTiet> findByName(String name);

    @Query(value = "select * from san_pham_chi_tiet where is_deleted = false and san_pham_id = ?", nativeQuery = true)
    List<SanPhamChiTiet> findByProductID(Long id);

    @Query(value = "select * from san_pham_chi_tiet where id_product = ? and id_color = ? and id_size = ?", nativeQuery = true)
    SanPhamChiTiet findByColorAndSize(long id_product, long id_color, long id_size);

    @Query(value = "SELECT pc.color AS color_name, ps.shoe_size AS size_name\n" +
            "FROM san_pham_chi_tiet pd\n" +
            "         JOIN product_color pc ON pd.id_color = pc.id\n" +
            "         JOIN product_size ps ON pd.id_size = ps.id\n" +
            "WHERE pd.id_product = ?\n" +
            "GROUP BY pc.color, ps.shoe_size;", nativeQuery = true)
    List<String> getProduct(long id_product);

}
