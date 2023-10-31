package SD94.service.service;

import SD94.entity.product.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> findAllProduct();

    ResponseEntity<Product> saveEdit(Product productUpdate);

    ResponseEntity<List<Product>> deleteProduct(Long id);

    ResponseEntity<Product> saveCreate(Product productCreate);

    List<Product> searchAllProduct(String search);

    List<Product> searchDateProduct(String searchDate);

}
