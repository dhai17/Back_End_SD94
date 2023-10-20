package SD94.service;

import SD94.entity.ProductColor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductColorService {

    List<ProductColor> findAllProductColor();

    ResponseEntity<ProductColor> saveEdit(ProductColor productColorUpdate);

    ResponseEntity<List<ProductColor>> deleteProductColor(Long id);

    ResponseEntity<ProductColor> saveCreate(ProductColor productColor);

    List<ProductColor> searchAllProductColor(String search);

    List<ProductColor> searchDateProductColor(String searchDate);

}
