package SD94.repository.sanPham;

import SD94.entity.sanPham.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    @Query(value = "SELECT * FROM san_pham WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<SanPham> findAll();

    @Query(value = "select * from san_pham_chi_tiet pd\n" +
            "join hinh_anh ha on pd.id = ha.id_product\n" +
            "where ha.anh_mac_dinh = false;", nativeQuery = true)
    List<SanPham> findAllSpAnh();

    @Query(value = "select * from san_pham where id = ? and is_deleted = false", nativeQuery = true)
    SanPham findByID(Long id);

    @Query(value = "select * from san_pham\n" +
            "where is_deleted = false and (ten_san_pham = ?1 or gia = ?1);", nativeQuery = true)
    List<SanPham> findByAll(String input);

    @Query(value = "SELECT * FROM san_pham WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<SanPham> findByDate(LocalDate ngayTao);

    @Query(value = "select * from san_pham where name = ?", nativeQuery = true)
    Optional<SanPham> findByName(String name);

    @Query(value = "select * from san_pham where loai_san_pham_id = ? and is_deleted = false", nativeQuery = true)
    List<SanPham> findByLoaiSanPham(long loaiSanPham_id);

    @Query(value = "select * from san_pham where chat_lieu_id = ? and is_deleted = false", nativeQuery = true)
    List<SanPham> findByChatLieu(long chatLieu_id);

    @Query(value = "select * from san_pham where nha_san_xuat_id = ? and is_deleted = false", nativeQuery = true)
    List<SanPham> findByNSX(long nhaSanXuat_id);

    @Query(value = "select * from san_pham \n" +
            "         join san_pham_chi_tiet on san_pham.id = san_pham_id\n" +
            "         where mau_sac_id = ? and san_pham.is_deleted = false", nativeQuery = true)
    List<SanPham> findByMau(long mauSac_id);

    @Query(value = "select * from san_pham\n" +
            "         join san_pham_chi_tiet on san_pham.id = san_pham_id\n" +
            "         where kich_co_id = ? and san_pham.is_deleted = false", nativeQuery = true)
    List<SanPham> findByKichCo(long kichCo_id);

    @Query(value = "SELECT * FROM san_pham WHERE gia >= san_pham.gia1 and gia <= gia2 and is_deleted = false", nativeQuery = true)
    List<SanPham> findTheoGia(Float gia1, Float gia2);
}
