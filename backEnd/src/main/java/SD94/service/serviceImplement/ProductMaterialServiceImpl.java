package SD94.service.serviceImplement;

import SD94.controller.Message.Message;
import SD94.entity.ProductMaterial;
import SD94.repository.ProductMaterialRepository;
import SD94.service.ProductMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductMaterialServiceImpl implements ProductMaterialService {

    @Autowired
    ProductMaterialRepository repository;

    @Override
    public List<ProductMaterial> findAllProductMaterial() {
        List<ProductMaterial> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<ProductMaterial> saveEdit(ProductMaterial productMaterialUpdate) {
        String errorMessage;
        Message errorResponse;

        if (productMaterialUpdate.getMaterial() == "") {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<ProductMaterial> optional = repository.findById(productMaterialUpdate.getId());
            if (optional.isPresent()){
                ProductMaterial material = optional.get();
                material.setMaterial(productMaterialUpdate.getMaterial());
                repository.save(material);
                return ResponseEntity.ok(material);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ProductMaterial>> deleteProductMaterial(Long id) {
        try {
            Optional<ProductMaterial> optional = repository.findById(id);
            if (optional.isPresent()){
                ProductMaterial material = optional.get();
                material.setDeleted(true);
                repository.save(material);

                List<ProductMaterial> list = findAllProductMaterial();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<ProductMaterial> saveCreate(ProductMaterial productMaterialCreate) {
        String errorMessage;
        Message errorResponse;

        if (productMaterialCreate.getMaterial() == "") {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            ProductMaterial material = new ProductMaterial();
            material.setMaterial(productMaterialCreate.getMaterial());
            repository.save(material);
            return ResponseEntity.ok(material);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ProductMaterial> searchAllProductMaterial(String search) {
        List<ProductMaterial> list = repository.findByAll(search);
        System.out.println("Search ...");
        return list;
    }

    @Override
    public List<ProductMaterial> searchDateProductMaterial(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<ProductMaterial> list = repository.findByDate(search);
        return list;
    }
}
