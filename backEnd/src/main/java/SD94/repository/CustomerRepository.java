package SD94.repository;

import SD94.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select * from customer where is_deleted = false ORDER BY id desc", nativeQuery = true)
    List<Customer> findAllCustomer();

    @Query(value = "select * from customer where name = ?", nativeQuery = true)
    Optional<Customer> findByName(String name);
}
