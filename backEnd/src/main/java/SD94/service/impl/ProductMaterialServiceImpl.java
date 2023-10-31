package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.product.Material;
import SD94.repository.ProductMaterialRepository;
import SD94.service.service.ProductMaterialService;
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
    public List<Material> findAllProductMaterial() {
        List<Material> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<Material> saveEdit(Material materialUpdate) {
        String errorMessage;
        Message errorResponse;

        if (materialUpdate.getMaterial() == "") {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<Material> optional = repository.findById(materialUpdate.getId());
            if (optional.isPresent()){
                Material material = optional.get();
                material.setMaterial(materialUpdate.getMaterial());
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
    public ResponseEntity<List<Material>> deleteProductMaterial(Long id) {
        try {
            Optional<Material> optional = repository.findById(id);
            if (optional.isPresent()){
                Material material = optional.get();
                material.setDeleted(true);
                repository.save(material);

                List<Material> list = findAllProductMaterial();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<Material> saveCreate(Material materialCreate) {
        String errorMessage;
        Message errorResponse;

        if (materialCreate.getMaterial() == "") {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Material material = new Material();
            material.setMaterial(materialCreate.getMaterial());
            repository.save(material);
            return ResponseEntity.ok(material);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Material> searchAllProductMaterial(String search) {
        List<Material> list = repository.findByAll(search);
        System.out.println("Search ...");
        return list;
    }

    @Override
    public List<Material> searchDateProductMaterial(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Material> list = repository.findByDate(search);
        return list;
    }
}
