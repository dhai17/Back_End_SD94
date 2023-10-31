package SD94.service.service;

import SD94.entity.product.TypeProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductLineService {

    List<TypeProduct> findAllProductLine();

    ResponseEntity<TypeProduct> saveEdit(TypeProduct typeProductUpdate);

    ResponseEntity<List<TypeProduct>> deleteProductLine(Long id);

    ResponseEntity<TypeProduct> saveCreate(TypeProduct typeProductCreate);

    List<TypeProduct> searchAllProductLine(String search);

    List<TypeProduct> searchDateProductLine(String searchDate);

}
