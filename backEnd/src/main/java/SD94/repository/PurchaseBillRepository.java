package SD94.repository;

import SD94.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PurchaseBillRepository extends JpaRepository<Bill, Long> {
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 1  ORDER BY id DESC", nativeQuery = true)
    List<Bill> findAllBill1();
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 2  ORDER BY id DESC", nativeQuery = true)
    List<Bill> findAllBill2();
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 3  ORDER BY id DESC", nativeQuery = true)
    List<Bill> findAllBill3();
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 4  ORDER BY id DESC", nativeQuery = true)
    List<Bill> findAllBill4();
    @Query(value = "SELECT * FROM bill WHERE is_deleted = false and id_status = 5  ORDER BY id DESC", nativeQuery = true)
    List<Bill> findAllBill5();

    @Query(value = "select * from bill where id = ? and is_deleted = false", nativeQuery = true)
    Bill findByID(Long id);

    @Modifying
    @Query(value = "update bill set id_status = ? where id = ?;", nativeQuery = true)
    void updateStatus(long id_status, long id_bill);

}
