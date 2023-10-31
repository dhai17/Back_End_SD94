package SD94.service.service;

import SD94.entity.product.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductImagesService {

    List<Image> findAllProductImages();

    ResponseEntity<Image> saveEdit(Image imageUpdate);

    ResponseEntity<List<Image>> deleteProductImages(Long id);

    ResponseEntity<Image> saveCreate(Image imageCreate);

    List<Image> searchAllProductImages(String search);

    List<Image> searchDateProductImages(String searchDate);

    Image saveOrUpdate(Image images);

    Image findByMaSanPham(Long maSanPham);

}
