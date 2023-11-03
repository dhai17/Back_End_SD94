package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.dto.SanPhamDTO;
import SD94.entity.sanPham.*;
import SD94.repository.sanPham.*;
import SD94.service.service.HinhAnhService;
import SD94.service.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository repository;

    @Autowired
    HinhAnhService hinhAnhService;

    @Autowired
    ChatLieuRepository chatLieuRepository;

    @Autowired
    LoaiSanPhamRepository loaiSanPhamRepository;

    @Autowired
    NhaSanXuatRepository nhaSanXuatRepository;

    @Autowired
    MauSacRepository mauSacRepository;

    @Autowired
    KichCoRepository kichCoRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Override
    public List<SanPham> findAllProduct() {
        List<SanPham> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<SanPham> saveEdit(SanPhamDTO sanPhamDTO) {
        try {
            Optional<SanPhamChiTiet> chiTietOptional = sanPhamChiTietRepository.findById(sanPhamDTO.getId());
            Optional<SanPham> optional = repository.findById(sanPhamDTO.getId());
            if (optional.isPresent() && chiTietOptional.isPresent()){
                SanPham sanPham = optional.get();
                SanPhamChiTiet sanPhamChiTiet = chiTietOptional.get();
                sanPham.setTenSanPham(sanPhamDTO.getTenSanPham());
                sanPham.setGia(sanPhamDTO.getGia());
                sanPham.setTrangThai(sanPhamDTO.getTrangThai());
                sanPham.setNhaSanXuat(sanPhamDTO.getNhaSanXuat());
                sanPham.setLoaiSanPham(sanPhamDTO.getLoaiSanPham());
                sanPham.setChatLieu(sanPhamDTO.getChatLieu());
                sanPhamChiTiet.setSoLuong(sanPhamDTO.getSoLuong());
                sanPhamChiTiet.setMauSac((MauSac) sanPhamDTO.getMauSac());
                sanPhamChiTiet.setKichCo((KichCo) sanPhamDTO.getKichCo());
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
                sanPham.setTrangThai(1);
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
    public List<SanPham> searchAllProduct(String search) {
        List<SanPham> list = repository.findByAll(search);
        return list;
    }

    @Override
    public List<SanPham> searchDateProduct(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<SanPham> list = repository.findByDate(search);
        return list;
    }

    @Override
    public List<SanPhamChiTiet> taoSanPham(SanPhamDTO sanPhamDTO) {
        ChatLieu chatLieu = chatLieuRepository.findByID(sanPhamDTO.getChatLieu_id());
        LoaiSanPham loaiSanPham = loaiSanPhamRepository.findByID(sanPhamDTO.getLoaiSanPham_id());
        NhaSanXuat nhaSanXuat = nhaSanXuatRepository.findByID(sanPhamDTO.getNhaSanXuat_id());

        SanPham sanPham = new SanPham();
        sanPham.setTenSanPham(sanPhamDTO.getTenSanPham());
        sanPham.setGia(sanPhamDTO.getGia());
        sanPham.setTrangThai(0);
        sanPham.setLoaiSanPham(loaiSanPham);
        sanPham.setNhaSanXuat(nhaSanXuat);
        sanPham.setChatLieu(chatLieu);
        repository.save(sanPham);

        List<SanPhamChiTiet> sanPhamChiTietList = new ArrayList<>();
        for (Long kichCo_id : sanPhamDTO.getKichCo()) {
            KichCo kichCo = kichCoRepository.findByID(kichCo_id);
            for (Long mauSac_id : sanPhamDTO.getMauSac()) {
                MauSac mauSac = mauSacRepository.findByID(mauSac_id);
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                sanPhamChiTiet.setSanPham(sanPham);
                sanPhamChiTiet.setMauSac(mauSac);
                sanPhamChiTiet.setKichCo(kichCo);
                sanPhamChiTiet.setSoLuong(sanPhamDTO.getSoLuong());
                sanPhamChiTietRepository.save(sanPhamChiTiet);
                sanPhamChiTietList.add(sanPhamChiTiet);
            }
        }
        return sanPhamChiTietList;
    }

    @Override
    public List<Object> chiTietSanPham(long id_SanPham) {
        SanPham sanPham = repository.findByID(id_SanPham);
        List<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietRepository.findByProductID(id_SanPham);
        List<Object> respone = new ArrayList<>();
        respone.add(sanPham);
        respone.add(sanPhamChiTiets);
        return respone;
    }

}
