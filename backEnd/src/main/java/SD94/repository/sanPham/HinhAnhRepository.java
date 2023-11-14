package SD94.repository.sanPham;

import SD94.entity.sanPham.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, Long> {
    @Query(value = "SELECT * FROM hinh_anh WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<HinhAnh> findAll();

    @Query(value = "select * from hinh_anh where id = ? and is_deleted = false", nativeQuery = true)
    HinhAnh findByID(Long id);

    @Query(value = "SELECT * FROM hinh_anh WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<HinhAnh> findByDate(LocalDate ngayTao);

    @Query(value = "select * from hinh_anh where name = ?", nativeQuery = true)
    Optional<HinhAnh> findByName(String name);

    @Query(value = "select * from hinh_anh where id_product = ? and is_deleted = false", nativeQuery = true)
    List<HinhAnh> findByIDProduct(long id_sanPham);

    @Query(value = "select * from hinh_anh where id_product = ? and anh_mac_dinh = true;", nativeQuery = true)
    HinhAnh findAnhMacDinh(long id_sp);

    @Modifying
    @Query(value = "delete from hinh_anh where id = ?", nativeQuery = true)
    void xoaAnh(long id);
}
