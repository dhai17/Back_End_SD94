package SD94.repository;

import SD94.entity.cart.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailsRepository extends JpaRepository<CartDetails, Long> {
    @Query(value = "select * from detailed_shopping_cart where cart_id = ? and is_deleted = false", nativeQuery = true)
    List<CartDetails> findByCartID(long id);

    @Query(value = "select * from detailed_shopping_cart where product_details_id = ?", nativeQuery = true)
    CartDetails findByProductDetailsID(long id);
}
