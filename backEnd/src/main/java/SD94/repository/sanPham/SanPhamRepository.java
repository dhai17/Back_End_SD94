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

    @Query(value = "select * from san_pham sp\n" +
            "                  join chat_lieu cl on sp.chat_lieu_id = cl.id\n" +
            "                  join nha_san_xuat nsx on sp.nha_san_xuat_id = nsx.id\n" +
            "                  join loai_san_pham lsp on sp.loai_san_pham_id = lsp.id\n" +
            "where sp.is_deleted = false and (ten_san_pham = ?1 or gia = ?1 or chat_lieu = ?1 or nha_san_xuat = ?1 or loai_san_pham = ?1);", nativeQuery = true)
    List<SanPham> findByAll(String input);

    @Query(value = "SELECT * FROM san_pham WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<SanPham> findByDate(LocalDate ngayTao);

    @Query(value = "select * from san_pham where name = ?", nativeQuery = true)
    Optional<SanPham> findByName(String name);

    @Query(value = "select * from san_pham where loaiSanPham_id = ?", nativeQuery = true)
    List<SanPham> findByLoaiSanPham(long loaiSanPham_id);

    @Query(value = "select * from san_pham where chatLieu_id = ?", nativeQuery = true)
    List<SanPham> findByChatLieu(long chatLieu_id);

    @Query(value = "select * from san_pham where nhaSanXuat_id = ?", nativeQuery = true)
    List<SanPham> findByNSX(long nhaSanXuat_id);

    @Query(value = "SELECT * FROM san_pham WHERE gia >= gia1 and gia <= gia2", nativeQuery = true)
    List<SanPham> findTheoGia(Float gia1, Float gia2);
}
