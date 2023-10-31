package SD94.repository.sanPham;

import SD94.entity.sanPham.KichCo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface KichCoRepository extends JpaRepository<KichCo, Long> {
    @Query(value = "SELECT * FROM product_KichCo WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<KichCo> findAll();

    @Query(value = "select * from product_KichCo where id = ? and is_deleted = false", nativeQuery = true)
    KichCo findByID(Long id);

    @Query(value = "SELECT * FROM product_KichCo WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<KichCo> findByAll(String input);

    @Query(value = "SELECT * FROM product_KichCo WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<KichCo> findByDate(LocalDate ngayTao);

    @Query(value = "select * from product_KichCo where name = ?", nativeQuery = true)
    Optional<KichCo> findByName(String name);

    @Query(value = "SELECT pc.shoe_KichCo AS KichCo_name FROM product_details pd JOIN product_KichCo pc ON pd.id_KichCo = pc.id WHERE pd.id_product = ? GROUP BY pd.id_KichCo\n", nativeQuery = true)
    List<String> getKichCo(long id_product);
}
