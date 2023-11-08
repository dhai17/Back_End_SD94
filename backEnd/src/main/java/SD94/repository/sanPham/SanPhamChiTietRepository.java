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

    @Query(value = "select * from san_pham_chi_tiet where san_pham_id = ? and is_deleted = false;", nativeQuery = true)
    List<SanPhamChiTiet> findByProductID(Long id);

    @Query(value = "select * from san_pham_chi_tiet where id_product = ? and id_color = ? and id_size = ?", nativeQuery = true)
    SanPhamChiTiet findByColorAndSize(long id_product, long id_color, long id_size);

    @Query(value = "SELECT pc.ma_mau_sac AS color_name, ps.kich_co AS size_name\n" +
            "FROM san_pham_chi_tiet pd\n" +
            "         JOIN mau_sac pc ON pd.mau_sac_id = pc.id\n" +
            "         JOIN kich_co ps ON pd.kich_co_id = ps.id\n" +
            "WHERE pd.san_pham_id = ?\n" +
            "GROUP BY pc.ma_mau_sac, ps.kich_co;", nativeQuery = true)
    List<String> getProduct(long id_product);

    @Query(value = "select tenAnh from hinh_anh where id_product = ? and anh_mac_dinh = true", nativeQuery = true)
    String getAnhMacDinh(long sanPham_id);

    @Query(value = "select so_luong from san_pham_chi_tiet where mau_sac_id = ?1 and kich_co_id = ?2 and san_pham_id = ?3", nativeQuery = true)
    Integer getSoLuongHienCp(long mau_sac_id, long kich_co_id, long san_pham_id);

    @Query(value = "select * from san_pham_chi_tiet where mau_sac_id = ?1 and kich_co_id = ?2 and san_pham_id = ?3", nativeQuery = true)
    SanPhamChiTiet getSanPhamChiTiet(long mau_sac_id, long kich_co_id, long san_pham_id);
}
