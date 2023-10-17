package SD94.repository;

import SD94.entity.AddRess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddRess, Long> {
    @Query(value = "select * from add_ress where costomer_id = ?", nativeQuery = true)
    List<AddRess> findByCustomerID(long id);

    @Query(value = "select * from add_ress where costomer_id = ? and id = ?", nativeQuery = true)
    AddRess findbyCustomerAndID(long costomer_id, long id);
}
