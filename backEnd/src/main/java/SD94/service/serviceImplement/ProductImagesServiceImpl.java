package SD94.service.serviceImplement;

import SD94.controller.Message.Message;
import SD94.entity.ProductImages;
import SD94.repository.ProductImagesRepository;
import SD94.service.ProductImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductImagesServiceImpl implements ProductImagesService {

    @Autowired
    ProductImagesRepository repository;


    @Override
    public List<ProductImages> findAllProductImages() {
        List<ProductImages> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<ProductImages> saveEdit(ProductImages productImagesUpdate) {
        String errorMessage;
        Message errorResponse;

        if (productImagesUpdate.getName() == "" || productImagesUpdate.getImg() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<ProductImages> optional = repository.findById(productImagesUpdate.getId());
            if (optional.isPresent()){
                ProductImages images = optional.get();
                images.setName(productImagesUpdate.getName());
                images.setImg(productImagesUpdate.getImg());
                images.setColor(productImagesUpdate.getColor());
                images.setProductDetails(productImagesUpdate.getProductDetails());
                repository.save(images);
                return ResponseEntity.ok(images);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ProductImages>> deleteProductImages(Long id) {
        try {
            Optional<ProductImages> optional = repository.findById(id);
            if (optional.isPresent()){
                ProductImages images = optional.get();
                images.setDeleted(true);
                repository.save(images);

                List<ProductImages> list = findAllProductImages();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<ProductImages> saveCreate(ProductImages productImagesCreate) {
        String errorMessage;
        Message errorResponse;

        if (productImagesCreate.getName() == "" || productImagesCreate.getImg() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            ProductImages images = new ProductImages();
            images.setName(productImagesCreate.getName());
            images.setImg(productImagesCreate.getImg());
            images.setColor(productImagesCreate.getColor());
            images.setProductDetails(productImagesCreate.getProductDetails());
            repository.save(images);
            return ResponseEntity.ok(images);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ProductImages> searchAllProductImages(String search) {
        List<ProductImages> list = repository.findByAll(search);
        System.out.println("Search ...");
        return list;
    }

    @Override
    public List<ProductImages> searchDateProductImages(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<ProductImages> list = repository.findByDate(search);
        return list;
    }

    @Override
    public ProductImages saveOrUpdate(ProductImages images) {
//        images.setImg(0);
        return repository.save(images);
    }

    @Override
    public ProductImages findByMaSanPham(Long maSanPham) {
        ProductImages banPhim = new ProductImages();
        banPhim.setId(maSanPham);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());

        Example<ProductImages> example = Example.of(banPhim, matcher);
        return repository.findOne(example).orElse(null);
    }
}
