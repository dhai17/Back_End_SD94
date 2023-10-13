package SD94.service.serviceImplement;

import SD94.controller.Message.Message;
import SD94.entity.ProductLine;
import SD94.repository.ProductLineRepository;
import SD94.service.ProductLineService;
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
    public List<ProductLine> findAllProductLine() {
        List<ProductLine> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<ProductLine> saveEdit(ProductLine productLineUpdate) {
        String errorMessage;
        Message errorResponse;

        if (productLineUpdate.getName() == "") {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<ProductLine> optional = repository.findById(productLineUpdate.getId());
            if (optional.isPresent()){
                ProductLine line = optional.get();
                line.setName(productLineUpdate.getName());
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
    public ResponseEntity<List<ProductLine>> deleteProductLine(Long id) {
        try {
            Optional<ProductLine> optional = repository.findById(id);
            if (optional.isPresent()){
                ProductLine line = optional.get();
                line.setDeleted(true);
                repository.save(line);

                List<ProductLine> list = findAllProductLine();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<ProductLine> saveCreate(ProductLine productLineCreate) {
        String errorMessage;
        Message errorResponse;

        if (productLineCreate.getName() == "") {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            ProductLine line = new ProductLine();
            line.setName(productLineCreate.getName());
            repository.save(line);
            return ResponseEntity.ok(line);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ProductLine> searchAllProductLine(String search) {
        List<ProductLine> list = repository.findByAll(search);
        System.out.println("Search ...");
        return list;
    }

    @Override
    public List<ProductLine> searchDateProductLine(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<ProductLine> list = repository.findByDate(search);
        return list;
    }
}
