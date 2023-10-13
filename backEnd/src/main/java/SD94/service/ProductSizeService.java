package SD94.service;

import SD94.entity.ProductSize;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductSizeService {

    List<ProductSize> findAllProductSize();

    ResponseEntity<ProductSize> saveEdit(ProductSize productSizeUpdate);

    ResponseEntity<List<ProductSize>> deleteProductSize(Long id);

    ResponseEntity<ProductSize> saveCreate(ProductSize productSizeCreate);

    List<ProductSize> searchAllProductSize(String search);

    List<ProductSize> searchDateProductSize(String searchDate);

}
