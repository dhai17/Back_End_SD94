package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import SD94.service.service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    @Autowired
    SanPhamChiTietRepository repository;


    @Override
    public List<SanPhamChiTiet> findAllProductDetails() {
        List<SanPhamChiTiet> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<SanPhamChiTiet> saveEdit(SanPhamChiTiet sanPhamChiTietUpdate) {
//        String errorMessage;
//        Message errorResponse;
//
//        if (productDetailsUpdate.getQuantity() == null) {
//            errorMessage = "Nhập đầy đủ thông tin";
//            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
//            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
//        }

        try {
            Optional<SanPhamChiTiet> optional = repository.findById(sanPhamChiTietUpdate.getId());
            if (optional.isPresent()){
                SanPhamChiTiet sanPhamChiTiet = optional.get();
                sanPhamChiTiet.setSoLuong(sanPhamChiTietUpdate.getSoLuong());
                sanPhamChiTiet.setTrangThai(sanPhamChiTietUpdate.getTrangThai());
                sanPhamChiTiet.setSanPham(sanPhamChiTietUpdate.getSanPham());
                sanPhamChiTiet.setMauSac(sanPhamChiTietUpdate.getMauSac());
                sanPhamChiTiet.setKichCo(sanPhamChiTietUpdate.getKichCo());
                repository.save(sanPhamChiTiet);
                return ResponseEntity.ok(sanPhamChiTiet);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<SanPhamChiTiet>> deleteProductDetails(Long id) {
        try {
            Optional<SanPhamChiTiet> optional = repository.findById(id);
            if (optional.isPresent()){
                SanPhamChiTiet sanPhamChiTiet = optional.get();
                sanPhamChiTiet.setDeleted(true);
                repository.save(sanPhamChiTiet);

                List<SanPhamChiTiet> list = findAllProductDetails();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<SanPhamChiTiet> saveCreate(SanPhamChiTiet sanPhamChiTietCreate) {
        String errorMessage;
        Message errorResponse;
//
//        if (productDetailsCreate.getQuantity() == null) {
//            errorMessage = "Nhập đầy đủ thông tin";
//            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
//            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
//        }

        try {
            SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
//            productDetails.setQuantity(productDetailsCreate.getQuantity());
//            productDetails.setStatus(productDetailsCreate.getStatus());
//            productDetails.setProduct(productDetailsCreate.getProduct());
//            productDetails.setProductColor(productDetailsCreate.getProductColor());
//            productDetails.setProductSize(productDetailsCreate.getProductSize());
//            repository.save(productDetails);
            return ResponseEntity.ok(sanPhamChiTiet);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<SanPhamChiTiet> searchAllProductDetails(String search) {
        List<SanPhamChiTiet> list = repository.findByAll(search);
        System.out.println("Search ...");
        return list;
    }

    @Override
    public List<SanPhamChiTiet> searchDateProductDetails(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<SanPhamChiTiet> list = repository.findByDate(search);
        return list;
    }
}
