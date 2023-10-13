package SD94.service;

import SD94.entity.ProductImages;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductImagesService {

    List<ProductImages> findAllProductImages();

    ResponseEntity<ProductImages> saveEdit(ProductImages productImagesUpdate);

    ResponseEntity<List<ProductImages>> deleteProductImages(Long id);

    ResponseEntity<ProductImages> saveCreate(ProductImages productImagesCreate);

    List<ProductImages> searchAllProductImages(String search);

    List<ProductImages> searchDateProductImages(String searchDate);

}
