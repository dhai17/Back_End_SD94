package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.sanPham.ChatLieu;
import SD94.entity.sanPham.NhaSanXuat;
import SD94.entity.sanPham.SanPham;
import SD94.entity.sanPham.LoaiSanPham;
import SD94.repository.sanPham.ChatLieuRepository;
import SD94.repository.sanPham.LoaiSanPhamRepository;
import SD94.repository.sanPham.NhaSanXuatRepository;
import SD94.repository.sanPham.SanPhamRepository;
import SD94.service.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository repository;

    @Autowired
    ChatLieuRepository productMaterialRepository;

    @Autowired
    LoaiSanPhamRepository productLineRepository;

    @Autowired
    NhaSanXuatRepository producerRepository;


    @Override
    public List<SanPham> findAllProduct() {
        List<SanPham> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<SanPham> saveEdit(SanPham sanPhamUpdate) {
        try {
            Optional<SanPham> optional = repository.findById(sanPhamUpdate.getId());
            if (optional.isPresent()){
                SanPham sanPham = optional.get();
                sanPham.setTenSanPham(sanPhamUpdate.getTenSanPham());
                sanPham.setGia(sanPhamUpdate.getGia());
                sanPham.setTrangThai(sanPhamUpdate.getTrangThai());
                sanPham.setNhaSanXuat(sanPhamUpdate.getNhaSanXuat());
                sanPham.setLoaiSanPham(sanPhamUpdate.getLoaiSanPham());
                sanPham.setChatLieu(sanPhamUpdate.getChatLieu());
                repository.save(sanPham);
                return ResponseEntity.ok(sanPham);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<SanPham>> deleteProduct(Long id) {
        try {
            Optional<SanPham> optional = repository.findById(id);
            if (optional.isPresent()){
                SanPham sanPham = optional.get();
                sanPham.setDeleted(true);
                repository.save(sanPham);

                List<SanPham> list = findAllProduct();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<SanPham> saveCreate(SanPham sanPhamCreate) {
        System.out.println(sanPhamCreate);
        try {
            if (sanPhamCreate.getChatLieu() != null) {
                Optional<ChatLieu> optionalProductMaterial = productMaterialRepository.findById(sanPhamCreate.getChatLieu().getId());
                if (optionalProductMaterial.isPresent()) {
                    ChatLieu chatLieu = optionalProductMaterial.get();
                    sanPhamCreate.setChatLieu(chatLieu);
                } else {
                    return new ResponseEntity(new Message("Invalid Product Material", TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
                }
            }

            if (sanPhamCreate.getLoaiSanPham() != null) {
                Optional<LoaiSanPham> optionalProductLine = productLineRepository.findById(sanPhamCreate.getLoaiSanPham().getId());
                if (optionalProductLine.isPresent()) {
                    LoaiSanPham line = optionalProductLine.get();
                    sanPhamCreate.setLoaiSanPham(line);
                } else {
                    return new ResponseEntity(new Message("Invalid Product Line", TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
                }
            }

            if (sanPhamCreate.getNhaSanXuat() != null) {
                Optional<NhaSanXuat> optionalProducer = producerRepository.findById(sanPhamCreate.getNhaSanXuat().getId());
                if (optionalProducer.isPresent()) {
                    NhaSanXuat nhaSanXuat = optionalProducer.get();
                    sanPhamCreate.setNhaSanXuat(nhaSanXuat);
                } else {
                    return new ResponseEntity(new Message("Invalid Producer", TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
                }
            }

            repository.save(sanPhamCreate);
            return ResponseEntity.ok(sanPhamCreate);
        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<SanPham> searchAllProduct(String search) {
        List<SanPham> list = repository.findByAll(search);
        System.out.println("Search ...");
        return list;
    }

    @Override
    public List<SanPham> searchDateProduct(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<SanPham> list = repository.findByDate(search);
        return list;
    }

}
