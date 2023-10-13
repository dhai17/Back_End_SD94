package SD94.service.serviceImplement;

import SD94.controller.Message.Message;
import SD94.entity.ProductColor;
import SD94.repository.ProductColorRepository;
import SD94.service.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductColorServiceImpl implements ProductColorService {

    @Autowired
    ProductColorRepository repository;

    @Override
    public List<ProductColor> findAllProductColor() {
        List<ProductColor> colors = repository.findAll();
        return colors;
    }

    @Override
    public ResponseEntity<ProductColor> saveEdit(ProductColor productColorUpdate) {
        String errorMessage;
        Message errorResponse;

        if (productColorUpdate.getColor() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<ProductColor> optionalProductColor = repository.findById(productColorUpdate.getId());
            if (optionalProductColor.isPresent()){
                ProductColor color = optionalProductColor.get();
                color.setColor(productColorUpdate.getColor());
                repository.save(color);
                return ResponseEntity.ok(color);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ProductColor>> deleteProductColor(Long id) {
        try {
            Optional<ProductColor> optionalProductColor = repository.findById(id);
            if (optionalProductColor.isPresent()){
                ProductColor color = optionalProductColor.get();
                color.setDeleted(true);
                repository.save(color);

                List<ProductColor> colorList = findAllProductColor();
                return ResponseEntity.ok(colorList);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<ProductColor> saveCreate(ProductColor productColorCreate) {
        String errorMessage;
        Message errorResponse;

        if (productColorCreate.getColor() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            ProductColor color = new ProductColor();
            color.setColor(productColorCreate.getColor());
            repository.save(color);
            return ResponseEntity.ok(color);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ProductColor> searchAllProductColor(String search) {
        List<ProductColor> colorList = repository.findByAll(search);
        System.out.println("Search ...");
        return colorList;
    }

    @Override
    public List<ProductColor> searchDateProductColor(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<ProductColor> colorList = repository.findByDate(search);
        return colorList;
    }
}
