package SD94.service.service;

import SD94.entity.product.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductSizeService {

    List<Size> findAllProductSize();

    ResponseEntity<Size> saveEdit(Size sizeUpdate);

    ResponseEntity<List<Size>> deleteProductSize(Long id);

    ResponseEntity<Size> saveCreate(Size sizeCreate);

    List<Size> searchAllProductSize(String search);

    List<Size> searchDateProductSize(String searchDate);

}
