package SD94.service;

import SD94.entity.ProductLine;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductLineService {

    List<ProductLine> findAllProductLine();

    ResponseEntity<ProductLine> saveEdit(ProductLine productLineUpdate);

    ResponseEntity<List<ProductLine>> deleteProductLine(Long id);

    ResponseEntity<ProductLine> saveCreate(ProductLine productLineCreate);

    List<ProductLine> searchAllProductLine(String search);

    List<ProductLine> searchDateProductLine(String searchDate);

}
