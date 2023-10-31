package SD94.repository;

import SD94.entity.gioHang.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<GioHang, Long> {
    @Query(value = "select * from cart where customer_id = ?", nativeQuery = true)
    GioHang findbyCustomerID(long id);
}
