package SD94.service.serviceImplement;

import SD94.controller.Message.Message;
import SD94.entity.ProductSize;
import SD94.repository.ProductSizeRepository;
import SD94.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    @Autowired
    ProductSizeRepository repository;


    @Override
    public List<ProductSize> findAllProductSize() {
        List<ProductSize> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<ProductSize> saveEdit(ProductSize productSizeUpdate) {
        String errorMessage;
        Message errorResponse;

        if (productSizeUpdate.getShoeSize() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<ProductSize> optional = repository.findById(productSizeUpdate.getId());
            if (optional.isPresent()){
                ProductSize product = optional.get();
                product.setShoeSize(productSizeUpdate.getShoeSize());
                repository.save(product);
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ProductSize>> deleteProductSize(Long id) {
        try {
            Optional<ProductSize> optional = repository.findById(id);
            if (optional.isPresent()){
                ProductSize product = optional.get();
                product.setDeleted(true);
                repository.save(product);

                List<ProductSize> list = findAllProductSize();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<ProductSize> saveCreate(ProductSize productSizeCreate) {
        String errorMessage;
        Message errorResponse;

        if (productSizeCreate.getShoeSize() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            ProductSize product = new ProductSize();
            product.setShoeSize(productSizeCreate.getShoeSize());
            repository.save(product);
            return ResponseEntity.ok(product);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ProductSize> searchAllProductSize(String search) {
        List<ProductSize> list = repository.findByAll(search);
        System.out.println("Search ...");
        return list;
    }

    @Override
    public List<ProductSize> searchDateProductSize(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<ProductSize> list = repository.findByDate(search);
        return list;
    }
}
