package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.product.TypeProduct;
import SD94.repository.ProductLineRepository;
import SD94.service.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductLineServiceImpl implements ProductLineService {

    @Autowired
    ProductLineRepository repository;


    @Override
    public List<TypeProduct> findAllProductLine() {
        List<TypeProduct> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<TypeProduct> saveEdit(TypeProduct typeProductUpdate) {
        String errorMessage;
        Message errorResponse;

        if (typeProductUpdate.getName() == "") {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<TypeProduct> optional = repository.findById(typeProductUpdate.getId());
            if (optional.isPresent()){
                TypeProduct line = optional.get();
                line.setName(typeProductUpdate.getName());
                repository.save(line);
                return ResponseEntity.ok(line);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<TypeProduct>> deleteProductLine(Long id) {
        try {
            Optional<TypeProduct> optional = repository.findById(id);
            if (optional.isPresent()){
                TypeProduct line = optional.get();
                line.setDeleted(true);
                repository.save(line);

                List<TypeProduct> list = findAllProductLine();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<TypeProduct> saveCreate(TypeProduct typeProductCreate) {
        String errorMessage;
        Message errorResponse;

        if (typeProductCreate.getName() == "") {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            TypeProduct line = new TypeProduct();
            line.setName(typeProductCreate.getName());
            repository.save(line);
            return ResponseEntity.ok(line);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<TypeProduct> searchAllProductLine(String search) {
        List<TypeProduct> list = repository.findByAll(search);
        System.out.println("Search ...");
        return list;
    }

    @Override
    public List<TypeProduct> searchDateProductLine(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<TypeProduct> list = repository.findByDate(search);
        return list;
    }
}
