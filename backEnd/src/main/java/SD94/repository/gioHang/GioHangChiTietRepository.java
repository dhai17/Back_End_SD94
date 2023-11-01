package SD94.repository.gioHang;

import SD94.entity.gioHang.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {
    @Query(value = "select * from gio_hang_chi_tiet where cart_id = ? and is_deleted = false", nativeQuery = true)
    List<GioHangChiTiet> findByCartID(long id);

    @Query(value = "select * from gio_hang_chi_tiet where product_details_id = ?", nativeQuery = true)
    GioHangChiTiet findByProductDetailsID(long id);
}
