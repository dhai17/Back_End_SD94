package SD94.repository;

import SD94.entity.Customer;
import SD94.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query(value = "select * from staff where is_deleted  = false  order by id desc ", nativeQuery = true)
    List<Staff> findAllStaff();

    @Query(value = "select * from staff where id = ? and is_deleted = false", nativeQuery = true)
    Staff findByID(Long id);

    @Query(value = "select * from staff where is_deleted = false and (phone_number LIKE %?1% OR name LIKE %?1% OR email LIKE %?1%) ", nativeQuery = true)
    Optional<Staff> findByName(String name);

    @Query(value = "select * from staff where is_deleted = false and (phone_number LIKE %?1% OR name LIKE %?1% OR email LIKE %?1%)", nativeQuery = true)
    List<Staff> findStaffAll(String input);


    @Query(value = "select * from staff where is_deleted = false  and date(dateOfBrirth)=?", nativeQuery = true)
    List<Staff> findStaffDate(LocalDate dateOfBirth);

}
