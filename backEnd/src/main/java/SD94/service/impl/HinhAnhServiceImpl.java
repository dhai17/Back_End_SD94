package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.sanPham.HinhAnh;
import SD94.repository.sanPham.HinhAnhRepository;
import SD94.service.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HinhAnhServiceImpl implements HinhAnhService {

    @Autowired
    HinhAnhRepository repository;


    @Override
    public List<HinhAnh> findAllProductImages() {
        List<HinhAnh> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<HinhAnh> saveEdit(HinhAnh hinhAnhUpdate) {
        try {
            Optional<HinhAnh> optional = repository.findById(hinhAnhUpdate.getId());
            if (optional.isPresent()){
                HinhAnh images = optional.get();
                images.setTenAnh(hinhAnhUpdate.getTenAnh());
//                images.setImg(productImagesUpdate.getImg());
                images.setMauSac(hinhAnhUpdate.getMauSac());
                images.setSanPhamChiTiet(hinhAnhUpdate.getSanPhamChiTiet());
                repository.save(images);
                return ResponseEntity.ok(images);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<HinhAnh>> deleteProductImages(Long id) {
        try {
            Optional<HinhAnh> optional = repository.findById(id);
            if (optional.isPresent()){
                HinhAnh images = optional.get();
                images.setDeleted(true);
                repository.save(images);

                List<HinhAnh> list = findAllProductImages();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<HinhAnh> saveCreate(HinhAnh hinhAnhCreate) {
        try {
            HinhAnh images = new HinhAnh();
            images.setTenAnh(hinhAnhCreate.getTenAnh());
            images.setMauSac(hinhAnhCreate.getMauSac());
            images.setSanPhamChiTiet(hinhAnhCreate.getSanPhamChiTiet());
            repository.save(images);
            return ResponseEntity.ok(images);
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<HinhAnh> searchAllProductImages(String search) {
        List<HinhAnh> list = repository.findByAll(search);
        return list;
    }

    @Override
    public List<HinhAnh> searchDateProductImages(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<HinhAnh> list = repository.findByDate(search);
        return list;
    }

    @Override
    public HinhAnh saveOrUpdate(HinhAnh images) {
//        images.setImg(0);
        return repository.save(images);
    }

    @Override
    public HinhAnh findByMaSanPham(Long maSanPham) {
        HinhAnh banPhim = new HinhAnh();
        banPhim.setId(maSanPham);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());

        Example<HinhAnh> example = Example.of(banPhim, matcher);
        return repository.findOne(example).orElse(null);
    }
}
