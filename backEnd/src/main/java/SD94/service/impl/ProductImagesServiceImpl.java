package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.product.Image;
import SD94.repository.ProductImagesRepository;
import SD94.service.service.ProductImagesService;
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
    public List<Image> findAllProductImages() {
        List<Image> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<Image> saveEdit(Image imageUpdate) {
        try {
            Optional<Image> optional = repository.findById(imageUpdate.getId());
            if (optional.isPresent()){
                Image images = optional.get();
                images.setName(imageUpdate.getName());
//                images.setImg(productImagesUpdate.getImg());
                images.setColor(imageUpdate.getColor());
                images.setProductDetails(imageUpdate.getProductDetails());
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
    public ResponseEntity<List<Image>> deleteProductImages(Long id) {
        try {
            Optional<Image> optional = repository.findById(id);
            if (optional.isPresent()){
                Image images = optional.get();
                images.setDeleted(true);
                repository.save(images);

                List<Image> list = findAllProductImages();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<Image> saveCreate(Image imageCreate) {
        try {
            Image images = new Image();
            images.setName(imageCreate.getName());
            images.setColor(imageCreate.getColor());
            images.setProductDetails(imageCreate.getProductDetails());
            repository.save(images);
            return ResponseEntity.ok(images);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Image> searchAllProductImages(String search) {
        List<Image> list = repository.findByAll(search);
        System.out.println("Search ...");
        return list;
    }

    @Override
    public List<Image> searchDateProductImages(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Image> list = repository.findByDate(search);
        return list;
    }

    @Override
    public Image saveOrUpdate(Image images) {
//        images.setImg(0);
        return repository.save(images);
    }

    @Override
    public Image findByMaSanPham(Long maSanPham) {
        Image banPhim = new Image();
        banPhim.setId(maSanPham);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());

        Example<Image> example = Example.of(banPhim, matcher);
        return repository.findOne(example).orElse(null);
    }
}
