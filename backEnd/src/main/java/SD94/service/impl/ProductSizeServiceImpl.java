package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.product.Size;
import SD94.repository.ProductSizeRepository;
import SD94.service.service.ProductSizeService;
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
    public List<Size> findAllProductSize() {
        List<Size> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<Size> saveEdit(Size sizeUpdate) {
        String errorMessage;
        Message errorResponse;

        if (sizeUpdate.getShoeSize() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<Size> optional = repository.findById(sizeUpdate.getId());
            if (optional.isPresent()){
                Size product = optional.get();
                product.setShoeSize(sizeUpdate.getShoeSize());
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
    public ResponseEntity<List<Size>> deleteProductSize(Long id) {
        try {
            Optional<Size> optional = repository.findById(id);
            if (optional.isPresent()){
                Size product = optional.get();
                product.setDeleted(true);
                repository.save(product);

                List<Size> list = findAllProductSize();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<Size> saveCreate(Size sizeCreate) {
        String errorMessage;
        Message errorResponse;

        if (sizeCreate.getShoeSize() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Size product = new Size();
            product.setShoeSize(sizeCreate.getShoeSize());
            repository.save(product);
            return ResponseEntity.ok(product);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Size> searchAllProductSize(String search) {
        List<Size> list = repository.findByAll(search);
        System.out.println("Search ...");
        return list;
    }

    @Override
    public List<Size> searchDateProductSize(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Size> list = repository.findByDate(search);
        return list;
    }
}
