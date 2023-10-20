package SD94.controller.admin.product;

import SD94.entity.ProductImages;
import SD94.repository.ProductImagesRepository;
import SD94.service.ProductImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/productImg")
public class ImagesController {

    @Autowired
    ProductImagesService productImagesService;

    @PostMapping("/add")
    public ResponseEntity<ProductImages> add(@RequestPart(value = "file", required = false) MultipartFile file,
                                             @RequestPart("requestData") ProductImages images) throws IOException {
        images.setImages(file.getBytes());
        return ResponseEntity.ok(productImagesService.saveOrUpdate(images));
    }

    @GetMapping("/detail/{id}")
    public ProductImages detail(@PathVariable(value = "id") Long id) {
        ProductImages productImages = productImagesService.findByMaSanPham(id);
        return productImages;
    }

}
