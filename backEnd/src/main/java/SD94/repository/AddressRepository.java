package SD94.repository;

import SD94.entity.khachHang.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<DiaChi, Long> {
    @Query(value = "select * from add_ress where costomer_id = ?", nativeQuery = true)
    List<DiaChi> findByCustomerID(long id);

    @Query(value = "select * from add_ress where costomer_id = ? and id = ?", nativeQuery = true)
    DiaChi findbyCustomerAndID(long costomer_id, long id);
}
