package SD94.service.service;

import SD94.entity.product.Material;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductMaterialService {

    List<Material> findAllProductMaterial();

    ResponseEntity<Material> saveEdit(Material materialUpdate);

    ResponseEntity<List<Material>> deleteProductMaterial(Long id);

    ResponseEntity<Material> saveCreate(Material materialCreate);

    List<Material> searchAllProductMaterial(String search);

    List<Material> searchDateProductMaterial(String searchDate);
}
