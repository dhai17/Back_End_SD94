package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.sanPham.ChatLieu;
import SD94.repository.sanPham.ChatLieuRepository;
import SD94.service.service.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChatLieuServiceImpl implements ChatLieuService {

    @Autowired
    ChatLieuRepository repository;

    @Override
    public List<ChatLieu> findAllProductMaterial() {
        List<ChatLieu> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<ChatLieu> saveEdit(ChatLieu chatLieuUpdate) {
        String errorMessage;
        Message errorResponse;

        if (chatLieuUpdate.getChatLieu() == "") {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<ChatLieu> optional = repository.findById(chatLieuUpdate.getId());
            if (optional.isPresent()){
                ChatLieu chatLieu = optional.get();
                chatLieu.setChatLieu(chatLieuUpdate.getChatLieu());
                repository.save(chatLieu);
                return ResponseEntity.ok(chatLieu);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ChatLieu>> deleteProductMaterial(Long id) {
        try {
            Optional<ChatLieu> optional = repository.findById(id);
            if (optional.isPresent()){
                ChatLieu chatLieu = optional.get();
                chatLieu.setDeleted(true);
                repository.save(chatLieu);

                List<ChatLieu> list = findAllProductMaterial();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<ChatLieu> saveCreate(ChatLieu chatLieuCreate) {
        String errorMessage;
        Message errorResponse;

        if (chatLieuCreate.getChatLieu() == "") {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            ChatLieu chatLieu = new ChatLieu();
            chatLieu.setChatLieu(chatLieuCreate.getChatLieu());
            repository.save(chatLieu);
            return ResponseEntity.ok(chatLieu);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ChatLieu> searchAllProductMaterial(String search) {
//        List<ChatLieu> list = repository.f(search);
        return null;
    }

    @Override
    public List<ChatLieu> searchDateProductMaterial(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<ChatLieu> list = repository.findByDate(search);
        return list;
    }
}
