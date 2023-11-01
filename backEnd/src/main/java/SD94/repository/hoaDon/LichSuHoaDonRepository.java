package SD94.repository.hoaDon;

import SD94.entity.hoaDon.LichSuHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichSuHoaDonRepository extends JpaRepository<LichSuHoaDon, Long> {
    @Query(value = "select * from lich_su_hoa_don where hoa_don_id = ? and trang_thai_id = ? group by id", nativeQuery = true)
    List<LichSuHoaDon> findTimeLineByHoaDonID(long id_hoa_don, long id_trang_thai);
}
