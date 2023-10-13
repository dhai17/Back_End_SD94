package SD94.service.serviceImplement;

import SD94.controller.Message.Message;
import SD94.entity.Producer;
import SD94.repository.ProducerRepository;
import SD94.service.ProducerService;
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
    public List<Producer> findAllProducer() {
        List<Producer> producers = producerRepository.findAllProducer();
        return producers;
    }

    @Override
    public ResponseEntity<Producer> saveEdit(Producer producerUpdate) {

        String errorMessage;
        Message errorResponse;

        if (producerUpdate.getName() == "" || producerUpdate.getAddress() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<Producer> optionalProducer = producerRepository.findById(producerUpdate.getId());
            if (optionalProducer.isPresent()){
                Producer producer = optionalProducer.get();
                producer.setName(producerUpdate.getName());
                producer.setAddress(producerUpdate.getAddress());
                producerRepository.save(producer);
                return ResponseEntity.ok(producer);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Producer>> deleteProducer(Long id) {
        try {
            Optional<Producer> optionalProducer = producerRepository.findById(id);
            if (optionalProducer.isPresent()){
                Producer producer = optionalProducer.get();
                producer.setDeleted(true);
                producerRepository.save(producer);

                List<Producer> producerList = findAllProducer();
                return ResponseEntity.ok(producerList);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<Producer> saveCreate(Producer producerCreate) {

        String errorMessage;
        Message errorResponse;

        if (producerCreate.getName() == null || producerCreate.getAddress() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Producer producer = new Producer();
            producer.setName(producerCreate.getName());
            producer.setAddress(producerCreate.getAddress());
            producerRepository.save(producer);
            return ResponseEntity.ok(producer);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public List<Producer> searchAllProducer(String search) {
        List<Producer> producerList = producerRepository.findProducerByAll(search);
        System.out.println("Search ...");
        return producerList;
    }

    @Override
    public List<Producer> searchDateProducer(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Producer> producerList = producerRepository.findProducerByDate(search);
        return producerList;
    }
}
