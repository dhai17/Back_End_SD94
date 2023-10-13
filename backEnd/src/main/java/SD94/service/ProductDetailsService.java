package SD94.service;

import SD94.entity.ProductDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductDetailsService {

    List<ProductDetails> findAllProductDetails();

    ResponseEntity<ProductDetails> saveEdit(ProductDetails productDetailsUpdate);

    ResponseEntity<List<ProductDetails>> deleteProductDetails(Long id);

    ResponseEntity<ProductDetails> saveCreate(ProductDetails productDetailsCreate);

    List<ProductDetails> searchAllProductDetails(String search);

    List<ProductDetails> searchDateProductDetails(String searchDate);

}
