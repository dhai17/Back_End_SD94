package SD94.repository;

import SD94.entity.khuyenMai.KhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, Long> {
    @Query(value = "SELECT * FROM khuyen_mai WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<KhuyenMai> findAllkhuyenMai();

    @Query(value = "select * from khuyen_mai where id = ? and is_deleted = false", nativeQuery = true)
    KhuyenMai findByID(Long id);

    @Query(value = "SELECT * FROM khuyen_mai WHERE is_deleted = false AND (ten_khuyen_mai LIKE %?1% OR tien_giam_toi_da LIKE %?1% OR phan_tram_giam LIKE %?1%)", nativeQuery = true)
    List<KhuyenMai> findkhuyenMaiByAll(String input);

    @Query(value = "SELECT * FROM khuyen_mai WHERE is_deleted = false AND DATE(ngay_bat_dau) = ?", nativeQuery = true)
    List<KhuyenMai> findkhuyenMaiByDate(LocalDate ngayTao);

    @Query(value = "select * from khuyen_mai where ten_khuyen_mai = ?", nativeQuery = true)
    Optional<KhuyenMai> findByten(String ten);

    @Modifying
    @Query(value = "update khuyen_mai set trang_thai = ?1 where id = ?2", nativeQuery = true)
    void updateStatusDiscount(int trangThai, long id);
}
