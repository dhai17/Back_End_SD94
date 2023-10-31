package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.product.Manufacturer;
import SD94.repository.ProducerRepository;
import SD94.service.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    ProducerRepository producerRepository;

    @Override
    public List<Manufacturer> findAllProducer() {
        List<Manufacturer> manufacturers = producerRepository.findAllProducer();
        return manufacturers;
    }

    @Override
    public ResponseEntity<Manufacturer> saveEdit(Manufacturer manufacturerUpdate) {

        String errorMessage;
        Message errorResponse;

        if (manufacturerUpdate.getName() == "" || manufacturerUpdate.getAddress() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<Manufacturer> optionalProducer = producerRepository.findById(manufacturerUpdate.getId());
            if (optionalProducer.isPresent()){
                Manufacturer manufacturer = optionalProducer.get();
                manufacturer.setName(manufacturerUpdate.getName());
                manufacturer.setAddress(manufacturerUpdate.getAddress());
                producerRepository.save(manufacturer);
                return ResponseEntity.ok(manufacturer);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Manufacturer>> deleteProducer(Long id) {
        try {
            Optional<Manufacturer> optionalProducer = producerRepository.findById(id);
            if (optionalProducer.isPresent()){
                Manufacturer manufacturer = optionalProducer.get();
                manufacturer.setDeleted(true);
                producerRepository.save(manufacturer);

                List<Manufacturer> manufacturerList = findAllProducer();
                return ResponseEntity.ok(manufacturerList);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<Manufacturer> saveCreate(Manufacturer manufacturerCreate) {

        String errorMessage;
        Message errorResponse;

        if (manufacturerCreate.getName() == null || manufacturerCreate.getAddress() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setName(manufacturerCreate.getName());
            manufacturer.setAddress(manufacturerCreate.getAddress());
            producerRepository.save(manufacturer);
            return ResponseEntity.ok(manufacturer);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public List<Manufacturer> searchAllProducer(String search) {
        List<Manufacturer> manufacturerList = producerRepository.findProducerByAll(search);
        System.out.println("Search ...");
        return manufacturerList;
    }

    @Override
    public List<Manufacturer> searchDateProducer(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Manufacturer> manufacturerList = producerRepository.findProducerByDate(search);
        return manufacturerList;
    }
}
