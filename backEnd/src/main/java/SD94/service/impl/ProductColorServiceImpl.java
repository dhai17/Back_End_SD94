package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.product.Color;
import SD94.repository.ProductColorRepository;
import SD94.service.service.ProductColorService;
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
    public List<Color> findAllProductColor() {
        List<Color> colors = repository.findAll();
        return colors;
    }

    @Override
    public ResponseEntity<Color> saveEdit(Color colorUpdate) {
        String errorMessage;
        Message errorResponse;

        if (colorUpdate.getColor() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<Color> optionalProductColor = repository.findById(colorUpdate.getId());
            if (optionalProductColor.isPresent()){
                Color color = optionalProductColor.get();
                color.setCode(colorUpdate.getCode());
                color.setColor(colorUpdate.getColor());
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
    public ResponseEntity<List<Color>> deleteProductColor(Long id) {
        try {
            Optional<Color> optionalProductColor = repository.findById(id);
            if (optionalProductColor.isPresent()){
                Color color = optionalProductColor.get();
                color.setDeleted(true);
                repository.save(color);

                List<Color> colorList = findAllProductColor();
                return ResponseEntity.ok(colorList);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<Color> saveCreate(Color productColor) {
        String errorMessage;
        Message errorResponse;

        if (productColor.getColor() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Color color = new Color();
            color.setColor(productColor.getColor());
            color.setCode(productColor.getCode());
            repository.save(color);
            return ResponseEntity.ok(color);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Color> searchAllProductColor(String search) {
        List<Color> colorList = repository.findByAll(search);
        System.out.println("Search ...");
        return colorList;
    }

    @Override
    public List<Color> searchDateProductColor(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Color> colorList = repository.findByDate(search);
        return colorList;
    }
}
