package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.dto.HinhAnhDTO;
import SD94.entity.sanPham.HinhAnh;
import SD94.entity.sanPham.MauSac;
import SD94.entity.sanPham.SanPham;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.sanPham.HinhAnhRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import SD94.repository.sanPham.SanPhamRepository;
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

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    SanPhamRepository sanPhamRepository;


    @Override
    public List<SanPhamChiTiet> findAllProductDetails() {
        List<SanPhamChiTiet> list = repository.findAll();
        return list;
    }

    @Override
    public List<SanPhamChiTiet> findProductDetails() {
        List<SanPhamChiTiet> list = repository.findProductDetails();
        return list;
    }

    @Override
    public ResponseEntity<SanPhamChiTiet> saveEdit(SanPhamChiTiet sanPhamChiTietUpdate) {
        try {
            Optional<SanPhamChiTiet> optional = repository.findById(sanPhamChiTietUpdate.getId());
            if (optional.isPresent()){
                SanPhamChiTiet sanPhamChiTiet = optional.get();
                sanPhamChiTiet.setSoLuong(sanPhamChiTietUpdate.getSoLuong());
                sanPhamChiTiet.setTrangThai(0);
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

    public ResponseEntity<List<SanPhamChiTiet>> deleteProductDetails(Long id) {
        try {
            Optional<SanPhamChiTiet> optional = repository.findById(id);
            SanPham sp = sanPhamRepository.findByID(optional.get().getSanPham().getId());
            if (optional.isPresent()){
                SanPhamChiTiet sanPhamChiTiet = optional.get();
                sanPhamChiTiet.setDeleted(true);
                repository.save(sanPhamChiTiet);
                List<SanPhamChiTiet> list = repository.findSpctByIdSp(sp.getId());
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
        try {
            SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();

            return ResponseEntity.ok(sanPhamChiTiet);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<SanPhamChiTiet> searchAllProductDetails(String search) {
        List<SanPhamChiTiet> list = repository.findByAll(search);
        return list;
    }

    @Override
    public List<SanPhamChiTiet> searchDateProductDetails(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<SanPhamChiTiet> list = repository.findByDate(search);
        return list;
    }

    @Override
    public ResponseEntity chinhSuaSoLuongSPCT(SanPhamChiTiet sanPhamChiTiet) {
        SanPhamChiTiet sanPhamChiTiets = repository.findByID(sanPhamChiTiet.getId());
        sanPhamChiTiets.setSoLuong(sanPhamChiTiet.getSoLuong());
        repository.save(sanPhamChiTiets);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity themAnhSanPham(HinhAnhDTO hinhAnhDTO) {
        SanPhamChiTiet sanPhamChiTiet = repository.findByID(hinhAnhDTO.getId_SPCT());
        MauSac mauSac = sanPhamChiTiet.getMauSac();
        HinhAnh hinhAnh = new HinhAnh();
        hinhAnh.setTenAnh(hinhAnhDTO.getName());
        hinhAnh.setSanPhamChiTiet(sanPhamChiTiet);
        hinhAnh.setMauSac(mauSac);
        hinhAnh.setAnhMacDinh(false);
        hinhAnhRepository.save(hinhAnh);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity themAnhMacDinh(HinhAnhDTO hinhAnhDTO) {
        HinhAnh hinhAnh = hinhAnhRepository.findByID(hinhAnhDTO.getId());
        hinhAnh.setAnhMacDinh(true);
        hinhAnhRepository.save(hinhAnh);
        return new ResponseEntity(HttpStatus.OK);
    }
}
