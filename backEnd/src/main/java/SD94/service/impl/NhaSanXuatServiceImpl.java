package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.sanPham.NhaSanXuat;
import SD94.repository.ProducerRepository;
import SD94.service.service.NhaSanXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NhaSanXuatServiceImpl implements NhaSanXuatService {

    @Autowired
    ProducerRepository producerRepository;

    @Override
    public List<NhaSanXuat> findAllProducer() {
        List<NhaSanXuat> nhaSanXuats = producerRepository.findAllProducer();
        return nhaSanXuats;
    }

    @Override
    public ResponseEntity<NhaSanXuat> saveEdit(NhaSanXuat nhaSanXuatUpdate) {

        String errorMessage;
        Message errorResponse;

        if (nhaSanXuatUpdate.getName() == "" || nhaSanXuatUpdate.getDiaChi() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<NhaSanXuat> optionalProducer = producerRepository.findById(nhaSanXuatUpdate.getId());
            if (optionalProducer.isPresent()){
                NhaSanXuat nhaSanXuat = optionalProducer.get();
                nhaSanXuat.setName(nhaSanXuatUpdate.getName());
                nhaSanXuat.setDiaChi(nhaSanXuatUpdate.getDiaChi());
                producerRepository.save(nhaSanXuat);
                return ResponseEntity.ok(nhaSanXuat);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<NhaSanXuat>> deleteProducer(Long id) {
        try {
            Optional<NhaSanXuat> optionalProducer = producerRepository.findById(id);
            if (optionalProducer.isPresent()){
                NhaSanXuat nhaSanXuat = optionalProducer.get();
                nhaSanXuat.setDeleted(true);
                producerRepository.save(nhaSanXuat);

                List<NhaSanXuat> nhaSanXuatList = findAllProducer();
                return ResponseEntity.ok(nhaSanXuatList);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<NhaSanXuat> saveCreate(NhaSanXuat nhaSanXuatCreate) {

        String errorMessage;
        Message errorResponse;

        if (nhaSanXuatCreate.getName() == null || nhaSanXuatCreate.getDiaChi() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            NhaSanXuat nhaSanXuat = new NhaSanXuat();
            nhaSanXuat.setName(nhaSanXuatCreate.getName());
            nhaSanXuat.setDiaChi(nhaSanXuatCreate.getDiaChi());
            producerRepository.save(nhaSanXuat);
            return ResponseEntity.ok(nhaSanXuat);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public List<NhaSanXuat> searchAllProducer(String search) {
        List<NhaSanXuat> nhaSanXuatList = producerRepository.findProducerByAll(search);
        return nhaSanXuatList;
    }

    @Override
    public List<NhaSanXuat> searchDateProducer(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<NhaSanXuat> nhaSanXuatList = producerRepository.findProducerByDate(search);
        return nhaSanXuatList;
    }
}