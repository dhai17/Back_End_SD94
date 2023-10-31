package SD94.repository;

import SD94.entity.nhanVien.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<NhanVien, Long> {

    @Query(value = "select * from nhan_vien where is_deleted  = false  order by id desc ", nativeQuery = true)
    List<NhanVien> findAllStaff();

    @Query(value = "select * from nhan_vien where id = ? and is_deleted = false", nativeQuery = true)
    NhanVien findByID(Long id);

    @Query(value = "select * from nhan_vien where is_deleted = false and (phone_number LIKE %?1% OR name LIKE %?1% OR email LIKE %?1%) ", nativeQuery = true)
    Optional<NhanVien> findByName(String name);

    @Query(value = "select * from nhan_vien where is_deleted = false and (phone_number LIKE %?1% OR name LIKE %?1% OR email LIKE %?1%)", nativeQuery = true)
    List<NhanVien> findStaffAll(String input);


    @Query(value = "select * from nhan_vien where is_deleted = false  and date(dateOfBrirth)=?", nativeQuery = true)
    List<NhanVien> findStaffDate(LocalDate dateOfBirth);

    //    Staff findByUsername(String username);
    @Query(value = "select * from nhan_vien where email = ?", nativeQuery = true)
    NhanVien findByEmail(String username);
}
