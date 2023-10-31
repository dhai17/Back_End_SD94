package SD94.service.service;

import SD94.entity.product.Color;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductColorService {

    List<Color> findAllProductColor();

    ResponseEntity<Color> saveEdit(Color colorUpdate);

    ResponseEntity<List<Color>> deleteProductColor(Long id);

    ResponseEntity<Color> saveCreate(Color color);

    List<Color> searchAllProductColor(String search);

    List<Color> searchDateProductColor(String searchDate);

}
