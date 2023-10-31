package SD94.repository;

import SD94.entity.hoaDon.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseBillRepository extends JpaRepository<HoaDon, Long> {
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 1  ORDER BY id DESC", nativeQuery = true)
    List<HoaDon> findAllBill1();
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 2  ORDER BY id DESC", nativeQuery = true)
    List<HoaDon> findAllBill2();
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 3  ORDER BY id DESC", nativeQuery = true)
    List<HoaDon> findAllBill3();
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 4  ORDER BY id DESC", nativeQuery = true)
    List<HoaDon> findAllBill4();
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 5  ORDER BY id DESC", nativeQuery = true)
    List<HoaDon> findAllBill5();


    @Query(value = "SELECT * FROM bill  WHERE is_deleted = false and  id_status = 1 " +
            "AND (code LIKE %?1% OR phone_number LIKE %?1% OR email LIKE %?1%)", nativeQuery = true)
    List<HoaDon> findBillByAll1(String input);
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 1 AND DATE(created_date) = ?", nativeQuery = true)
    List<HoaDon> findBillByDate1(LocalDate ngayTao);

    @Query(value = "select * from bill where id = ? and is_deleted = false", nativeQuery = true)
    HoaDon findByID(Long id);

    @Modifying
    @Query(value = "update bill set id_status = ? where id = ?;", nativeQuery = true)
    void updateStatus(long id_status, long id_bill);

    @Query(value = "SELECT * FROM bill  WHERE is_deleted = false and  id_status = 2 " +
            "AND (code LIKE %?1% OR phone_number LIKE %?1% OR email LIKE %?1%)", nativeQuery = true)
    List<HoaDon> findBillByAll2(String input);
    //search
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 2 AND DATE(created_date) = ?", nativeQuery = true)
    List<HoaDon> findBillByDate2(LocalDate ngayTao);

    @Query(value = "SELECT * FROM bill  WHERE is_deleted = false and  id_status = 3 " +
            "AND (code LIKE %?1% OR phone_number LIKE %?1% OR email LIKE %?1%)", nativeQuery = true)
    List<HoaDon> findBillByAll3(String input);
    //search
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 3 AND DATE(created_date) = ?", nativeQuery = true)
    List<HoaDon> findBillByDate3(LocalDate ngayTao);
    @Query(value = "SELECT * FROM bill  WHERE is_deleted = false and  id_status = 4 " +
            "AND (code LIKE %?1% OR phone_number LIKE %?1% OR email LIKE %?1%)", nativeQuery = true)
    List<HoaDon> findBillByAll4(String input);
    //search
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 4 AND DATE(created_date) = ?", nativeQuery = true)
    List<HoaDon> findBillByDate4(LocalDate ngayTao);

    @Query(value = "SELECT * FROM bill  WHERE is_deleted = false and  id_status = 5 " +
            "AND (code LIKE %?1% OR phone_number LIKE %?1% OR email LIKE %?1%)", nativeQuery = true)
    List<HoaDon> findBillByAll5(String input);
    //search
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 5 AND DATE(created_date) = ?", nativeQuery = true)
    List<HoaDon> findBillByDate5(LocalDate ngayTao);

}
