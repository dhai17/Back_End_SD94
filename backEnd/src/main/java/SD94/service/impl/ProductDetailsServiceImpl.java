package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.product.ProductDetails;
import SD94.repository.ProductDetailsRepository;
import SD94.service.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

    @Autowired
    ProductDetailsRepository repository;


    @Override
    public List<ProductDetails> findAllProductDetails() {
        List<ProductDetails> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<ProductDetails> saveEdit(ProductDetails productDetailsUpdate) {
//        String errorMessage;
//        Message errorResponse;
//
//        if (productDetailsUpdate.getQuantity() == null) {
//            errorMessage = "Nhập đầy đủ thông tin";
//            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
//            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
//        }

        try {
            Optional<ProductDetails> optional = repository.findById(productDetailsUpdate.getId());
            if (optional.isPresent()){
                ProductDetails productDetails = optional.get();
                productDetails.setQuantity(productDetailsUpdate.getQuantity());
                productDetails.setStatus(productDetailsUpdate.getStatus());
                productDetails.setProduct(productDetailsUpdate.getProduct());
                productDetails.setColor(productDetailsUpdate.getColor());
                productDetails.setSize(productDetailsUpdate.getSize());
                repository.save(productDetails);
                return ResponseEntity.ok(productDetails);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ProductDetails>> deleteProductDetails(Long id) {
        try {
            Optional<ProductDetails> optional = repository.findById(id);
            if (optional.isPresent()){
                ProductDetails productDetails = optional.get();
                productDetails.setDeleted(true);
                repository.save(productDetails);

                List<ProductDetails> list = findAllProductDetails();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<ProductDetails> saveCreate(ProductDetails productDetailsCreate) {
        String errorMessage;
        Message errorResponse;
//
//        if (productDetailsCreate.getQuantity() == null) {
//            errorMessage = "Nhập đầy đủ thông tin";
//            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
//            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
//        }

        try {
            ProductDetails productDetails = new ProductDetails();
//            productDetails.setQuantity(productDetailsCreate.getQuantity());
//            productDetails.setStatus(productDetailsCreate.getStatus());
//            productDetails.setProduct(productDetailsCreate.getProduct());
//            productDetails.setProductColor(productDetailsCreate.getProductColor());
//            productDetails.setProductSize(productDetailsCreate.getProductSize());
//            repository.save(productDetails);
            return ResponseEntity.ok(productDetails);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ProductDetails> searchAllProductDetails(String search) {
        List<ProductDetails> list = repository.findByAll(search);
        System.out.println("Search ...");
        return list;
    }

    @Override
    public List<ProductDetails> searchDateProductDetails(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<ProductDetails> list = repository.findByDate(search);
        return list;
    }
}
