package SD94.repository;

import SD94.entity.DetailedShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailsRepository extends JpaRepository<DetailedShoppingCart, Long> {
    @Query(value = "select * from detailed_shopping_cart where cart_id = ? and is_deleted = false", nativeQuery = true)
    List<DetailedShoppingCart> findByCartID(long id);

    @Query(value = "select * from detailed_shopping_cart where product_details_id = ?", nativeQuery = true)
    DetailedShoppingCart findByProductDetailsID(long id);
}
