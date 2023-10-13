package SD94.service;

import SD94.entity.ProductMaterial;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductMaterialService {

    List<ProductMaterial> findAllProductMaterial();

    ResponseEntity<ProductMaterial> saveEdit(ProductMaterial productMaterialUpdate);

    ResponseEntity<List<ProductMaterial>> deleteProductMaterial(Long id);

    ResponseEntity<ProductMaterial> saveCreate(ProductMaterial productMaterialCreate);

    List<ProductMaterial> searchAllProductMaterial(String search);

    List<ProductMaterial> searchDateProductMaterial(String searchDate);
}
