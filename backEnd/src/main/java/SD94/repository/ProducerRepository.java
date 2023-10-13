package SD94.repository;

import SD94.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    @Query(value = "SELECT * FROM producer WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<Producer> findAllProducer();

    @Query(value = "select * from producer where name = ? and is_deleted = false", nativeQuery = true)
    Producer findByID(Long id);

    @Query(value = "SELECT * FROM producer WHERE is_deleted = false AND (name LIKE %?1% OR maximumvalue LIKE %?1% OR percent_discount LIKE %?1%)", nativeQuery = true)
    List<Producer> findProducerByAll(String input);

    @Query(value = "SELECT * FROM producer WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<Producer> findProducerByDate(LocalDate ngayTao);

    @Query(value = "select * from producer where name = ?", nativeQuery = true)
    Optional<Producer> findByName(String name);
}
